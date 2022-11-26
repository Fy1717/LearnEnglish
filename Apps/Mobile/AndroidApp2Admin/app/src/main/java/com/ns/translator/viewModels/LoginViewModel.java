package com.ns.translator.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.view.View;

import com.ns.translator.models.LoginUser;


public class LoginViewModel extends ViewModel {
    private String mMail;
    private String mPassword;

    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<LoginUser> userMutableLiveData;

    public MutableLiveData<LoginUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    public MutableLiveData<LoginUser> setUser(String mail, String password) {
        if (mail == null) {
            this.mMail = mail;
        }

        if (mPassword == null) {
            this.mPassword = password;
        }

        return userMutableLiveData;
    }


    public void onClick(View view) {
        LoginUser loginUser = new LoginUser(EmailAddress.getValue(), Password.getValue());

        userMutableLiveData.setValue(loginUser);
    }
}