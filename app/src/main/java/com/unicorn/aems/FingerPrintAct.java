package com.unicorn.aems;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.navigate.RoutePath;

import butterknife.BindView;

import static android.animation.ValueAnimator.INFINITE;


@Route(path = RoutePath.FINGERPRINT)
public class FingerPrintAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_fingerprint;
    }


    @BindView(R.id.fingerView)
    FingerView fingerView;

@BindView(R.id.lottieAnimationView)
    LottieAnimationView animationView;

    @Override
    protected void init(Bundle savedInstanceState) {
       ValueAnimator valueAnimator= ValueAnimator.ofInt(0, 360);
               valueAnimator .setDuration(5000);
        valueAnimator.setRepeatCount(INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                valueAnimator.addUpdateListener(animation -> {
                    fingerView.setStartDegree((int) animation.getAnimatedValue());
//                    fingerView.setStartDegree2((int) animation.getAnimatedValue() + 30);
                });
        valueAnimator.start();

        ValueAnimator valueAnimator2= ValueAnimator.ofInt(0, 360);
        valueAnimator2 .setDuration(8000);
        valueAnimator2.setRepeatCount(INFINITE);
        valueAnimator2.setInterpolator(new LinearInterpolator());
        valueAnimator2.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator2.addUpdateListener(animation -> {
//            fingerView.setStartDegree((int) animation.getAnimatedValue());
            fingerView.setStartDegree2((int) animation.getAnimatedValue() + 30);
        });
        valueAnimator2.start();
        // Any class that conforms to the ColorFilter interface
        final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);

// Adding a color filter to the whole view
        animationView.addColorFilter(colorFilter);
        animationView.setScale(10);
        animationView.setAnimation("fingerprint2.json");
        animationView.loop(true);
        animationView.playAnimation();
    }
}
