package com.unicorn.aems.airport;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.entity.AirportSection;

import java.util.List;

public class AirportSectionAdapter extends BaseSectionQuickAdapter<AirportSection, BaseViewHolder> {

    public AirportSectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AirportSection section) {
        helper.setText(R.id.tvSectionName, section.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, AirportSection item) {
        helper.setText(R.id.tvName, item.t.getName());
    }

}