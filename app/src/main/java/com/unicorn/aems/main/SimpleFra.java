package com.unicorn.aems.main;

import android.widget.TextView;

import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseFra;
import com.unicorn.aems.constant.Key;

import butterknife.BindView;

public class SimpleFra extends BaseFra {

    @Override
    protected int layoutResId() {
        return R.layout.fra_simple;
    }

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void init() {
        String text = getArguments().getString(Key.TEXT);
        textView.setText(text);
    }

}
