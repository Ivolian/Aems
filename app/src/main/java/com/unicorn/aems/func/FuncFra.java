package com.unicorn.aems.func;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.unicorn.aems.R;
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

    @BindView(R.id.tab)
    SlidingTabLayout tab;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        viewPager.setAdapter(new FuncPagerAdapter(getChildFragmentManager()));
        tab.setViewPager(viewPager);
    }

}
