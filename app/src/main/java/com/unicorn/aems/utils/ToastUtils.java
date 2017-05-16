package com.unicorn.aems.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.unicorn.aems.app.dagger.App;

import javax.inject.Inject;

@App
public class ToastUtils {

    private Toast toast;

    @Inject
    public ToastUtils(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    public void show(String msg) {
        toast.setText(msg);
        toast.show();
    }

}
