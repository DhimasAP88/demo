package com.art.dhimas.artapplication.component.network;

import com.art.dhimas.artapplication.component.network.gson.GArtObject;
import com.art.dhimas.artapplication.component.network.gson.GContainerArtObject;
import com.art.dhimas.artapplication.component.network.gson.GListArtObject;
import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkService {

    @GET("/api/en/collection")
    Observable<GListArtObject> getListArt(@Query("key") String key);

    @GET("/api/en/collection/{object_id}")
    Observable<GContainerArtObject> getDetailArt(@Path("object_id") String objectId,
                                                 @Query("key") String key);
}
