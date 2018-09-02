package com.art.dhimas.artapplication.modul.home;

import com.art.dhimas.artapplication.BuildConfig;
import com.art.dhimas.artapplication.component.network.NetworkService;
import com.art.dhimas.artapplication.component.network.gson.GListArtObject;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeInteractorImpl implements HomeInteractor {
    NetworkService mService;

    public HomeInteractorImpl(NetworkService mService) {
        this.mService = mService;
    }

    @Override
    public Observable<GListArtObject> getListObject() {
        return mService.getListArt(BuildConfig.KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
