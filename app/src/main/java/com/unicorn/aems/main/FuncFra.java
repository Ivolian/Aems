package com.unicorn.aems.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.unicorn.aems.R;
import com.unicorn.aems.app.App;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseFra;
import com.unicorn.aems.login.entity.SessionInfo;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FuncFra extends BaseFra {
    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Override
    protected int layoutResId() {
        return R.layout.act_login;
    }

    @Override
    protected void init() {
//        initSwipeRefreshLayout();
//        initRecycleView();
    }

    @Inject
    MenuService menuService;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        swipeRefreshLayout.setRefreshing(true);

        getMenu();
    }

    @Inject
    MenuProvider menuProvider;

    private void getMenu() {
        SessionInfo sessionInfo = App.getSessionInfo();
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
                        List<Menu> menus = menuProvider.provide();
                        ToastUtils.showLong("err");
                    }

                    @Override
                    public void onNext(List<Menu> menus) {

                    }
                });

    }



}
