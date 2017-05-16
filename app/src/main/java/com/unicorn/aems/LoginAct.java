package com.unicorn.aems;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.unicorn.Constant;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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

//        llAccount.setFocusable(true);
//        llAccount.setFocusableInTouchMode(true);
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
//        llPwd.setFocusable(true);
//        llPwd.setFocusableInTouchMode(true);
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

    @OnClick(R.id.iivEye)
    public void eyeOnClick() {
        boolean visible = etPwd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        if (visible) {
            etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    @BindView(R.id.iivEye)
    IconicsImageView iivEye;

    private void w() {
        int showPassword = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        int hidePassword = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        etPwd.setInputType(hidePassword);
    }


    /**
     * 清除密码
     */
    @BindView(R.id.iivClearPwd)
    IconicsImageView iivClearPwd;

    private void addClearPwdFunc() {
        RxTextView.afterTextChangeEvents(etPwd)
                .subscribe(event -> {
                    String text = event.editable().toString();
                    boolean empty = TextUtils.isEmpty(text);
                    iivClearPwd.setVisibility(empty ? View.INVISIBLE : View.VISIBLE);
                });
        RxView.clicks(iivClearPwd)
                .throttleFirst(Constant.DEFAULT_WINDOW_DURATION, TimeUnit.SECONDS)
                .subscribe(aVoid -> etPwd.setText(""));
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
