package com.unicorn.aems.app;

import android.app.Application;

import com.unicorn.aems.airport.entity.DaoMaster;
import com.unicorn.aems.airport.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DaoSessionProvider {

    public  final boolean ENCRYPTED = false;

    public DaoSession provide(Application application) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application.getApplicationContext(), ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

}
