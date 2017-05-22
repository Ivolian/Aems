package com.unicorn.aems.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.afollestad.materialdialogs.MaterialDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.ToastUtils;
import com.mtramin.rxfingerprint.RxFingerprint;
import com.orhanobut.logger.Logger;
import com.unicorn.aems.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;


public class FingerPrintUtils {

    public interface AuthenticateSuccessListener {
        void onAuthenticateSuccess();
    }

    private Context context;
    private AuthenticateSuccessListener authenticateSuccessListener;
    private Subscription subscription;

    public FingerPrintUtils(Context context,AuthenticateSuccessListener authenticateSuccessListener) {
        this.context = context;
        this.authenticateSuccessListener = authenticateSuccessListener;
    }

    public void authenticate() {
        showFingerDialog();
        subscription = RxFingerprint.authenticate(context).subscribe(fingerprintAuthenticationResult -> {
                    switch (fingerprintAuthenticationResult.getResult()) {
                        case FAILED:
                            ToastUtils.showShort("指纹验证失败");
                            break;
                        case HELP:
                            ToastUtils.showShort(fingerprintAuthenticationResult.getMessage());
                            break;
                        case AUTHENTICATED:
                            onAuthenticateSuccess();
                            break;
                    }
                },
                Logger::d
        );
    }

    private void onAuthenticateSuccess() {
        dialog.setTitle("指纹验证成功");
        animationView.setAnimation("finger.json");
        animationView.addAnimatorUpdateListener((animation) -> {
            if (animation.getAnimatedFraction() == 1.0f) {
                dialog.dismiss();
                authenticateSuccessListener.onAuthenticateSuccess();
            }
        });
        animationView.playAnimation();
    }

    private MaterialDialog dialog;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;

    @BindView(R.id.lottieAnimationView)
    LottieAnimationView animationView;

    private void showFingerDialog() {
        dialog = new MaterialDialog.Builder(context)
                .title("请验证指纹")
                .customView(R.layout.finger, false)
                .positiveText("取消")
                .dismissListener(dialog1 -> {
                    if (subscription != null) {
                        subscription.unsubscribe();
                    }
                })
                .cancelable(false).show();

        ButterKnife.bind(this, dialog);
        animationView.addColorFilter(new PorterDuffColorFilter(colorPrimary, PorterDuff.Mode.LIGHTEN));
    }

}
