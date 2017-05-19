package com.unicorn.aems.navigate;

import com.alibaba.android.arouter.launcher.ARouter;
import com.unicorn.aems.app.dagger.App;

import javax.inject.Inject;

@App
public class Navigator {

    private final ARouter aRouter;

    @Inject
    public Navigator() {
        aRouter = ARouter.getInstance();
    }

    public void navigateTo(String routePath) {
        aRouter.build(routePath).navigation();
    }

}
