package com.unicorn.aems.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.navigate.RoutePath;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;

@Route(path = RoutePath.MAIN)
public class MainAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initViewPager();
        initMainTab();
    }

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private void initViewPager() {
//        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    }

    @BindView(R.id.mainTab)
    PageBottomTabLayout mainTab;

    private void initMainTab() {
        NavigationController navigationController = mainTab.material()
                .addItem(android.R.drawable.ic_menu_camera, "首页")
                .addItem(android.R.drawable.ic_menu_compass, "功能")
                .addItem(android.R.drawable.ic_menu_search, "策略")
                .addItem(android.R.drawable.ic_menu_help, "更多")
                .build();
        navigationController.setupWithViewPager(viewPager);
    }

}
