package com.unicorn.aems.app;

import android.app.Application;

import com.unicorn.aems.airport.entity.DaoMaster;
import com.unicorn.aems.airport.entity.DaoSession;
import com.unicorn.aems.app.dagger.AppComponentProvider;

import org.greenrobot.greendao.database.Database;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
//        JPushInterface.init(this);
        DaoSession daoSession = initDaoSession();
        AppComponentProvider.init(this, daoSession);
//        initFragmentation();
    }

//
//    private void initFragmentation() {
//        Fragmentation.builder()
//                .stackViewMode(Fragmentation.BUBBLE)
//                .debug(BuildConfig.DEBUG)
//                .install();
//    }
//
    private DaoSession initDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aems-db");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

}
