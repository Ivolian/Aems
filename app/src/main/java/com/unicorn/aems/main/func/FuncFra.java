package com.unicorn.aems.main.func;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.unicorn.aems.R;
import com.unicorn.aems.app.App;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseFra;
import com.unicorn.aems.login.entity.SessionInfo;
import com.unicorn.aems.main.Menu;
import com.unicorn.aems.main.MenuProvider;
import com.unicorn.aems.main.MenuService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
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
        return R.layout.fra_func;
    }

    @Override
    protected void init() {
//        initSwipeRefreshLayout();
//        initRecycleView();


    }

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.coordinatorTabLayout)
    CoordinatorTabLayout coordinatorTabLayout;

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

                        viewPager.setAdapter(new FuncPagerAdapter(getChildFragmentManager(),menus));
                        coordinatorTabLayout.setTitle("功能");
                        coordinatorTabLayout.setImageArray(new int[]{
                                R.drawable.i1,
                                R.drawable.i2,
                                R.drawable.i3,
                                R.drawable.i4,
                        });
                        coordinatorTabLayout.setupWithViewPager(viewPager);
                    }

                    @Override
                    public void onNext(List<Menu> menus) {

                    }
                });

    }



}
