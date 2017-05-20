package com.unicorn.aems.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.f2prateek.dart.Dart;
import com.hwangjr.rxbus.RxBus;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseAct extends SupportActivity {

    protected  int layoutResId() {return -1;};

    protected boolean useRxBus() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dart.inject(this);
        if (layoutResId() != -1) {
            setContentView(layoutResId());
        }
        ButterKnife.bind(this);
        inject();
        if (useRxBus()) {
            RxBus.get().register(this);
        }
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (useRxBus()) {
            RxBus.get().unregister(this);
        }
        super.onDestroy();
    }

    protected void inject() {
        // 依赖注入
    }

    protected void init(Bundle savedInstanceState) {
        //
    }


}
