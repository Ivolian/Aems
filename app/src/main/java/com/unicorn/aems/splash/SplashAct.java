package com.unicorn.aems.splash;

import android.Manifest;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindColor;
import rx.android.schedulers.AndroidSchedulers;

public class SplashAct extends BaseAct {

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Inject
    AirportService airportService;

    @Inject
    Navigator navigator;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;

    @Override
    protected void init(Bundle savedInstanceState) {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .doOnNext(granted -> {
                    if (granted) {
                         airportService.network();
                    } else {
                        ToastUtils.showLong("未授予应用权限");
                    }
                })

                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(airports -> navigator.navigateTo(RoutePath.LOGIN));
    }


}
