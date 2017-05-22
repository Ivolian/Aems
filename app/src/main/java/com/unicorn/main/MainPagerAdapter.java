package com.unicorn.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.unicorn.aems.SimpleFra;

class MainPagerAdapter extends FragmentPagerAdapter {

    MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SimpleFra();
    }

    @Override
    public int getCount() {
        return 4;
    }

}
