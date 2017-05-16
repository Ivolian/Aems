package com.unicorn.utils;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import com.unicorn.aems.app.dagger.App;

import javax.inject.Inject;

@App
public class ColorUtils {

    private Context context;

    @Inject
    public ColorUtils(Context context) {
        this.context = context;
    }

    public
    @ColorInt
    int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(context,colorRes);
    }

}
