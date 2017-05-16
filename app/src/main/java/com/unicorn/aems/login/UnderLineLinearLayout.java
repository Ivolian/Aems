package com.unicorn.aems.login;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.unicorn.aems.R;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.utils.DensityUtils;

import javax.inject.Inject;

public class UnderLineLinearLayout extends LinearLayout {

    public UnderLineLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        AppComponentProvider.provide().inject(this);
        setWillNotDraw(false);
        initPaint();
    }

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int colorFocused = ContextCompat.getColor(getContext(), R.color.colorPrimary);
    private int colorUnFocused = ContextCompat.getColor(getContext(), R.color.md_grey_300);

    @Inject
    DensityUtils densityUtils;

    private void initPaint() {
        paint.setStrokeWidth(densityUtils.dp2px(2f));
        paint.setColor(colorUnFocused);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnderLine(canvas);
    }

    private void drawUnderLine(Canvas canvas) {
        canvas.drawLine(0, getHeight(), getWidth(), getHeight(), paint);
    }

    public void focus() {
        paint.setColor(colorFocused);
        invalidate();
    }

    public void unFocus() {
        paint.setColor(colorUnFocused);
        invalidate();
    }

    public void changeFocus(boolean hasFocus) {
        if (hasFocus) {
            focus();
        } else {
            unFocus();
        }
    }

}
