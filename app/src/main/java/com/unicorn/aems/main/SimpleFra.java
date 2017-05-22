package com.unicorn.aems.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseFra;

public class SimpleFra extends BaseFra {

    @Override
    protected int layoutResId() {
        return R.layout.act_login;
    }

    @Override
    protected void init() {

//        BookshelfComponentProvider.provide().inject(this);
//        initSwipeRefreshLayout();
//        initRecycleView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        swipeRefreshLayout.setRefreshing(true);
//        loadFirst();
    }



}
