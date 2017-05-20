package com.unicorn.aems.login;

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

    public Observable<LoginInfo> saveLoginInfo(LoginInfo loginInfo) {
        return loginInfoDao.rx().insertOrReplace(loginInfo);
    }

}
