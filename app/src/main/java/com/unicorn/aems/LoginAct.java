package com.unicorn.aems;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * The type Login act.
 */
public class LoginAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppComponentProvider.provide().inject(this);
        StatusBarUtil.setColor(this, Color.WHITE, 50);
        s();
        addShowHidePwdFunc();
        addClearPwdFunc();
    }

    @BindView(R.id.llAccount)
    UnderLineLinearLayout llAccount;

    @BindView(R.id.etAccount)
    EditText etAccount;

    @BindView(R.id.llPwd)
    UnderLineLinearLayout llPwd;

    @BindView(R.id.etPwd)
    EditText etPwd;


    private void s() {

        llAccount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
//                        etAccount.requestFocus();
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        etAccount.requestFocus();

//                        llAccount.focus();
//                        llAccount.requestFocus();
                        return true;
                }
                return false;
            }
        });
        llPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
//                        llPwd.unFocus();
                        return true;
                    case MotionEvent.ACTION_DOWN:
//                        llPwd.focus();
                        etPwd.requestFocus();
                        return true;
                }
                return false;
            }
        });
        etAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    llAccount.focus();
                } else {
                    llAccount.unFocus();
                }
            }
        });

        etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    llPwd.focus();
                } else {
                    llPwd.unFocus();
                }
            }
        });
    }

    @Inject
    ToastUtils toastUtils;


    /**
     * 密码可见
     */
    @BindView(R.id.iivEye)
    IconicsImageView iivEye;

    private final int PWD_VISIBLE = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    private final int PWD_INVISIBLE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

    private void addShowHidePwdFunc() {
        hidePwd();
        RxView.clicks(iivEye).subscribe(aVoid -> {
            boolean visible = etPwd.getInputType() == PWD_VISIBLE;
            if (visible) {
                hidePwd();
            } else {
                showPwd();
            }
            // 移动光标到末尾
            etPwd.setSelection(etPwd.getText().length());
        });
    }

    private void showPwd() {
        iivEye.setIcon(Ionicons.Icon.ion_eye);
        iivEye.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        etPwd.setInputType(PWD_VISIBLE);
    }

    private void hidePwd() {
        iivEye.setIcon(Ionicons.Icon.ion_eye_disabled);
        iivEye.setColor(ContextCompat.getColor(this, R.color.md_grey_300));
        etPwd.setInputType(PWD_INVISIBLE);
    }


    /**
     * 清除密码
     */
    @BindView(R.id.iivClearPwd)
    IconicsImageView iivClearPwd;

    @SuppressWarnings("ConstantConditions")
    private void addClearPwdFunc() {
        RxTextView.afterTextChangeEvents(etPwd)
                .subscribe(event -> {
                    String text = event.editable().toString();
                    boolean empty = TextUtils.isEmpty(text);
                    iivClearPwd.setVisibility(empty ? View.INVISIBLE : View.VISIBLE);
                });
        RxView.clicks(iivClearPwd).subscribe(aVoid -> etPwd.setText(""));
    }

//    @Inject
//    PushUtils pushUtils;
//
//    private void setTags() {
//        Set<String> tags = new HashSet<>();
//        tags.add("-----");
//        pushUtils.setTags(tags);
//    }

}
