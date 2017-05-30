package com.unicorn.aems.login;

import com.orhanobut.logger.Logger;
import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.app.App;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.login.entity.LoginInfo;
import com.unicorn.aems.login.entity.SessionInfo;
import com.unicorn.aems.login.entity.UserInfo;
import com.unicorn.aems.menu.Menu;
import com.unicorn.aems.menu.MenuProvider;
import com.unicorn.aems.menu.MenuService;
import com.unicorn.aems.push.PushHelper;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginHelper {

    private Airport airport;
    private String account;
    private String pwd;
    private LoginListener loginListener;

    public LoginHelper(Airport airport, String account, String pwd,LoginListener loginListener) {
        this.airport = airport;
        this.account = account;
        this.pwd = pwd;
        this.loginListener = loginListener;
        AppComponentProvider.provide().inject(this);
    }

    public LoginHelper(LoginInfo loginInfo,LoginListener loginListener) {
        this(loginInfo.getAirport(), loginInfo.getAccount(), loginInfo.getPwd(),loginListener);
    }

    @Inject
    LoginService loginService;

    public void login() {
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
                    }

                    @Override
                    public void onNext(SessionInfo sessionInfo) {

                    }
                });
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


    @Inject
    PushHelper pushHelper;

    private void onLoginSuccessInternal(SessionInfo sessionInfo) {
        // 保持 sessionInfo
        App.global.sessionInfo = sessionInfo;
        // 设置推送标签
        pushHelper.setTags(airport, sessionInfo);
        // 保持登录信息
        saveLoginInfo();
        // 获取用户菜单
        getMenus(sessionInfo);
    }

    @Inject
    UserService userService;

    private void saveLoginInfo() {
        userService.saveLoginInfo(account, pwd, airport)
                .subscribe(loginInfo -> Logger.d("保存登录信息成功"));
    }

    @Inject
    MenuProvider menuProvider;

    @Inject
    MenuService menuService;

    private void getMenus(SessionInfo sessionInfo) {
        String cookie = "JSESSIONID=" + sessionInfo.getJsessionid();
        String userId = sessionInfo.getCurrentUser().getUserId();
        menuService.getMenu(cookie, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Menu>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        App.global.menus = menuProvider.provide();
                        loginListener.onLoginSuccess();
                    }

                    @Override
                    public void onNext(List<Menu> menus) {

                    }
                });
    }


    public interface LoginListener {
        void onLoginSuccess();
    }

}
