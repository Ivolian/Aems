package com.unicorn.aems.login;

import android.support.annotation.NonNull;

import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.app.dagger.App;
import com.unicorn.aems.login.entity.LoginInfo;
import com.unicorn.aems.login.entity.LoginInfoDao;

import javax.inject.Inject;

import rx.Observable;

@App
public class UserService {

    private LoginInfoDao loginInfoDao;

    @Inject
    public UserService(LoginInfoDao loginInfoDao) {
        this.loginInfoDao = loginInfoDao;
    }

    public Observable<LoginInfo> getLoginInfo() {
        return loginInfoDao.queryBuilder().rx().unique();
    }

    public Observable<LoginInfo> saveLoginInfo(String account, String pwd, @NonNull Airport airport) {
        LoginInfo loginInfo = new LoginInfo(account,pwd,airport.getId());
        return loginInfoDao.rx().insertOrReplace(loginInfo);
    }

}
