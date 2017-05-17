package com.unicorn.aems.airport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unicorn.aems.R;
import com.unicorn.aems.airport.model.Airport;
import com.unicorn.aems.app.dagger.App;

import javax.inject.Inject;

import me.yokeyword.indexablerv.IndexableAdapter;

@App
public class AirportSelectAdapter extends IndexableAdapter<Airport> {

//    @Inject
//    public AirportSelectAdapter() {
//        super(R.layout.item_airport_select, R.layout.header_airport_select, null);
//    }
//
//    @Override
//    protected void convertHead(BaseViewHolder helper, AirportSection airportSection) {
//        helper.setText(R.id.tvHeaderName, airportSection.header);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, AirportSection airportSection) {
//        helper.setText(R.id.tvAirportName, airportSection.t.getName());
//    }

    private LayoutInflater mInflater;

    @Inject
    public AirportSelectAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.header_airport_select, parent, false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_airport_select, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        IndexVH vh = (IndexVH) holder;
        vh.tv.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Airport entity) {
        ContentVH vh = (ContentVH) holder;
        vh.tv.setText(entity.getName());
    }

    private class IndexVH extends RecyclerView.ViewHolder {
        TextView tv;

        public IndexVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvHeaderName);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView tv;

        public ContentVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvAirportName);
        }
    }
}