package com.art.dhimas.artapplication.modul.detail;

import com.art.dhimas.artapplication.component.network.NetworkManager;
import com.art.dhimas.artapplication.component.network.gson.GArtObject;
import com.art.dhimas.artapplication.component.network.gson.GContainerArtObject;
import com.art.dhimas.artapplication.modul.LoadingInterface;

import rx.Subscriber;

public class DetailPresenterImpl implements DetailPresenter {
    EventDetail eventDetail;
    LoadingInterface loadingInterface;
    private DetailInteractor mInteractor;

    public DetailPresenterImpl(EventDetail eventDetail, LoadingInterface loadingInterface) {
        this.eventDetail = eventDetail;
        this.loadingInterface = loadingInterface;
        mInteractor = new DetailInteractorImpl(NetworkManager.getInstance());
    }

    @Override
    public void getDetailArt(String objectId) {
        loadingInterface.showProgressLoading();
        mInteractor.getDetail(objectId).subscribe(new Subscriber<GContainerArtObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loadingInterface.hideProgressLoading();
                eventDetail.onError("Connection Error");
            }

            @Override
            public void onNext(GContainerArtObject objArt) {
                loadingInterface.hideProgressLoading();
                eventDetail.onSuccess(objArt.artObject);
            }
        });
    }

    public interface EventDetail{
        void onSuccess(GArtObject artObject);

        void onError(String msg);
    }
}
