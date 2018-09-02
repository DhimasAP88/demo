package com.art.dhimas.artapplication.modul.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.art.dhimas.artapplication.BaseActivity;
import com.art.dhimas.artapplication.R;
import com.art.dhimas.artapplication.component.util.MethodUtil;
import com.art.dhimas.artapplication.modul.home.HomePageActivity;
import com.art.dhimas.artapplication.modul.register.RegisterPageActivity;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;

public class LoginPageActivity extends BaseActivity implements LoginPresenterImpl.EventLogin {
    private LoginPresenter mPresenter;
    private EditText username;
    private EditText password;
    private Button submitBtn;
    private TextView registerTxt;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.login;
    }

    @Override
    protected void setContentViewOnChild() {
        containerMenu.setVisibility(View.GONE);
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
        submitBtn = (Button) findViewById(R.id.login_btn);
        registerTxt = (TextView) findViewById(R.id.register_txt);
    }

    @Override
    protected void onCreateAtChild() {
        mPresenter = new LoginPresenterImpl(this, this);

        RxView.clicks(submitBtn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mPresenter.submitLogin(username.getText().toString(), password.getText().toString());
            }
        });

        RxView.clicks(registerTxt).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                pushToRegister();
            }
        });
    }

    @Override
    public void onSuccessLogin() {
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String msg) {
        MethodUtil.showCustomToast(this,msg, R.drawable.ic_error_login);
    }

    private void pushToRegister() {
        startActivity(new Intent(this, RegisterPageActivity.class));
    }
}
