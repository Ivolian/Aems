package com.unicorn.aems.app;

import android.app.Application;

import com.unicorn.aems.app.dagger.AppComponentProvider;

import cn.jpush.android.api.JPushInterface;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
        AppComponentProvider.init(this);
//        initARouter();
//        initFragmentation();
//        String tag = "QqReader";
//        Logger.init(tag);
//        initDb();
    }

//    private void initARouter() {
//        ARouter.openLog();
//        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        ARouter.openDebug();
//        ARouter.init(this);
//    }
//
//    private void initFragmentation() {
//        Fragmentation.builder()
//                .stackViewMode(Fragmentation.BUBBLE)
//                .debug(BuildConfig.DEBUG)
//                .install();
//    }
//
//    private void initDb() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "QqReader-db");
//        Database db = helper.getWritableDb();
//        DaoSession daoSession = new DaoMaster(db).newSession();
//
////        List<Ad> adList = new Gson().fromJson(Delete.de(),
////                new TypeToken<List<Ad>>() {
////                }.getType());
////
////        daoSession.getAdDao().insertOrReplaceInTx(adList);
////        for (Ad ad : adList) {
////            if (ad.extInfo != null)
////                daoSession.getExtInfoDao().insertOrReplace(ad.extInfo);
////        }
////        Logger.e("ss");
//    }

}
