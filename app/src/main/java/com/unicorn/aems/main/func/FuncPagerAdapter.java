package com.unicorn.aems.main.func;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.unicorn.aems.constant.Key;
import com.unicorn.aems.menu.Menu;
import com.unicorn.aems.main.SimpleFra;

import java.util.List;

public class FuncPagerAdapter extends FragmentPagerAdapter {

    private List<Menu> menus;

    public FuncPagerAdapter(FragmentManager fm, List<Menu> menus) {
        super(fm);
        this.menus = menus;
    }

    @Override
    public Fragment getItem(int position) {
        SimpleFra simpleFra = new SimpleFra();
        Bundle bundle = new Bundle();
        bundle.putString(Key.TEXT, menus.get(position).getName());
        simpleFra.setArguments(bundle);
        return simpleFra;
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
