package com.example.yexin.menu6.Person_Information.Base;

import android.app.Application;

/**
 * Created by 23646 on 2019/10/22.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    public static synchronized MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
