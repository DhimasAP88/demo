package com.art.dhimas.artapplication.modul.home;

import com.art.dhimas.artapplication.component.network.gson.GListArtObject;

import rx.Observable;


public interface HomeInteractor {
    Observable<GListArtObject> getListObject();
}
