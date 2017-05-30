package com.unicorn.aems.main;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;

public class SimpleAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public SimpleAdapter() {
        super(R.layout.item_airport);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Object item) {
        viewHolder.setText(R.id.tvAirport, "机车");
    }
}
