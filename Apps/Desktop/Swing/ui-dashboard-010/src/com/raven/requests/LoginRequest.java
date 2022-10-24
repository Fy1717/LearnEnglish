package com.raven.requests;
import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    private final static String USER_AGENT = "Mozilla/5.0";


    static {
        // this part is needed cause Lebocoin has invalid SSL certificate, that cannot be normally processed by Java
        TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null; // Not relevant.
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing. Just allow them all.
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Do nothing. Just allow them all.
                    }
                }
        };

        HostnameVerifier trustAllHostnames = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true; // Just allow them all.
            }
        };

        try {
            System.setProperty("jsse.enableSNIExtension", "false");
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCertificates, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "https://api.learnenglish.helloworldeducation.com/api/user/login";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("Mail", "frknyldz848@gmail.com");
        parameters.put("Password", "Furkan123+-");
        makePostRequest(url, parameters);
    }

    /**
     * Make post request for given URL with given parameters and save response into RESPONSE_FILE_LOCATION
     *
     * @param url        HTTPS link to send POST request
     * @param parameters POST request parameters. currently expecting following parameters:
     *                   name, email, phone, body, send
     * @return
     */
    public static String makePostRequest(String url, Map<String, String> parameters) {
        try {
            //we need this cookie to submit form
            String initialCookies = getUrlConnection(url, "").getHeaderField("Set-Cookie");
            HttpsURLConnection con = getUrlConnection(url, initialCookies);
            String urlParameters = processRequestParameters(parameters);

            sendPostParameters(con, urlParameters);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));

            String result = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
            }
            in.close();

            System.out.println("RESULT : " + result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    /**
     * Send POST parameters to given connection
     *
     * @param con           connection to set parameters on
     * @param urlParameters encoded URL POST parameters
     * @throws IOException
     */
    private static void sendPostParameters(URLConnection con, String urlParameters) throws IOException {
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
    }

    /**
     * Create HttpsURLConnection for given URL with given Cookies
     *
     * @param url     url to query
     * @param cookies cookies to use for this connection
     * @return ready-to-use HttpURLConnection
     * @throws IOException
     */
    private static HttpsURLConnection getUrlConnection(String url, String cookies) throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Cookie", cookies);
        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
        con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

        return con;
    }

    /**
     * Convert given Map of parameters to URL-encoded string
     *
     * @param parameters request parameters
     * @return URL-encoded parameters string
     */
    private static String processRequestParameters(Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder();
        for (String parameterName : parameters.keySet()) {
            sb.append(parameterName).append('=').append(urlEncode(parameters.get(parameterName))).append('&');
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Encode given String with URLEncoder in UTF-8
     *
     * @param s string to encode
     * @return URL-encoded string
     */
    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // This is impossible, UTF-8 is always supported according to the java standard
            throw new RuntimeException(e);
        }
    }
}