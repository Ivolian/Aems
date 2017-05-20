package com.unicorn.aems.app.dagger;

import android.content.Context;

import com.unicorn.aems.airport.AirportAct;
import com.unicorn.aems.login.LoginAct;
import com.unicorn.aems.login.ui.LoginButton;
import com.unicorn.aems.login.ui.UnderLineLinearLayout;
import com.unicorn.aems.splash.SplashAct;

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

    void inject(SplashAct o);
    //
    void inject(LoginAct o);
    void inject(UnderLineLinearLayout o);
    void inject(LoginButton o);

    void inject(AirportAct o);


}
