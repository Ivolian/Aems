package com.unicorn.aems;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;

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
        s();
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

//    @Inject
//    PushUtils pushUtils;
//
//    private void setTags() {
//        Set<String> tags = new HashSet<>();
//        tags.add("-----");
//        pushUtils.setTags(tags);
//    }

}
