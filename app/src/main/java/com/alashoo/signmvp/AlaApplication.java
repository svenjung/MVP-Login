package com.alashoo.signmvp;

import android.app.Application;

import com.alashoo.signmvp.easemob.EaseMobManager;
import com.alashoo.signmvp.logger.MLog;

public class AlaApplication extends Application {

    private static AlaApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        MLog.get().i("Application create");

        instance = this;

        EaseMobManager.get().initEaseMobSdk();
    }

    public static Application getInstance() {
        return instance;
    }
}
