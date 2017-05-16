package com.unicorn.aems;

import android.os.Bundle;

import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.push.PushUtils;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class LoginAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppComponentProvider.provide().inject(this);
//        setTags();
    }

    @Inject
    PushUtils pushUtils;

    private void setTags() {
        Set<String> tags = new HashSet<>();
        tags.add("-----");
        pushUtils.setTags(tags);
    }

}
