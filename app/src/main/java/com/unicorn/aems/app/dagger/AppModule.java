package com.unicorn.aems.app.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private final Context context;

    AppModule(Application application) {
        context = application.getApplicationContext();
    }

    @App
    @Provides
    Context provideContext() {
        return context;
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
//        return retrofit.create(BookCategoryService.class);
//    }

}
