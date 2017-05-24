package com.unicorn.aems.navigate;

import android.content.Context;

import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.unicorn.aems.app.dagger.App;

import javax.inject.Inject;

@App
public class Navigator {

    private final Context context;
    private final ARouter aRouter;

    @Inject
    public Navigator(Context context) {
        this.context = context;
        aRouter = ARouter.getInstance();
    }

    public void navigateTo(String routePath) {
        aRouter.build(routePath).navigation();
    }

    public void navigateTo(String routePath, NavCallback callback) {
        aRouter.build(routePath).navigation(context, callback);
    }

}
