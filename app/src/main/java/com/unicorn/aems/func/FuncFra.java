package com.unicorn.aems.func;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.unicorn.aems.R;
import com.unicorn.aems.app.App;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseFra;

import butterknife.BindView;

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

    @BindView(R.id.tab)
    SlidingTabLayout tab;



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        swipeRefreshLayout.setRefreshing(true);

        viewPager.setAdapter(new FuncPagerAdapter(getChildFragmentManager(),App.global.menus));
        tab.setViewPager(viewPager);
    }





}
