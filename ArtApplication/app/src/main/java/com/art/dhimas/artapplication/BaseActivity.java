package com.art.dhimas.artapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.art.dhimas.artapplication.component.dialog.CustomProgressBar;
import com.art.dhimas.artapplication.component.util.PreferenceManager;
import com.art.dhimas.artapplication.modul.home.HomePageActivity;
import com.art.dhimas.artapplication.modul.profile.ProfilePageActivity;
import com.jakewharton.rxbinding.view.RxView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import rx.functions.Action1;

public abstract class BaseActivity extends AppCompatActivity{
    protected Toolbar toolbar;
    protected ImageView backBtn;
    protected ImageView menuBtn;
    protected TextView toolbarTitle;
    protected SlidingMenu mSlidingMenu;
    protected RelativeLayout containerMenu;
    protected static CustomProgressBar progressBar = new CustomProgressBar();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        initComponent();
        setContentViewOnChild();
        onCreateAtChild();
    }

    protected abstract int getLayoutResourceId();

    protected abstract void setContentViewOnChild();

    protected abstract void onCreateAtChild();

    private void initComponent(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        backBtn = (ImageView) findViewById(R.id.hometoolbar_imgBtnBack);
        menuBtn = (ImageView) findViewById(R.id.hometoolbar_imgBtnMenu);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        containerMenu = (RelativeLayout) findViewById(R.id.container_menu);

        RxView.clicks(menuBtn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (mSlidingMenu != null) {
                    if (mSlidingMenu.isMenuShowing()) {
                        mSlidingMenu.toggle();
                    } else {
                        mSlidingMenu.showMenu(true);
                    }
                }
            }
        });
    }

    protected void setSlidingMenu(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mSlidingMenu = new SlidingMenu(context);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        mSlidingMenu.setShadowWidthRes(R.dimen.d2);
        mSlidingMenu.setFadeDegree(0.0f);
        mSlidingMenu.setBehindWidth(metrics.widthPixels / 3 + (metrics.widthPixels / 2));
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setMenu(R.layout.hamburger);
        mSlidingMenu.setShadowDrawable(R.drawable.sidemenu_shadow);

        LinearLayout home = mSlidingMenu.findViewById(R.id.home);
        LinearLayout profile = mSlidingMenu.findViewById(R.id.profile);
        TextView name = mSlidingMenu.findViewById(R.id.greeting);

        String[] user = PreferenceManager.getUserLogin();
        if (!TextUtils.isEmpty(user[0])) {
            name.setText("Welcome, " + user[0]);
        }

        RxView.clicks(home).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mSlidingMenu.toggle();
                pushHomePage();
            }
        });
        RxView.clicks(profile).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mSlidingMenu.toggle();
                pushProfilePage();
            }
        });
    }

    private void pushHomePage() {
        startActivity(new Intent(this, HomePageActivity.class));
    }

    private void pushProfilePage() {
        startActivity(new Intent(this, ProfilePageActivity.class));
    }

}
