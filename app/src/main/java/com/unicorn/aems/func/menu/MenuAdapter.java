package com.unicorn.aems.func.menu;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;

public class MenuAdapter extends BaseSectionQuickAdapter<MenuSectionEntity, BaseViewHolder> {

    public MenuAdapter() {
        super(R.layout.item_menu, R.layout.header_menu, null);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MenuSectionEntity item) {
        // todo
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuSectionEntity item) {
        // TODO: 2017/6/1
    }

}