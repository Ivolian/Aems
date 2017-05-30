package com.unicorn.aems.main.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoTouchViewPager extends android.support.v4.view.ViewPager {

    public NoTouchViewPager(Context context) {
        super(context);
    }

    public NoTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

}
