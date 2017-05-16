package com.unicorn.aems.airport;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.model.AirportSection;

import java.util.List;

public class AirportAdapter extends BaseSectionQuickAdapter<AirportSection, BaseViewHolder> {

    public AirportAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AirportSection airportSection) {
        helper.setText(R.id.tvHeaderName, airportSection.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, AirportSection airportSection) {
        helper.setText(R.id.tvAirportName, airportSection.t.getName());
    }

}