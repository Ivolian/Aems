package com.unicorn.aems.app.dagger;

import android.app.Application;

import dagger.internal.Preconditions;

public class AppComponentProvider {

    private AppComponentProvider() {
    }

    private static AppComponent appComponent;

    public static void init(Application application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public static AppComponent provide() {
        Preconditions.checkNotNull(appComponent);
        return appComponent;
    }

}
