package com.unicorn.aems.app;

import android.app.Application;

import com.unicorn.aems.airport.entity.DaoMaster;
import com.unicorn.aems.airport.entity.DaoSession;
import com.unicorn.aems.base.Provider;

import org.greenrobot.greendao.database.Database;

public class DaoSessionProvider implements Provider<DaoSession> {

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
