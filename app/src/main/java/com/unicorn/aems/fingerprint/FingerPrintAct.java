package com.unicorn.aems.fingerprint;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.unicorn.aems.R;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.fingerprint.ui.FingerWrapperView;
import com.unicorn.aems.login.LoginHelper;
import com.unicorn.aems.login.UserService;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;

import static android.animation.ValueAnimator.INFINITE;

@Route(path = RoutePath.FINGERPRINT)
public class FingerPrintAct extends BaseAct {

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Override
    protected int layoutResId() {
        return R.layout.act_fingerprint;
    }

    @BindColor(R.color.colorFingerprint)
    int colorFingerprint;

    @Override
    protected void init(Bundle savedInstanceState) {
        BarUtils.setColor(this, colorFingerprint, 50);
        startFingerWrapperAnim();
        startFingerprintAnim();
        initLoginHelper();
        if (savedInstanceState == null) {
            startIdentify();
        }
    }

    @BindView(R.id.fingerWrapperView)
    FingerWrapperView fingerWrapperView;

    private void startFingerWrapperAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 360);
        animator.setDuration(3000);
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

    @BindView(R.id.animationView)
    LottieAnimationView animationView;

    private void startFingerprintAnim() {
        // 设置颜色
        animationView.addColorFilter(new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN));
        animationView.setAnimation("fingerprint2.json");
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> animationView.setProgress((float) animation.getAnimatedValue()));
        animator.start();
    }

    @Inject
    UserService userService;

    LoginHelper loginHelper;

    @Inject
    Navigator navigator;

    private void initLoginHelper() {
        userService.getLoginInfo().subscribe(loginInfo -> loginHelper = new LoginHelper(loginInfo));
    }

    private void startIdentify() {
        FingerprintIdentify fingerprintIdentify = new FingerprintIdentify(this);
        fingerprintIdentify.startIdentify(3, new BaseFingerprint.FingerprintIdentifyListener() {
            @Override
            public void onSucceed() {
                ToastUtils.showLong("指纹验证成功");
                KProgressHUD mask = showMask().show();
                if (loginHelper != null) {
                    loginHelper.login(() -> navigator.navigateTo(RoutePath.MAIN, new NavCallback() {
                        @Override
                        public void onArrival(Postcard postcard) {
                            finish();
                            mask.dismiss();
                        }
                    }));
                }
            }

            @Override
            public void onNotMatch(int availableTimes) {
                ToastUtils.showLong("指纹验证失败, 还可尝试" + availableTimes + "次");
            }

            @Override
            public void onFailed() {
                ToastUtils.showLong("指纹验证失败");
                navigator.navigateTo(RoutePath.LOGIN, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        finish();
                    }
                });
            }
        });
    }

    private KProgressHUD showMask() {
      return
               KProgressHUD.create(this)
                      .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                      .setDimAmount(0.5f);

    }

}
