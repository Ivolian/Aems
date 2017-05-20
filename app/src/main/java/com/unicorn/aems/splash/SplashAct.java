package com.unicorn.aems.splash;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 依赖注入
        AppComponentProvider.provide().inject(this);
        // 初始化工作
        init();
    }

    @Inject
    AirportService airportService;

    @Inject
    Navigator navigator;

    private void init() {
        new RxPermissions(this)
                .request(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .filter(granted -> granted)
                .flatMap(granted -> airportService.network())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(airports -> navigator.navigateTo(RoutePath.LOGIN));
    }


}
