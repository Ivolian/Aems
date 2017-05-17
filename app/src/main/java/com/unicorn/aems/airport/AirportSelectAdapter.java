package com.unicorn.aems.airport;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.model.AirportSection;
import com.unicorn.aems.app.dagger.App;

import javax.inject.Inject;

@App
public class AirportSelectAdapter extends BaseSectionQuickAdapter<AirportSection, BaseViewHolder> {

    @Inject
    public AirportSelectAdapter() {
        super(R.layout.item_airport_select, R.layout.header_airport_select, null);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AirportSection airportSection) {
        helper.setText(R.id.tvHeaderName, airportSection.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, AirportSection airportSection) {
        helper.setText(R.id.tvAirportName, airportSection.t.getName());
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, StockEntity.StockInfo.TYPE_HEADER);
//    }
//
//    @Override
//    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        FullSpanUtil.onViewAttachedToWindow(holder, this, StockEntity.StockInfo.TYPE_HEADER);
//    }

}