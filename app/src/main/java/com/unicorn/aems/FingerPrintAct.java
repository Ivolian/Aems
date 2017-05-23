package com.unicorn.aems;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.navigate.RoutePath;

import butterknife.BindColor;
import butterknife.BindView;

import static android.animation.ValueAnimator.INFINITE;


@Route(path = RoutePath.FINGERPRINT)
public class FingerPrintAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_fingerprint;
    }

    @BindColor(R.color.fingerprint)
    int fingerprintColor;

    @Override
    protected void init(Bundle savedInstanceState) {
        BarUtils.setColor(this, fingerprintColor, 50);
        startFingerWrapperAnim();
        showFingerprintAnim();
    }

    @BindView(R.id.animationView)
    LottieAnimationView animationView;

    private void showFingerprintAnim() {
        // 设置颜色
        animationView.addColorFilter(new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN));
        animationView.setAnimation("fingerprint2.json");
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            animationView.setProgress((float) animation.getAnimatedValue());
            if (animation.getAnimatedFraction() == 1f) {
//                startFingerWrapperAnim();
            }
        });
        animator.start();
    }

    @BindView(R.id.fingerWrapperView)
    FingerWrapperView fingerWrapperView;

    private void startFingerWrapperAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(INFINITE);
        animator.addUpdateListener(animation -> fingerWrapperView.setOuterStartDegree((int) animation.getAnimatedValue()));
        animator.start();

        ValueAnimator animator2 = ValueAnimator.ofInt(0, 360);
        animator2.setDuration(10000);
        animator2.setInterpolator(new LinearInterpolator());
        animator2.setRepeatMode(ValueAnimator.RESTART);
        animator2.setRepeatCount(INFINITE);
        animator2.addUpdateListener(animation -> fingerWrapperView.setInnerStartDegree((int) animation.getAnimatedValue()));
        animator2.start();
    }

}
