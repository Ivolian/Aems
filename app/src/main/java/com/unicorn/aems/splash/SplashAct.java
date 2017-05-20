package com.unicorn.aems.splash;

import android.Manifest;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

public class SplashAct extends BaseAct {

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        requestPermission();
        initAirports();
    }

    private void requestPermission() {
        Logger.d("请求权限");
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    Logger.d("请求权限" + (granted ? "成功" : "失败"));
                    if (!granted) {
                        ToastUtils.showLong("未授予应用权限");
                    }
                });
    }

    @Inject
    AirportService airportService;

    @Inject
    Navigator navigator;

    private void initAirports() {
        Logger.d("机场数据初始化");
        airportService.initAirports()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(airports -> {
                    boolean success = airports.size() != 0;
                    Logger.d("机场数据初始化" + (success ? "成功" : "失败"));
                    navigator.navigateTo(RoutePath.LOGIN);
                    finish();
                });
    }

}
