package com.art.dhimas.artapplication.modul.home;

import com.art.dhimas.artapplication.component.network.NetworkManager;
import com.art.dhimas.artapplication.component.network.gson.GArtObject;
import com.art.dhimas.artapplication.component.network.gson.GListArtObject;
import com.art.dhimas.artapplication.modul.LoadingInterface;

import java.util.List;

import rx.Subscriber;

public class HomePresenterImpl implements HomePresenter {
    private EventHome eventHome;
    private HomeInteractor mInteractor;
    private LoadingInterface loading;

    public HomePresenterImpl(EventHome eventHome, LoadingInterface loading) {
        this.eventHome = eventHome;
        this.loading = loading;
        mInteractor = new HomeInteractorImpl(NetworkManager.getInstance());
    }

    @Override
    public void getListArt() {
        loading.showProgressLoading();
        mInteractor.getListObject().subscribe(new Subscriber<GListArtObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loading.hideProgressLoading();
                eventHome.onError("Connection Error");
            }

            @Override
            public void onNext(GListArtObject gListArtObject) {
                loading.hideProgressLoading();
                eventHome.onSuccess(gListArtObject.artObjects);
            }
        });
    }

    public interface EventHome{
        void onSuccess(List<GArtObject> listArt);

        void onError(String msg);
    }
}
