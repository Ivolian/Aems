package com.unicorn.aems.login.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.blankj.utilcode.util.ConvertUtils;
import com.unicorn.aems.R;

import butterknife.BindColor;
import butterknife.ButterKnife;

public class LoginButton extends AppCompatTextView {

    public LoginButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        // cope text
        setText("登录");
        setGravity(Gravity.CENTER);
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        setTextColor(Color.WHITE);

        // cope bg
        GradientDrawable pressed = new GradientDrawable();
        pressed.setCornerRadius(ConvertUtils.dp2px(3));
        pressed.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        GradientDrawable unpressed = new GradientDrawable();
        unpressed.setCornerRadius(ConvertUtils.dp2px(3));
        unpressed.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, unpressed);
        setBackground(stateListDrawable);
    }

    public void enable() {
        setTextColor(Color.WHITE);
        setClickable(true);
    }

    @BindColor(R.color.colorAccent)
    int colorAccent;

    public void disable() {
        setTextColor(colorAccent);
        setClickable(false);
    }

}
