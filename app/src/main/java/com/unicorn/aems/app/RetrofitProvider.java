package com.unicorn.aems.app;


import com.unicorn.aems.base.Provider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@com.unicorn.aems.app.dagger.App
public class RetrofitProvider implements Provider<Retrofit> {

    private final String BASE_REQUEST_URL;

    @Inject
    RetrofitProvider() {
        BASE_REQUEST_URL = "http://192.168.7.71:8008/aems/";
    }

    @Override
    public Retrofit provide() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
//            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        OkHttpClient okHttpClient = okHttpClientBuilder
                .connectTimeout(1, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .client(okHttpClient)

                .baseUrl(BASE_REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
