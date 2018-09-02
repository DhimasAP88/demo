package com.art.dhimas.artapplication.modul.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.art.dhimas.artapplication.BaseActivity;
import com.art.dhimas.artapplication.R;
import com.art.dhimas.artapplication.component.adapter.RecyListAdapter;
import com.art.dhimas.artapplication.component.network.gson.GArtObject;
import com.art.dhimas.artapplication.component.util.MethodUtil;
import com.art.dhimas.artapplication.modul.LoadingInterface;
import com.art.dhimas.artapplication.modul.detail.DetailPageActivity;

import java.util.List;

public class HomePageActivity extends BaseActivity implements HomePresenterImpl.EventHome, RecyListAdapter.OnClickItem, LoadingInterface {
    private RecyclerView recyclerView;
    private HomePresenter mPresenter;
    private RecyListAdapter mAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.home;
    }

    @Override
    protected void setContentViewOnChild() {
        setSlidingMenu(this);
        mAdapter = new RecyListAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.list_art_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onCreateAtChild() {
        mPresenter = new HomePresenterImpl(this, this);
        mPresenter.getListArt();
    }

    @Override
    public void onSuccess(List<GArtObject> listArt) {
        mAdapter.setData(listArt);
    }

    @Override
    public void onError(String msg) {
        MethodUtil.showCustomToast(this, msg, R.drawable.ic_error_login);
    }

    @Override
    public void onClick(String title, String imgUrl) {
        Intent intent = new Intent(this, DetailPageActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("image", imgUrl);
        startActivity(intent);
    }

    @Override
    public void showProgressLoading() {
        progressBar.show(this, false, null);
    }

    @Override
    public void hideProgressLoading() {
        progressBar.getDialog().dismiss();
    }
}
