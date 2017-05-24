package com.unicorn.aems.app.dagger;

import android.content.Context;

import com.unicorn.aems.FingerPrintAct;
import com.unicorn.aems.airport.AirportAct;
import com.unicorn.aems.login.LoginAct;
import com.unicorn.aems.login.LoginHelper;
import com.unicorn.aems.main.FuncFra;
import com.unicorn.aems.splash.SplashAct;

import dagger.Component;

@App
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context provideContext();

//    Retrofit provideRetrofit();
//    GlideHelper provideGlideHelper();

    void inject(SplashAct o);
    void inject(LoginAct o);
    void inject(AirportAct o);
    void inject(LoginHelper o);
    void inject(FingerPrintAct o);

    void inject(FuncFra o);

}
