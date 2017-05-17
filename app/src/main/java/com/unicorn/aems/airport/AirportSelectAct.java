package com.unicorn.aems.airport;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.unicorn.Constant;
import com.unicorn.aems.R;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.utils.ColorUtils;
import com.unicorn.aems.utils.DensityUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.indexablerv.IndexableLayout;

public class AirportSelectAct extends BaseAct {

    @Override
    protected int layoutResId() {
        return R.layout.act_airport_select;
    }

    @Override
    protected void inject() {
        AppComponentProvider.provide().inject(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        copeBack();
        copeSearch();
        initIndexableLayout();
    }

    /**
     * 处理回退
     */
    private void copeBack() {
        RxView.clicks(findViewById(R.id.flBack))
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> finish());
    }

    /**
     * 按名称或拼音查询机场
     */
    @BindView(R.id.etSearch)
    EditText etSearch;

    @Inject
    DensityUtils densityUtils;

    private void copeSearch() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(densityUtils.dp2px(4));
        gradientDrawable.setColor(colorUtils.getColor(R.color.md_grey_200));
        etSearch.setBackground(gradientDrawable);
        Drawable drawable = new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_search)
                .colorRes(R.color.colorPrimary)
                .sizeDp(14);

        etSearch.setCompoundDrawablePadding(20);
        etSearch.setCompoundDrawables(drawable, null, null, null);

        RxTextView.afterTextChangeEvents(etSearch)
                .map(event -> event.editable().toString().trim())
                .flatMap(keyword -> airportRepository.getAirports(keyword))
                .filter(airports -> airports.size() != 0)
                .subscribe(airports -> airportSelectAdapter.setDatas(airports));
    }


    /**
     * initIndexableLayout
     */
    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;

    @Inject
    AirportSelectAdapter airportSelectAdapter;

    @Inject
    AirportRepository airportRepository;

    private void initIndexableLayout() {
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        indexableLayout.setCompareMode(IndexableLayout.MODE_ALL_LETTERS);
        setCenterOverlay();
        indexableLayout.setAdapter(airportSelectAdapter);
        addItemDecoration();
        setOnItemContentClickListener();
        airportRepository.getAirports().subscribe(airports -> airportSelectAdapter.setDatas(airports));
    }

    private void setCenterOverlay() {
        indexableLayout.setOverlayStyle_Center();
        indexableLayout.getmCenterOverlay().setBackgroundColor(Color.TRANSPARENT);
        indexableLayout.getmCenterOverlay().setTextColor(colorUtils.getColor(R.color.colorPrimary));
        indexableLayout.getmCenterOverlay().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 48);
    }

    /**
     * addItemDecoration.
     */
    @Inject
    ColorUtils colorUtils;

    private void addItemDecoration() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1);
        paint.setColor(colorUtils.getColor(R.color.md_grey_300));
        HorizontalDividerItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(this).paint(paint).build();
        indexableLayout.getRecyclerView().addItemDecoration(itemDecoration);
    }

    /**
     * setOnItemContentClickListener.
     */
    private void setOnItemContentClickListener() {
        airportSelectAdapter.setOnItemContentClickListener((v, originalPosition, currentPosition, entity) -> {
            if (originalPosition >= 0) {
                finishAfterSetResult(entity.getName());
            }
        });
    }

    private void finishAfterSetResult(String airportName) {
        Intent intent = new Intent();
        intent.putExtra(Constant.AIRPORT_NAME, airportName);
        setResult(Constant.AIRPORT_RESULT_CODE, intent);
        finish();
    }

}
