package com.art.dhimas.artapplication.modul.register;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.art.dhimas.artapplication.BaseActivity;
import com.art.dhimas.artapplication.R;
import com.art.dhimas.artapplication.component.util.MethodUtil;
import com.art.dhimas.artapplication.modul.login.LoginPageActivity;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;

public class RegisterPageActivity extends BaseActivity implements RegisterPresenterImpl.EventRegister {
    private EditText username;
    private EditText password;
    private CheckBox checkBox;
    private Button submitBtn;
    private RegisterPresenter mPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.register;
    }

    @Override
    protected void setContentViewOnChild() {
        backBtn.setVisibility(View.VISIBLE);
        menuBtn.setVisibility(View.GONE);

        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
        checkBox = (CheckBox) findViewById(R.id.checkbox_register);
        submitBtn = (Button) findViewById(R.id.register_btn);

        RxView.clicks(backBtn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onCreateAtChild() {
        mPresenter = new RegisterPresenterImpl(this, this);

        RxView.clicks(submitBtn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (checkBox.isChecked()) {
                    mPresenter.submitRegister(username.getText().toString(), password.getText().toString());
                } else {
                    onError("Please check the checkbox");
                }
            }
        });
    }

    @Override
    public void onSuccessRegister() {
        Intent intent = new Intent(this, LoginPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String msg) {
        MethodUtil.showCustomToast(this, msg, R.drawable.ic_error_login);
    }
}
