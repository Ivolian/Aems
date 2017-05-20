package com.unicorn.aems.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.orhanobut.logger.Logger;
import com.unicorn.MenuService;
import com.unicorn.RxBusTag;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.respository.AirportRepository;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.login.entity.LoginInfo;
import com.unicorn.aems.login.entity.Menu;
import com.unicorn.aems.login.entity.SessionInfo;
import com.unicorn.aems.login.entity.UserInfo;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;
import com.unicorn.aems.push.PushUtils;
import com.unicorn.aems.user.UserService;
import com.unicorn.aems.utils.ToastUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        BarUtils.setColor(this, Color.WHITE,50);
        clicksAirport();
        focusOrTextChanges();
        clicksEye();
        clicksClear();

        getLoginInfo();

        RxView.clicks(btnLogin).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
                login();
        });
    }

    @Inject
    UserService userService;

    private void getLoginInfo() {
        Logger.d("获取登录信息");
        userService.getLoginInfo()
                .subscribe(loginInfo -> {
                    boolean success = (loginInfo != null);
                    Logger.d("获取登录信息" + (success ? "成功" : "失败"));
                    if (success) {
                        renderLoginInfo(loginInfo);
                    }
                });
    }

    private void renderLoginInfo(@NonNull LoginInfo loginInfo) {
        tvAirportName.setText(loginInfo.getAirport().getName());
        etAccount.setText(loginInfo.getAccount());
        etPwd.setText(loginInfo.getPwd());
    }

    /**
     * 选择机场
     */
    @BindView(R.id.llAirport)
    UnderLineLinearLayout llAirport;

    @Inject
    Navigator navigator;

    private void clicksAirport() {
        RxView.clicks(llAirport)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> navigator.navigateTo(RoutePath.AIRPORT));
    }

    @BindView(R.id.tvAirportName)
    TextView tvAirportName;

    @Subscribe(tags = {@Tag(RxBusTag.AIRPORT_SELECTED)})
    public void airportOnSelected(Airport airport) {
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

    private void focusOrTextChanges() {
        focusOrTextChange(etAccount, llAccount, iivClearAccount);
        focusOrTextChange(etPwd, llPwd, iivClearPwd);
    }

    private void focusOrTextChange(EditText editText, UnderLineLinearLayout underLineLinearLayout, View clear) {
        RxView.focusChanges(editText).subscribe(hasFocus -> {
            underLineLinearLayout.onFocusChange(hasFocus);
            showClear(editText, clear);
        });
        RxTextView.afterTextChangeEvents(editText).subscribe(event -> {
            showClear(editText, clear);
            enableOrDisableLogin();
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

    private void clicksEye() {
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
     * clickClear
     */
    @BindView(R.id.iivClearAccount)
    IconicsImageView iivClearAccount;

    @BindView(R.id.iivClearPwd)
    IconicsImageView iivClearPwd;

    private void clicksClear() {
        clickClear(etAccount, iivClearAccount);
        clickClear(etPwd, iivClearPwd);
    }

    private void clickClear(EditText editText, View clear) {
        RxView.clicks(clear).subscribe(aVoid -> editText.setText(""));
    }


    /**
     * enableOrDisableLogin
     */
    @BindView(R.id.btnLogin)
    LoginButton btnLogin;

    private void enableOrDisableLogin() {
        if (!TextUtils.isEmpty(etAccount.getText()) && !TextUtils.isEmpty(etPwd.getText())) {
//            btnLogin.enable();

        } else {
//            btnLogin.disable();
        }
    }

    /**
     * login
     */
    @Inject
    ToastUtils toastUtils;

    @Inject
    PushUtils pushUtils;

    @Inject
    LoginService loginService;

    @Inject
    MenuService menuService;

    private void getMenu(SessionInfo sessionInfo){
        String cookie = "JSESSIONID=" + sessionInfo.getJsessionid();
        String userId = sessionInfo.getCurrentUser().getUserId();
        menuService.getMenu(cookie,userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Menu>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                toastUtils.show(e.toString());
                    }

                    @Override
                    public void onNext(List<Menu> menus) {
                        toastUtils.show("");

                    }
                });

    }

    private SessionInfo generateSessionInfo() {
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

    private void login() {


        loginService.login("admin","123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SessionInfo>() {
                    @Override
                    public void onCompleted() {

                        toastUtils.show("com");


                    }

                    @Override
                    public void onError(Throwable e) {
                        SessionInfo sessionInfo = generateSessionInfo();
                        onLoginSuccess(sessionInfo);
                    }

                    @Override
                    public void onNext(SessionInfo sessionInfo) {
//                        getMenu(sessionInfo);
//                        Object currentUser =map.list("currentUser");
//                        Map m  = (Map)currentUser;8
                    }
                });

//        KProgressHUD kProgressHUD = KProgressHUD.fetchData(this)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("登录中")
//                .setCancellable(true)
//                .setAnimationSpeed(2)
//                .setDimAmount(0.5f)
//                .show();
//        Set<String> tags = new HashSet<>();
//        tags.add(etAccount.getText().toString().trim());

//        Observable.just("").delay(1, TimeUnit.SECONDS).subscribe(ss -> {
//            pushUtils.setTags(tags, (i, s, set) -> {
//                kProgressHUD.dismiss();
//                if (i == 0) {
//                    toastUtils.show("登录成功");
//                    saveLoginInfo();
//                } else {
//                    toastUtils.show("登录失败，错误码:" + i);
//                }
//            });
//        });

    }

    @Inject
    AirportRepository airportRepository;

    private void onLoginSuccess(SessionInfo sessionInfo) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAccount(etAccount.getText().toString().trim());
        loginInfo.setAccount(etPwd.getText().toString().trim());
        String airportName = tvAirportName.getText().toString().trim();
        airportRepository.uniqueByName(airportName)
                .subscribe(airport -> {
                    loginInfo.setAirport(airport);
                    userService.saveLoginInfo(loginInfo);
                });
    }





}
