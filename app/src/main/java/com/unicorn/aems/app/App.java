package com.unicorn.aems.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
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
//        if (isDebug()) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(this);
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
