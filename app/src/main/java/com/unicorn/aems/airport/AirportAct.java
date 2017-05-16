package com.unicorn.aems.airport;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unicorn.Constant;
import com.unicorn.aems.R;
import com.unicorn.aems.airport.model.AirportSection;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.utils.ColorUtils;
import com.unicorn.utils.ToastUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;

public class AirportAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_airport;
    }

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycleView();
    }

    /**
     * initRecycleView
     */
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    AirportAdapter airportAdapter;

    @Inject
    AirportProvider airportProvider;

    private void initRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addItemDecoration();
        airportAdapter = new AirportAdapter(R.layout.item_airport, R.layout.header_airport, null);
        recyclerView.setAdapter(airportAdapter);
        setOnItemClickListener();
        airportAdapter.setNewData(airportProvider.provide());
    }

    /**
     * addItemDecoration.
     */
    @Inject
    ColorUtils colorUtils;

    private void addItemDecoration() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final int dividerWidthPx = 1;
        paint.setStrokeWidth(dividerWidthPx);
        paint.setColor(colorUtils.getColor(R.color.md_grey_400));
        HorizontalDividerItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(this)
                .paint(paint).build();
        recyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * setOnItemClickListener.
     */

    @Inject
    ToastUtils toastUtils;

    private void setOnItemClickListener() {
        airportAdapter.setOnItemClickListener((adapter, view, position) -> {
            AirportSection airportSection = (AirportSection) adapter.getItem(position);
            if (airportSection != null && !airportSection.isHeader) {
                String airportName = airportSection.t.getName();
                if (airportName != null) {
                    finishAfterSetResult(airportName);
                }
            }
        });
    }

    private void finishAfterSetResult(String airportName) {
        Intent intent = new Intent();
        intent.putExtra(Constant.AIRPORT_NAME, airportName);
        setResult(Constant.GENERAL_RESULT_CODE, intent);
        finish();
    }

}
