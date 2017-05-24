package com.unicorn.aems.splash;

import android.Manifest;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.login.UserService;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

public class SplashAct extends BaseAct {

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Inject
    AirportService airportService;

    @Inject
    UserService userService;

    @Inject
    Navigator navigator;

    @Override
    protected void init(Bundle savedInstanceState) {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .doOnNext(granted -> Logger.d("请求权限" + (granted ? "成功" : "失败")))
                .flatMap(granted -> {
                    if (!granted) {
                        ToastUtils.showLong("未授予应用权限");
                    }
                    return airportService.initAirportData();
                })
                .doOnNext(airports -> Logger.d("机场数据初始化数量:" + airports.size()))
                .flatMap(airports -> userService.getLoginInfo())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginInfo -> {
                            String routePath = RoutePath.LOGIN;
                            FingerprintIdentify identify = new FingerprintIdentify(this);
                            if (identify.isFingerprintEnable() && loginInfo != null) {
                                routePath = RoutePath.FINGERPRINT;
                            }
                            navigator.navigateTo(routePath, new NavCallback() {
                                @Override
                                public void onArrival(Postcard postcard) {
                                    finish();
                                }
                            });
                        }
                );
    }

}
