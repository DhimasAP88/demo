package com.art.dhimas.artapplication.modul.register;

import android.app.Activity;

import com.art.dhimas.artapplication.component.util.MethodUtil;
import com.art.dhimas.artapplication.component.util.PreferenceManager;

public class RegisterPresenterImpl implements RegisterPresenter {
    private Activity activity;
    private EventRegister eventRegister;

    public RegisterPresenterImpl(EventRegister eventRegister, Activity activity) {
        this.eventRegister = eventRegister;
        this.activity = activity;
    }

    @Override
    public void submitRegister(String username, String password) {
        if (MethodUtil.validateLogin(username, password, activity)) {
            PreferenceManager.setUserLogin(username, password);
            eventRegister.onSuccessRegister();
        } else {
            eventRegister.onError("Authentication failed");
        }
    }

    public interface EventRegister{
        void onSuccessRegister();

        void onError(String msg);
    }
}
