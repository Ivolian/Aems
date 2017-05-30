package com.unicorn.aems.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.StringUtils;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.orhanobut.logger.Logger;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.constant.RxBusTag;
import com.unicorn.aems.login.entity.LoginInfo;
import com.unicorn.aems.login.ui.LoginButton;
import com.unicorn.aems.login.ui.UnderLineLinearLayout;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

@Route(path = RoutePath.LOGIN)
public class LoginAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_login;
    }

    @Override
    protected boolean useRxBus() {
        return true;
    }

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        BarUtils.setColor(this, Color.WHITE, 50);

        // 好多事件啊 ！！！
        clickAirport();
        focusOrTextChangeAccountAndPwd();
        clickEye();
        clickClearAccountAndPwd();
        clickLogin();

        // ...
        getLoginInfo();
    }

    // ======================== 选择机场 ========================

    @BindView(R.id.llAirport)
    UnderLineLinearLayout llAirport;

    @Inject
    Navigator navigator;

    private void clickAirport() {
        RxView.clicks(llAirport)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> navigator.navigateTo(RoutePath.AIRPORT));
    }

    Airport airportSelected;

    @BindView(R.id.tvAirport)
    TextView tvAirport;

    @Subscribe(tags = {@Tag(RxBusTag.AIRPORT_SELECTED)})
    public void onAirportSelect(Airport airport) {
        airportSelected = airport;
        tvAirport.setText(airport.getName());
    }


    // ======================== 用户名 & 密码 ========================

    @BindView(R.id.llAccount)
    UnderLineLinearLayout llAccount;

    @BindView(R.id.etAccount)
    EditText etAccount;

    @BindView(R.id.llPwd)
    UnderLineLinearLayout llPwd;

    @BindView(R.id.etPwd)
    EditText etPwd;

    private void focusOrTextChangeAccountAndPwd() {
        focusOrTextChange(etAccount, llAccount, iivClearAccount);
        focusOrTextChange(etPwd, llPwd, iivClearPwd);
    }

    private void focusOrTextChange(EditText editText, UnderLineLinearLayout underLineLinearLayout, View clear) {
        RxView.focusChanges(editText).subscribe(hasFocus -> {
            underLineLinearLayout.onFocusChange(hasFocus);
            showClear(editText, clear);
        });
        RxTextView.afterTextChangeEvents(editText).subscribe(event -> {
            enableOrDisableLogin();
            showClear(editText, clear);
        });

        //
        RxView.touches(underLineLinearLayout)
                .map(MotionEvent::getAction)
                .filter(action -> action == MotionEvent.ACTION_DOWN)
                .subscribe(action -> editText.requestFocus());
    }

    private void showClear(EditText editText, View clear) {
        boolean showClear = editText.isFocused() && !TextUtils.isEmpty(editText.getText());
        clear.setVisibility(showClear ? View.VISIBLE : View.GONE);
    }


    // ======================== 密码可见 ========================

    @BindView(R.id.iivEye)
    IconicsImageView iivEye;

    private final int PWD_VISIBLE = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    private final int PWD_INVISIBLE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

    private void clickEye() {
        // 默认密码不可见
        hidePwd();
        RxView.clicks(iivEye).subscribe(aVoid -> {
            if (etPwd.getInputType() == PWD_VISIBLE) {
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


    // ======================== 清除输入 ========================

    @BindView(R.id.iivClearAccount)
    IconicsImageView iivClearAccount;

    @BindView(R.id.iivClearPwd)
    IconicsImageView iivClearPwd;

    private void clickClearAccountAndPwd() {
        clickClear(etAccount, iivClearAccount);
        clickClear(etPwd, iivClearPwd);
    }

    private void clickClear(EditText editText, View clear) {
        RxView.clicks(clear).subscribe(aVoid -> editText.setText(""));
    }


    // ======================== 登录按钮 ========================

    @BindView(R.id.btnLogin)
    LoginButton btnLogin;

    private void clickLogin() {
        RxView.clicks(btnLogin)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> login());
        // 默认不可点击
        btnLogin.disable();
    }

    private void enableOrDisableLogin() {
        if (!StringUtils.isEmpty(getAccount()) && !StringUtils.isEmpty(getPwd())) {
            btnLogin.enable();
        } else {
            btnLogin.disable();
        }
    }


    // ======================== 获取本地登录信息 ========================

    @Inject
    UserService userService;

    @Inject
    AirportService airportService;

    LoginInfo mLoginInfo;

    private void getLoginInfo() {
        userService.getLoginInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(loginInfo -> {
                    if (loginInfo != null) {
                        Logger.d("获取本地登录信息成功");
                        mLoginInfo = loginInfo;

                        // render loginInfo
                        onAirportSelect(mLoginInfo.getAirport());
                        etAccount.setText(mLoginInfo.getAccount());
                        etPwd.setText(mLoginInfo.getPwd());

                        // 事件不往下走了
                        return Observable.never();
                    } else {
                        Logger.d("无本地登录信息");

                        // 获取默认机场
                        return airportService.getDefaultAirport();
                    }
                })
                .filter(airport -> airport != null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAirportSelect);
    }


    // ======================== 登录 ========================

    private void login() {
        LoginHelper loginHelper = new LoginHelper(airportSelected, getAccount(), getPwd(),
                () -> navigator.navigateTo(RoutePath.MAIN, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }
        }));
        loginHelper.login();
    }

    private String getAccount() {
        return etAccount.getText().toString().trim();
    }

    private String getPwd() {
        return etPwd.getText().toString().trim();
    }


}
