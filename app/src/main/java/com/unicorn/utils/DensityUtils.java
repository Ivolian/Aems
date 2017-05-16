package com.unicorn.utils;

import android.content.Context;

import javax.inject.Inject;

public class DensityUtils {

    private Context context;

    @Inject
    public DensityUtils(Context context) {
        this.context = context;
    }

    public int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}  