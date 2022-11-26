package com.ns.translator.models;

import android.util.Log;
import android.util.Patterns;

import org.json.JSONObject;

import java.io.IOException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginUser {

    private String strEmailAddress;
    private String strPassword;

    public LoginUser(String EmailAddress, String Password) {
        strEmailAddress = EmailAddress;
        strPassword = Password;

        LoginToServer();
    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }

    public String LoginToServer() {
        System.out.println("XXXXXXXXXXXXXXX");
        String responseString = null;

        try {
            String urlParameter = "https://api.learnenglish.helloworldeducation.com/api/User/login";
            Response response = null;

            OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
            newBuilder.hostnameVerifier((hostname, session) -> true);

            OkHttpClient newClient = newBuilder.build();
            //Request.Builder builder = new Request.Builder();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"mail\": \"user@user.com\",\"password\": \"user123\"}");

//            Request request = builder
//                    .url(urlParameter)
//                    .method("POST", body)
//                    .addHeader("token", MainModel.MainToken)
//                    .build();

            Request request2 = new Request.Builder()
                    .url(urlParameter)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            response = newClient.newCall(request2).execute();
            responseString = response.body().string();

            System.out.println("RESPONSE : " + responseString);
            //jsonResponseObject = new JSONObject(jsonResponseData);

            if (response.code() == 200) {
            } else {
                System.out.println("HATA 1 : " + response.code());
            }
        } catch (Exception e) {
            System.out.println("HATA 0 : " + e.getMessage());
        }

        return "ssss";
    }
}