package com.unicorn.aems.func;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;

public class SimpleAdapter extends BaseSectionQuickAdapter<MenuEntity, BaseViewHolder> {

    public SimpleAdapter() {
        super(R.layout.item_airport, R.layout.index_airport, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuEntity item) {
        helper.setText(R.id.tvAirport,item.t.getName());
//        helper.setImageUrl(R.id.iv, (String) item.t);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MenuEntity item) {
        helper.setText(R.id.tvIndex,item.header);
    }

}