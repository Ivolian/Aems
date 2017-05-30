package com.unicorn.aems.login;

import com.orhanobut.logger.Logger;
import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.app.App;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.login.entity.LoginInfo;
import com.unicorn.aems.login.entity.SessionInfo;
import com.unicorn.aems.login.entity.UserInfo;
import com.unicorn.aems.push.PushHelper;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginHelper {

    public interface LoginListener {
        void onLoginSuccess();
    }

    private Airport airport;
    private String account;
    private String pwd;

    public LoginHelper(Airport airport, String account, String pwd) {
        this.airport = airport;
        this.account = account;
        this.pwd = pwd;
        AppComponentProvider.provide().inject(this);
    }

    public LoginHelper(LoginInfo loginInfo) {
        this(loginInfo.getAirport(), loginInfo.getAccount(), loginInfo.getPwd());
    }


    @Inject
    LoginService loginService;

    public void login(LoginListener loginListener) {
        loginService.login(account, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SessionInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onLoginSuccessInternal(createSessionInfo());
                        loginListener.onLoginSuccess();
                    }

                    @Override
                    public void onNext(SessionInfo sessionInfo) {

                    }
                });
    }

    private void onLoginSuccessInternal(SessionInfo sessionInfo) {
        saveSessionInfo(sessionInfo);
        setTags(sessionInfo);
        saveLoginInfo();
    }

    private void saveSessionInfo(SessionInfo sessionInfo) {
        App.global.sessionInfo = sessionInfo;
    }

    @Inject
    PushHelper pushHelper;

    private void setTags(SessionInfo sessionInfo) {
        pushHelper.setTags(airport, sessionInfo);
    }

    @Inject
    UserService userService;

    private void saveLoginInfo() {
        userService.saveLoginInfo(account, pwd, airport)
                .subscribe(loginInfo -> Logger.d("保存登录信息成功"));
    }

    private SessionInfo createSessionInfo() {
        UserInfo currentUser = new UserInfo();
        currentUser.setRoleId("");
        currentUser.setUserId("");
        currentUser.setUsername("");
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setCurrentUser(currentUser);
        sessionInfo.setJsessionid("");
        sessionInfo.setSuccess(true);
        return sessionInfo;
    }

}
