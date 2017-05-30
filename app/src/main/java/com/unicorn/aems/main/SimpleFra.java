package com.unicorn.aems.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseFra;

import java.util.Arrays;

import butterknife.BindView;

public class SimpleFra extends BaseFra {

    @Override
    protected int layoutResId() {
        return R.layout.fra_simple;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    SimpleAdapter simpleAdapter = new SimpleAdapter();

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(simpleAdapter);
        simpleAdapter.setNewData(Arrays.asList(1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8));
    }

}
