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

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;


public class LoginAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppComponentProvider.provide().inject(this);
        StatusBarUtil.setColor(this, Color.WHITE, 50);
        doSomeFocusWork();
        addShowHidePwdFunc();
        addClearFunc();

    }

    @BindView(R.id.llAccount)
    UnderLineLinearLayout llAccount;

    @BindView(R.id.etAccount)
    EditText etAccount;

    @BindView(R.id.llPwd)
    UnderLineLinearLayout llPwd;

    @BindView(R.id.etPwd)
    EditText etPwd;

    private void doSomeFocusWork() {
        RxView.focusChanges(etAccount)
                .subscribe(hasFocus -> {
                    llAccount.changeFocus(hasFocus);
                    iivClearPwd.setVisibility(hasFocus ? View.INVISIBLE : View.VISIBLE);
                });
        RxView.touches(llAccount)
                .map(MotionEvent::getAction)
                .filter(action -> action == MotionEvent.ACTION_DOWN)
                .subscribe(action -> etAccount.requestFocus());
        RxView.focusChanges(etPwd)
                .subscribe(hasFocus -> {
                    llPwd.changeFocus(hasFocus);
                    iivClearAccount.setVisibility(hasFocus ? View.INVISIBLE : View.VISIBLE);
                });
        RxView.touches(llPwd)
                .map(MotionEvent::getAction)
                .filter(action -> action == MotionEvent.ACTION_DOWN)
                .subscribe(action -> etPwd.requestFocus());
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
     * 清除账户和密码
     */
    @BindView(R.id.iivClearAccount)
    IconicsImageView iivClearAccount;

    @BindView(R.id.iivClearPwd)
    IconicsImageView iivClearPwd;

    @SuppressWarnings("ConstantConditions")
    private void addClearFunc() {
        RxTextView.afterTextChangeEvents(etAccount)
                .subscribe(event -> {
                    String text = event.editable().toString();
                    boolean empty = TextUtils.isEmpty(text);
                    iivClearAccount.setVisibility(empty ? View.INVISIBLE : View.VISIBLE);
                    checkLoginBtnState();
                });
        RxView.clicks(iivClearAccount).subscribe(aVoid -> etAccount.setText(""));

        RxTextView.afterTextChangeEvents(etPwd)
                .subscribe(event -> {
                    String text = event.editable().toString();
                    boolean empty = TextUtils.isEmpty(text);
                    iivClearPwd.setVisibility(empty ? View.INVISIBLE : View.VISIBLE);
                    checkLoginBtnState();
                });
        RxView.clicks(iivClearPwd).subscribe(aVoid -> etPwd.setText(""));
    }

    @BindView(R.id.btnLogin)
    LoginButton btnLogin;

    private void checkLoginBtnState() {
        if (!TextUtils.isEmpty(etAccount.getText()) && !TextUtils.isEmpty(etPwd.getText())) {
            btnLogin.enable();
            RxView.clicks(btnLogin).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
                login();
            });
        } else {
            btnLogin.disable();
        }
    }

    private void login() {
        toastUtils.show("登录成功");
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
