package com.unicorn.aems.app.dagger;

import android.app.Application;
import android.content.Context;

import com.unicorn.MenuService;
import com.unicorn.aems.airport.entity.AirportDao;
import com.unicorn.aems.airport.entity.DaoSession;
import com.unicorn.aems.app.RetrofitProvider;
import com.unicorn.aems.login.LoginService;
import com.unicorn.aems.login.entity.LoginInfoDao;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class AppModule {

    private final Context context;

    private final DaoSession daoSession;

    AppModule(Application application, DaoSession daoSession) {
        this.context = application.getApplicationContext();
        this.daoSession = daoSession;
    }

    @App
    @Provides
    Context provideContext() {
        return context;
    }

    @App
    @Provides
    DaoSession provideDaoSession() {
        return daoSession;
    }

    @App
    @Provides
    AirportDao provideAirportDao(DaoSession daoSession) {
        return daoSession.getAirportDao();
    }


    @App
    @Provides
    Retrofit provideRetrofit(RetrofitProvider retrofitProvider) {
        return retrofitProvider.provide();
    }

    @App
    @Provides
    LoginService provideLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

    @App
    @Provides
    MenuService provideMenuService(Retrofit retrofit) {
        return retrofit.create(MenuService.class);
    }

    @App
    @Provides
    LoginInfoDao provideLoginInfoDao(DaoSession daoSession) {
        return daoSession.getLoginInfoDao();
    }


}
