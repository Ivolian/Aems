package com.unicorn.aems.func;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;
import com.unicorn.aems.menu.Menu;

public class MenuAdapter extends BaseQuickAdapter<Menu, BaseViewHolder> {
    public MenuAdapter() {
        super(R.layout.item_airport);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Menu item) {
    }
}