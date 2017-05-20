package com.unicorn.aems.app;

import android.app.Application;
import android.os.Environment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.Utils;
import com.unicorn.aems.airport.entity.DaoSession;
import com.unicorn.aems.app.dagger.AppComponentProvider;


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
        Utils.init(this);
        ARouter.init(this);
        DaoSession daoSession = initDb();
        AppComponentProvider.init(this, daoSession);
//        Stetho.initializeWithDefaults(this);
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
        String dbPwd = DeviceUtils.getAndroidID();
        return new DaoSessionProvider(this, "aems-db", false, dbPwd).provide();
    }

    public static String baseDir() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

}
