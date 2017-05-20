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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.FileUtils;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jaeger.library.StatusBarUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mattprecious.swirl.SwirlView;
import com.mikepenz.iconics.view.IconicsImageView;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.orhanobut.logger.Logger;
import com.unicorn.Constant;
import com.unicorn.MenuService;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.app.App;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.finger.FingerPrinterView;
import com.unicorn.aems.login.entity.LoginInfo;
import com.unicorn.aems.login.entity.Menu;
import com.unicorn.aems.navigate.Navigator;
import com.unicorn.aems.navigate.RoutePath;
import com.unicorn.aems.push.PushUtils;
import com.unicorn.aems.utils.ToastUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zwh.com.lib.FPerException;
import zwh.com.lib.RxFingerPrinter;


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
        StatusBarUtil.setColor(this, Color.WHITE, 50);

        initLlAirport();
        copeAccountAndPwd();
        copeEye();
        copeClear();
        copeFinger();
        List<SwirlView.State> states = Arrays.asList(SwirlView.State.ON, SwirlView.State.ERROR, SwirlView.State.OFF);

        RxView.clicks(btnLogin).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
//            swirlView.setState(states.get(new Random().nextInt(3)));
                login();
        });
    }

    /**
     * 机场
     */
    @BindView(R.id.llAirport)
    UnderLineLinearLayout llAirport;

    @Inject
    Navigator navigator;

    private void initLlAirport() {
        RxView.clicks(llAirport)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> navigator.navigateTo(RoutePath.AIRPORT));
    }

    @BindView(R.id.tvAirport)
    TextView tvAirport;

    @Subscribe(tags = {@Tag(Constant.AIRPORT_SELECTED)})
    public void airportOnSelected(Airport airport) {
        tvAirport.setText(airport.getName());
    }


    @BindView(R.id.swirlView)
    SwirlView swirlView;

    @BindView(R.id.llAccount)
    UnderLineLinearLayout llAccount;

    @BindView(R.id.etAccount)
    EditText etAccount;

    @BindView(R.id.llPwd)
    UnderLineLinearLayout llPwd;

    @BindView(R.id.etPwd)
    EditText etPwd;

    private void copeAccountAndPwd() {
        RxView.focusChanges(etAccount).subscribe(hasFocus -> {
            llAccount.changeFocus(hasFocus);
            showOrHideClear(etAccount, iivClearAccount);
        });
        RxView.focusChanges(etPwd).subscribe(hasFocus -> {
            llPwd.changeFocus(hasFocus);
            showOrHideClear(etPwd, iivClearPwd);
        });

        RxView.touches(llAccount)
                .map(MotionEvent::getAction)
                .filter(action -> action == MotionEvent.ACTION_DOWN)
                .subscribe(action -> etAccount.requestFocus());
        RxView.touches(llPwd)
                .map(MotionEvent::getAction)
                .filter(action -> action == MotionEvent.ACTION_DOWN)
                .subscribe(action -> etPwd.requestFocus());
    }

    private void showOrHideClear(EditText editText, IconicsImageView iivClear) {
        boolean visible = editText.isFocused() && !TextUtils.isEmpty(editText.getText());
        iivClear.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * copeEye
     */
    @BindView(R.id.iivEye)
    IconicsImageView iivEye;

    private final int PWD_VISIBLE = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    private final int PWD_INVISIBLE = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

    private void copeEye() {
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
     * copeClear
     */
    @BindView(R.id.iivClearAccount)
    IconicsImageView iivClearAccount;

    @BindView(R.id.iivClearPwd)
    IconicsImageView iivClearPwd;

    @SuppressWarnings("ConstantConditions")
    private void copeClear() {
        RxView.clicks(iivClearAccount).subscribe(aVoid -> etAccount.setText(""));
        RxView.clicks(iivClearPwd).subscribe(aVoid -> etPwd.setText(""));

        RxTextView.afterTextChangeEvents(etAccount).subscribe(event -> {
            showOrHideClear(etAccount, iivClearAccount);
            enableOrDisableLogin();
        });
        RxTextView.afterTextChangeEvents(etPwd).subscribe(event -> {
            showOrHideClear(etPwd, iivClearPwd);
            enableOrDisableLogin();
        });
    }

    /**
     * enableOrDisableLogin
     */
    @BindView(R.id.btnLogin)
    LoginButton btnLogin;

    private void enableOrDisableLogin() {
        if (!TextUtils.isEmpty(etAccount.getText()) && !TextUtils.isEmpty(etPwd.getText())) {
            btnLogin.enable();

        } else {
            btnLogin.disable();
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
        String cookie = "JSESSIONID=" +sessionInfo.getJsessionid();
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
                        toastUtils.show("err");
                    }

                    @Override
                    public void onNext(SessionInfo sessionInfo) {
                        getMenu(sessionInfo);
//                        Object currentUser =map.get("currentUser");
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
    LoginInfoDao loginInfoDao;

    private void saveLoginInfo() {
        String airport = tvAirport.getText().toString().trim();
        String account = etAccount.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        LoginInfo loginInfo = new LoginInfo(airport, account, pwd);
        loginInfoDao.rx().insertOrReplace(loginInfo).subscribe(new Observer<LoginInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                toastUtils.show("保存登录信息失败");
            }

            @Override
            public void onNext(LoginInfo loginInfo) {
                toastUtils.show("保存登录信息成功");
            }
        });
    }

    //

    @BindView(R.id.fingerPrinterView)
    FingerPrinterView fingerPrinterView;

    private int fingerErrorNum = 0; // 指纹错误次数

    RxFingerPrinter rxfingerPrinter;

    private void copeFinger() {
        fingerPrinterView.setOnStateChangedListener(state -> {
            if (state == FingerPrinterView.STATE_CORRECT_PWD) {
                fingerErrorNum = 0;
                toastUtils.show("指纹识别成功");
                login();
            }
            if (state == FingerPrinterView.STATE_WRONG_PWD) {
                toastUtils.show("指纹识别失败，还剩" + (5 - fingerErrorNum) + "次机会");
                fingerPrinterView.setState(FingerPrinterView.STATE_NO_SCANING);
            }
        });
        RxView.clicks(findViewById(R.id.tvFinger)).subscribe(aVoid -> {
            long count = loginInfoDao.count();
            if (count != 0) {
                startFinger();
            } else {
                toastUtils.show("至少使用密码登录一次");
            }
        });
    }

    private void startFinger() {
        fingerPrinterView.setVisibility(View.VISIBLE);
        if (rxfingerPrinter == null) {
            rxfingerPrinter = new RxFingerPrinter(this);
        }
        fingerErrorNum = 0;
        rxfingerPrinter.unSubscribe(this);
        Subscription subscription = rxfingerPrinter.begin().subscribe(new Subscriber<Boolean>() {
            @Override
            public void onStart() {
                super.onStart();
                if (fingerPrinterView.getState() == FingerPrinterView.STATE_SCANING) {
                    return;
                } else if (fingerPrinterView.getState() == FingerPrinterView.STATE_CORRECT_PWD
                        || fingerPrinterView.getState() == FingerPrinterView.STATE_WRONG_PWD) {
                    fingerPrinterView.setState(FingerPrinterView.STATE_NO_SCANING);
                } else {
                    fingerPrinterView.setState(FingerPrinterView.STATE_SCANING);
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof FPerException) {
                    toastUtils.show(((FPerException) e).getDisplayMessage());
                }
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    fingerPrinterView.setState(FingerPrinterView.STATE_CORRECT_PWD);
//                    fingerPrinterView.setVisibility(View.INVISIBLE);
                } else {
                    fingerErrorNum++;
                    fingerPrinterView.setState(FingerPrinterView.STATE_WRONG_PWD);
                }
            }
        });
        rxfingerPrinter.addSubscription(this, subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rxfingerPrinter != null) {
            rxfingerPrinter.unSubscribe(this);
        }
    }

    private void s (){
        boolean result = FileUtils.copyFile(
                getDatabasePath("aems-db"),
                new File(App.baseDir(), "aems-db")
        );

        Logger.d(result);
    }


}
