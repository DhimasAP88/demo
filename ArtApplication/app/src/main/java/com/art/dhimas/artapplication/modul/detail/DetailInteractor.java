package com.art.dhimas.artapplication.modul.detail;

import com.art.dhimas.artapplication.component.network.gson.GArtObject;
import com.art.dhimas.artapplication.component.network.gson.GContainerArtObject;

import rx.Observable;

public interface DetailInteractor {
    Observable<GContainerArtObject> getDetail(String objectId);
}
