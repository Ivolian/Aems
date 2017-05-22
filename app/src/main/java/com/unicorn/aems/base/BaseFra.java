package com.unicorn.aems.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hwangjr.rxbus.RxBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFra extends SupportFragment {

    protected abstract int layoutResId();

    protected boolean useRxBus() {
        return false;
    }

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        if (useRxBus()) {
            RxBus.get().register(this);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useRxBus()) {
            RxBus.get().register(this);
        }
        unbinder.unbind();
    }

    protected void init() {

    }

}
