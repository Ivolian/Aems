package com.unicorn.main;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.navigate.RoutePath;

import butterknife.BindColor;
import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;

@Route(path = RoutePath.MAIN)
public class MainAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_main;
    }

//    @Override
//    protected boolean useRxBus() {
//        return true;
//    }



    @Override
    protected void init(Bundle savedInstanceState) {
        initViewPager();
        initMainTab();
    }


    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private void initViewPager() {
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    }

    @BindView(R.id.mainTab)
    PageBottomTabLayout mainTab;

    private void initMainTab() {

        NavigationController navigationController = mainTab.material()
                .addItem(android.R.drawable.ic_menu_camera, "相机")
                .addItem(android.R.drawable.ic_menu_compass, "位置")
                .addItem(android.R.drawable.ic_menu_search, "搜索")
                .addItem(android.R.drawable.ic_menu_help, "帮助")
                .build();
        navigationController.setupWithViewPager(viewPager);
//        setHasMessage(navigationController);
    }

    @BindColor(R.color.colorPrimary)
    int colorPrimary;

    @BindColor(R.color.md_grey_500)
    int mdGrey400;

    private BaseTabItem newItem(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes, String title) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawableRes, checkedDrawableRes, title);
        normalItemView.setTextDefaultColor(mdGrey400);
        normalItemView.setTextCheckedColor(colorPrimary);
        return normalItemView;
    }


}
