package com.unicorn.aems.app.dagger;

import android.content.Context;

import com.unicorn.aems.MainActivity;

import dagger.Component;

@App
@Component(modules = {AppModule.class})
public interface AppComponent {

    //
    Context provideContext();

//    Retrofit provideRetrofit();
//
//    GlideHelper provideGlideHelper();
//
//    ToastHelper provideToastHelper();
//
//    DensityHelper provideDensityHelper();
//
//    // main
    void inject(MainActivity o);
//
//    // sidebar
//    void inject(SidebarFra o);
//
//    void inject(SidebarItemDecoration o);
//
//    void inject(SidebarAdapter o);
//
//    void inject(SidebarHeaderView o);
//
//    // ui
//    void inject(HorseTabLayout o);

}
