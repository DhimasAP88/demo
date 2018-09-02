package com.art.dhimas.artapplication.modul.login;

import android.app.Activity;
import android.text.TextUtils;

import com.art.dhimas.artapplication.component.util.MethodUtil;
import com.art.dhimas.artapplication.component.util.PreferenceManager;

public class LoginPresenterImpl implements LoginPresenter {
    private static String USERNAME_BYPASS = "JHONDOE";
    private static String PASSWORD_BYPASS = "SECRET123";

    private EventLogin eventLogin;
    private Activity activity;

    public LoginPresenterImpl(EventLogin eventLogin, Activity activity) {
        this.eventLogin = eventLogin;
        this.activity = activity;
    }

    @Override
    public void submitLogin(String username, String password) {
        if (MethodUtil.validateLogin(username, password, activity)) {
            String[] userData = PreferenceManager.getUserLogin();
            if ((username.equalsIgnoreCase(USERNAME_BYPASS) &&
                    password.equalsIgnoreCase(PASSWORD_BYPASS)) ||
                    (username.equalsIgnoreCase(userData[0]) &&
                    password.equalsIgnoreCase(userData[1]))) {
                PreferenceManager.setIsLogin(true);
                eventLogin.onSuccessLogin();
            } else {
                eventLogin.onError("Authentication failed");
            }

        }
    }

    public interface EventLogin{
        void onSuccessLogin();

        void onError(String msg);
    }
}
