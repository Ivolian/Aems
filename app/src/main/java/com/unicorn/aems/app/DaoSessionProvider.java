package com.unicorn.aems.app;

import android.app.Application;

import com.unicorn.aems.airport.entity.DaoMaster;
import com.unicorn.aems.airport.entity.DaoSession;
import com.unicorn.aems.base.BaseProvider;

import org.greenrobot.greendao.database.Database;

public class DaoSessionProvider implements BaseProvider<DaoSession> {

    private final DaoMaster daoMaster;

    public DaoSessionProvider(Application application, String dbName, boolean encrypted, String dbPwd) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application.getApplicationContext(), dbName);
        Database db = encrypted ? helper.getEncryptedWritableDb(dbPwd) : helper.getWritableDb();
        daoMaster = new DaoMaster(db);
    }

    @Override
    public DaoSession provide() {
        return daoMaster.newSession();
    }

}
