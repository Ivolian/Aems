package com.unicorn.aems.func;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.unicorn.aems.app.App;
import com.unicorn.aems.constant.Key;
import com.unicorn.aems.func.menu.MenuFra;
import com.unicorn.aems.func.menu.Menu;

import java.util.List;

public class FuncPagerAdapter extends FragmentPagerAdapter {

    private List<Menu> menus;

    public FuncPagerAdapter(FragmentManager fm) {
        super(fm);
        this.menus = App.global.menus;
    }

    @Override
    public Fragment getItem(int position) {
        return createMenuFra(position);
    }

    private Fragment createMenuFra(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Key.MENUS, menus.get(position).getChildList());
        Fragment fra = new MenuFra();
        fra.setArguments(bundle);
        return fra;
    }


    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menus.get(position).getName();
    }

}
