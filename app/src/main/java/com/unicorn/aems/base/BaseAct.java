package com.unicorn.aems.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.f2prateek.dart.Dart;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseAct extends SupportActivity {

    protected abstract int layoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dart.inject(this);
        setContentView(layoutResId());
        ButterKnife.bind(this);
        inject();
        init(savedInstanceState);
    }

    protected void inject() {
        // 依赖注入
    }

    protected void init(Bundle savedInstanceState) {
        //
    }


}
