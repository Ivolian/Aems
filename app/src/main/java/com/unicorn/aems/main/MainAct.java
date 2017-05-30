package com.unicorn.aems.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.navigate.RoutePath;

import butterknife.BindColor;
import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView2;

@Route(path = RoutePath.MAIN)
public class MainAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initViewPager();
        initTab();
    }

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private void initViewPager() {
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    }

    @BindView(R.id.tab)
    PageBottomTabLayout tab;

    private void initTab() {
        NavigationController navigationController = tab.custom()
                .addItem(newItem(Ionicons.Icon.ion_ios_pie, "功能"))
                .addItem(newItem(Ionicons.Icon.ion_ios_pulse_strong, "策略"))
                .addItem(newItem(Ionicons.Icon.ion_ios_person, "个人"))
                .build();
        navigationController.setupWithViewPager(viewPager);
    }

    @BindColor(R.color.md_grey_300)
    int defaultColor;

    @BindColor(R.color.colorPrimary)
    int checkedColor;

    private BaseTabItem newItem(IIcon icon, String text) {
        return newItem(icon, icon, text);
    }

    private BaseTabItem newItem(IIcon icon, IIcon checkedIcon, String title) {
        NormalItemView2 item = new NormalItemView2(this);
        item.initialize(newIconDrawable(icon, defaultColor), newIconDrawable(checkedIcon, checkedColor), title);
        item.setTextDefaultColor(defaultColor);
        item.setTextCheckedColor(checkedColor);
        return item;
    }

    private Drawable newIconDrawable(IIcon icon, int color) {
        return new IconicsDrawable(this)
                .icon(icon)
                .color(color);
    }

}
