package com.unicorn.aems.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.unicorn.aems.constant.Key;
import com.unicorn.aems.main.func.FuncFra;

public class MainPagerAdapter extends FragmentPagerAdapter {

    MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        SimpleFra simpleFra = new SimpleFra();
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                return new FuncFra();
            case 1:
                bundle.putString(Key.TEXT, "策略");
                simpleFra.setArguments(bundle);
                return simpleFra;
            case 2:
                bundle.putString(Key.TEXT, "个人");
                simpleFra.setArguments(bundle);
                return simpleFra;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

}
