package com.unicorn.aems.airport;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unicorn.aems.R;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.utils.ColorUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class AirportAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_airport;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    AirportSectionAdapter airportSectionAdapter;

    @Inject
    AirportSectionProvider airportSectionProvider;

    @Inject
    ColorUtils colorUtils;

    @Override
    protected void init(Bundle savedInstanceState) {
        AppComponentProvider.provide().inject(this);
        recyclerView.setAdapter(airportSectionAdapter = new AirportSectionAdapter(R.layout.item_airport, R.layout.header_airport, new ArrayList()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        airportSectionAdapter.setNewData(airportSectionProvider.provide());

        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setColor(colorUtils.getColor(R.color.md_grey_400));
        paint.setAntiAlias(true);
//        new HorizontalDividerItemDecoration.Builder(this).build();
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());
    }

}
