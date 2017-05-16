package com.unicorn.aems.app;

import android.app.Application;

import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.login.DaoMaster;
import com.unicorn.aems.login.DaoSession;

import org.greenrobot.greendao.database.Database;

import cn.jpush.android.api.JPushInterface;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        JPushInterface.init(this);
//        JPushInterface.setDebugMode(true);
        DaoSession daoSession = initDb();
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
    private DaoSession initDb() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aems-db");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

}
