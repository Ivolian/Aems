package com.unicorn.aems.func.menu;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;

public class MenuAdapter extends BaseSectionQuickAdapter<MenuSectionEntity, BaseViewHolder> {

    public MenuAdapter() {
        super(R.layout.item_menu, R.layout.header_menu, null);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MenuSectionEntity item) {
        helper.setText(R.id.tvHeader, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuSectionEntity item) {
        s(helper.getView(R.id.llItem));
        helper.setText(R.id.tvName, item.t.getName());
        // TODO: 2017/6/1
    }

    private void s(View item) {
        GradientDrawable g
                = new GradientDrawable();
        g.setCornerRadius(5);
        g.setColor(ContextCompat.getColor(mContext, R.color.md_grey_100));
        item.setBackground(g);
    }

}