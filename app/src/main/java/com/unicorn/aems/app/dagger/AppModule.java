package com.unicorn.aems.app.dagger;

import android.app.Application;
import android.content.Context;

import com.unicorn.aems.airport.entity.AirportDao;
import com.unicorn.aems.airport.respository.AirportRepository;
import com.unicorn.aems.login.DaoSession;
import com.unicorn.aems.login.LoginInfoDao;

import dagger.Module;
import dagger.Provides;


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
    LoginInfoDao provideLoginInfoDao(DaoSession daoSession) {
        return daoSession.getLoginInfoDao();
    }

    @App
    @Provides
    AirportDao provideAirportDao(DaoSession daoSession) {
        return daoSession.getAirportDao();
    }


//    @App
//    @Provides
//    Retrofit provideRetrofit(RetrofitProvider retrofitProvider) {
//        return retrofitProvider.provide();
//    }
//
//    @App
//    @Provides
//    BookCategoryService provideBookService(Retrofit retrofit) {
//        return retrofit.fetchData(BookCategoryService.class);
//    }

}
