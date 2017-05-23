package com.unicorn.aems.splash;

import android.Manifest;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.login.UserService;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;

import javax.inject.Inject;

public class SplashAct extends BaseAct {

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        requestPermission();
    }

    private void requestPermission() {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (!granted) {
                        ToastUtils.showLong("未授予应用权限");
                    }
                    initAirportData();
                    Logger.d("请求权限" + (granted ? "成功" : "失败"));
                });
    }


    // ======================== initAirportData ========================

    @Inject
    AirportService airportService;

    @Inject
    UserService userService;

    @Inject
    Navigator navigator;

    private void initAirportData() {
        airportService.initAirportData()
                .doOnNext(airports -> Logger.d("机场数据初始化数量:" + airports.size()))
                .flatMap(airports -> userService.getLoginInfo())
                .subscribe(loginInfo -> navigator.navigateTo(loginInfo != null ? RoutePath.FINGERPRINT : RoutePath.LOGIN));
    }

}
