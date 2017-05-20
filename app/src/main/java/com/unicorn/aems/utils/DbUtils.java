package com.unicorn.aems.utils;

import android.content.Context;

import com.blankj.utilcode.util.FileUtils;
import com.unicorn.aems.app.App;

import java.io.File;

import javax.inject.Inject;


@com.unicorn.aems.app.dagger.App
public class DbUtils {

    private final Context context;

    private final String dbName;

    @Inject
    public DbUtils(Context context) {
        this.context = context;
        this.dbName = "aems-db";
    }

    public boolean exportDb() {
        return FileUtils.copyFile(context.getDatabasePath(dbName), new File(App.baseDir(), dbName));
    }

}
