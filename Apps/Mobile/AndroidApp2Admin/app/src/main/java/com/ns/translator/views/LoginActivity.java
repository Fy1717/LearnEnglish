package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ns.translator.R;
import com.ns.translator.models.LoginUser;
import com.ns.translator.viewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView resultUi;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginViewModel loginViewModel = new ViewModelProvider(LoginActivity.this).get(LoginViewModel.class);

        email = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        resultUi = findViewById(R.id.resultMailAndPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.onClick(view);

                System.out.println("yyyyyyyyyyyyyyyyy");
            }
        });

        loginViewModel.getUser().observe(LoginActivity.this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                System.out.println("MAIL : " + loginUser.getStrEmailAddress());
                System.out.println("PASSWORD : " + loginUser.getStrPassword());
            }
        });
    }
}