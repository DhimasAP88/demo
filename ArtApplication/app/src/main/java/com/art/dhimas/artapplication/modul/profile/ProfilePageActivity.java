package com.art.dhimas.artapplication.modul.profile;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.art.dhimas.artapplication.BaseActivity;
import com.art.dhimas.artapplication.R;
import com.art.dhimas.artapplication.component.util.PreferenceManager;
import com.art.dhimas.artapplication.modul.login.LoginPageActivity;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;

public class ProfilePageActivity extends BaseActivity{
    private TextView username;
    private Button logout;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.profile;
    }

    @Override
    protected void setContentViewOnChild() {
        setSlidingMenu(this);
        username = (TextView) findViewById(R.id.username_profile);
        logout = (Button) findViewById(R.id.logout_btn);
    }

    @Override
    protected void onCreateAtChild() {
        String[] user = PreferenceManager.getUserLogin();
        if (!TextUtils.isEmpty(user[0])) {
            username.setText(user[0]);
        }

        RxView.clicks(logout).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                PreferenceManager.setIsLogin(false);
                pushLoginPage();
            }
        });
    }

    private void pushLoginPage() {
        Intent intent = new Intent(this, LoginPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
