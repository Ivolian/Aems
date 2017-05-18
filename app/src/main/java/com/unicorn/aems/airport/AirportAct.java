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
import com.unicorn.aems.airport.service.AirportService;
import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.base.BaseAct;
import com.unicorn.aems.utils.DensityUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import me.yokeyword.indexablerv.IndexableLayout;
import rx.android.schedulers.AndroidSchedulers;

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
        initTvCancel();
        initEtSearch();
        initIndexableLayout();
    }

    private void initTvCancel() {
        RxView.clicks(findViewById(R.id.tvCancel))
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(aVoid -> finish());
    }

    /**
     * 按名称或拼音查询
     */
    @BindView(R.id.etSearch)
    EditText etSearch;

    @Inject
    DensityUtils densityUtils;

    @BindColor(R.color.md_grey_50)
    int grey50;

    @BindColor(R.color.md_grey_300)
    int grey300;

    @BindColor(R.color.md_grey_400)
    int grey400;

    private void initEtSearch() {
        // 背景
        GradientDrawable etSearchBg = new GradientDrawable();
        etSearchBg.setCornerRadius(densityUtils.dp2px(5));
        etSearchBg.setStroke(1, grey300);
        etSearchBg.setColor(grey50);
        etSearch.setBackground(etSearchBg);
        etSearch.setHintTextColor(grey400);

        // 查询图标
        Drawable left = new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_search)
                .colorRes(R.color.colorPrimary)
                .sizeDp(17);
        etSearch.setCompoundDrawablePadding(densityUtils.dp2px(8));
        etSearch.setCompoundDrawables(left, null, null, null);

        // 事件
        RxTextView.afterTextChangeEvents(etSearch)
                .map(event -> event.editable().toString().trim())
                .flatMap(keyword -> airportService.getByNameOrPinyin(keyword))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(airports -> airportAdapter.setDatas(airports));
    }

    /**
     * 机场索引列表
     */
    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;

    @Inject
    AirportService airportService;

    @Inject
    AirportAdapter airportAdapter;

    private void initIndexableLayout() {
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        indexableLayout.setCompareMode(IndexableLayout.MODE_ALL_LETTERS);
        setCenterOverlay();
        indexableLayout.setAdapter(airportAdapter);
        addItemDecoration();
        setOnItemContentClickListener();

        // 加载列表
        airportService.get().observeOn(AndroidSchedulers.mainThread())
                .subscribe(airports -> airportAdapter.setDatas(airports));
    }


    /**
     * setCenterOverlay.
     */
    @BindColor(R.color.colorPrimary)
    int colorPrimary;

    private void setCenterOverlay() {
        indexableLayout.setOverlayStyle_Center();
        indexableLayout.getmCenterOverlay().setBackgroundColor(Color.TRANSPARENT);
        indexableLayout.getmCenterOverlay().setTextColor(colorPrimary);
        indexableLayout.getmCenterOverlay().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 48);
    }

    /**
     * addItemDecoration.
     */
    private void addItemDecoration() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1);
        paint.setColor(grey300);
        HorizontalDividerItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(this).paint(paint).build();
        indexableLayout.getRecyclerView().addItemDecoration(itemDecoration);
    }

    /**
     * setOnItemContentClickListener.
     */
    private void setOnItemContentClickListener() {
        airportAdapter.setOnItemContentClickListener((v, originalPosition, currentPosition, entity) -> {
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