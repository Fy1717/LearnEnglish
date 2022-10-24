package com.raven.form;

import PrIns.Exceptions.General;
import com.raven.component.Form;
import com.raven.models.Controller;
import com.raven.models.Status;
import com.raven.requests.LoginRequest;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Login_Form extends Form implements ActionListener {
    public static Controller controller;
    private static Status status = Status.getInstanceStatus();
    JTextField username, password; JButton login;
    javax.swing.JLabel loginTitle, resultLabel;

    public void LabelExample(){
        loginTitle = new javax.swing.JLabel();
        loginTitle.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        loginTitle.setForeground(new java.awt.Color(230, 230, 230));
        loginTitle.setText("LOGIN PAGE");
        loginTitle.setBounds(120,100, 150,40);

        username = new JTextField();
        username.setBounds(100,150, 150,40);
        username.setText("frknyldz8489@gmail.com");

        password = new JTextField();
        password.setBounds(100,200, 150,40);
        password.setText("Furkan123+-");

        login = new com.raven.swing.Button();
        login.setText("LOGIN");
        login.setBounds(125,250,100,30);
        login.addActionListener(this);

        resultLabel = new javax.swing.JLabel();
        resultLabel.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        resultLabel.setForeground(new java.awt.Color(230, 230, 230));
        resultLabel.setBounds(125,300, 250,20);

        add(login); add(username); add(password); add(resultLabel); add(loginTitle);

        setSize(400,400);

        setLayout(null);
        setVisible(true);
    }

    public Login_Form() {
        initComponents();
        try {
            controller = Controller.getInstanceController();
        } catch (General e) {
            throw new RuntimeException(e);
        }
    }

    private void initComponents() {
        LabelExample();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String usernameInput = username.getText();
            String passwordInput = password.getText();

            String result = usernameInput + " " + passwordInput;

            JSONObject reqObj = prepareReqJsonObj(usernameInput, passwordInput);

            String APIUrl=  "https://api.learnenglish.helloworldeducation.com/api/user/login";

            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("Mail", usernameInput);
            parameters.put("Password", passwordInput);

            System.out.println("REQUEST STR : " + parameters.get("Mail") + " : " + parameters.get("Password"));

            String response = LoginRequest.makePostRequest(APIUrl, parameters);

            System.out.println("RESPONSE : " + response);

            resultLabel.setText("CONTROL DATA : " + result);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public JSONObject prepareReqJsonObj(String s1,String s2) {
        JSONObject jsonobj = new JSONObject();

        jsonobj.put("Mail", s1);
        jsonobj.put("Password", s2);

        return jsonobj;
    }
}
