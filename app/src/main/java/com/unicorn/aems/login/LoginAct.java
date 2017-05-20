package com.unicorn.aems.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.StringUtils;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.orhanobut.logger.Logger;
import com.unicorn.RxBusTag;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.login.entity.LoginInfo;
import com.unicorn.aems.login.entity.SessionInfo;
import com.unicorn.aems.login.entity.UserInfo;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;
import com.unicorn.aems.push.PushUtils;
import com.unicorn.aems.user.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
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


    /**
     * 选择机场
     */
    @BindView(R.id.llAirport)
    UnderLineLinearLayout llAirport;

    Airport airportSelected;

    @Inject
    Navigator navigator;

    private void clickAirport() {
        RxView.clicks(llAirport)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> navigator.navigateTo(RoutePath.AIRPORT));
    }

    @BindView(R.id.tvAirportName)
    TextView tvAirportName;

    @Subscribe(tags = {@Tag(RxBusTag.AIRPORT_SELECTED)})
    public void airportOnSelected(Airport airport) {
        airportSelected = airport;
        tvAirportName.setText(airport.getName());
    }

    /**
     * 用户名密码
     */
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
    }

    private void showClear(EditText editText, View clear) {
        boolean showClear = editText.isFocused() && !TextUtils.isEmpty(editText.getText());
        clear.setVisibility(showClear ? View.VISIBLE : View.GONE);
    }


    /**
     * 密码可见
     */
    @BindView(R.id.iivEye)
    IconicsImageView iivEye;

    private final int PWD_VISIBLE = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    private final int PWD_INVISIBLE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

    private void clickEye() {
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

    /**
     * 清除输入
     */
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


    /**
     * clickLogin
     */
    @BindView(R.id.btnLogin)
    LoginButton btnLogin;

    private void clickLogin() {
        RxView.clicks(btnLogin)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> login());
        btnLogin.disable();
    }

    private void enableOrDisableLogin() {
        if (!StringUtils.isEmpty(getAccount()) && !StringUtils.isEmpty(getPwd())) {
            btnLogin.enable();
        } else {
            btnLogin.disable();
        }
    }

    @Inject
    UserService userService;

    @Inject
    AirportService airportService;

    private void getLoginInfo() {
        Logger.d("获取本地登录信息");
        userService.getLoginInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(loginInfo -> {
                    boolean success = (loginInfo != null);
                    Logger.d("获取本地登录信息" + (success ? "成功" : "失败"));
                    if (success) {
                        this.loginInfo = loginInfo;
                        renderLoginInfo();
                        return Observable.never();
                    } else {
                        return airportService.defaultAirport();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(airport -> airport != null)
                .subscribe(airport -> {
                    airportSelected = airport;
                    tvAirportName.setText(airport.getName());
                });
    }

    private void renderLoginInfo() {
        tvAirportName.setText(loginInfo.getAirport().getName());
        etAccount.setText(loginInfo.getAccount());
        etPwd.setText(loginInfo.getPwd());
    }


    /**
     * login
     */

    @Inject
    PushUtils pushUtils;

    @Inject
    LoginService loginService;

    LoginInfo loginInfo;

    SessionInfo sessionInfo;

    private void login() {
        Logger.d("登录");
        loginService.login(getAccount(), getPwd())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SessionInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("登录成功");
                        sessionInfo = createSessionInfo();
                        saveLoginInfo();
                        setTags();
                        // todo
                    }

                    @Override
                    public void onNext(SessionInfo sessionInfo) {

                    }
                });

//        KProgressHUD kProgressHUD = KProgressHUD.fetchData(this)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("登录中")
//                .setCancellable(true)
//                .setAnimationSpeed(2)
//                .setDimAmount(0.5f)
//                .show();


    }



    private void saveLoginInfo() {
        LoginInfo loginInfo = new LoginInfo(getAccount(), getPwd(), airportSelected.getId());
        userService.saveLoginInfo(loginInfo).subscribe(o -> Logger.d("保存登录信息成功"));
    }

    private void setTags() {
        Set<String> tags = new HashSet<>();
        tags.add("aems");
        String airportCode = loginInfo.getAirport().getCode();
        tags.add(airportCode);
        UserInfo userInfo = sessionInfo.getCurrentUser();
        tags.add(airportCode + idToTag(userInfo.getUserId()));
        tags.add(airportCode + idToTag(userInfo.getRoleId()));
        pushUtils.setTags(tags);
    }

    private String idToTag(String id) {
        return id.replace("-", "_");
    }

    private SessionInfo createSessionInfo() {
        UserInfo currentUser = new UserInfo();
        currentUser.setRoleId("");
        currentUser.setUserId("");
        currentUser.setUsername("");
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setCurrentUser(currentUser);
        sessionInfo.setJsessionid("");
        sessionInfo.setSuccess(true);
        return sessionInfo;
    }

    private String getAccount() {
        return etAccount.getText().toString().trim();
    }

    private String getPwd() {
        return etPwd.getText().toString().trim();
    }


}
