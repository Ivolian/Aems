package com.unicorn.aems.func;

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
    protected void init() {
        viewPager.setAdapter(new FuncPagerAdapter(getChildFragmentManager()));
        tab.setViewPager(viewPager);
    }

}
