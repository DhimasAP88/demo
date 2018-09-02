package com.art.dhimas.artapplication.component.network;

import com.art.dhimas.artapplication.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    public static NetworkService instance;
    public static Retrofit retrofit;

    private static final int CONNECT_TIME_OUT = 300 * 1000;
    private static final int READ_TIME_OUT = 300 * 1000;

    public static synchronized NetworkService getInstance(){
        instance = null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS);
        httpClient.addNetworkInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("content-type", "application/json; charset=utf-8")
                        .header("cache-control", "no-cache")
                        .build();
                Response response = chain.proceed(request);

                return response;
            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        instance = retrofit.create(NetworkService.class);
        return instance;
    }
}
