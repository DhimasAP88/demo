package com.art.dhimas.artapplication.modul.detail;

import com.art.dhimas.artapplication.BuildConfig;
import com.art.dhimas.artapplication.component.network.NetworkService;
import com.art.dhimas.artapplication.component.network.gson.GArtObject;
import com.art.dhimas.artapplication.component.network.gson.GContainerArtObject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailInteractorImpl implements DetailInteractor {
    NetworkService mService;

    public DetailInteractorImpl(NetworkService mService) {
        this.mService = mService;
    }

    @Override
    public Observable<GContainerArtObject> getDetail(String objectId) {
        return mService.getDetailArt(objectId, BuildConfig.KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
