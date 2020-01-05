package com.example.yexin.menu6;

import android.app.Application;
import android.content.IntentFilter;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.yexin.menu6.Common.Wifi.NetManagerService;

import org.xutils.x;

/**
 * Created by DELL on 2019/9/29.
 */

public class MyApplication extends Application {
    private NetManagerService netManagerService;
    private IntentFilter intentFilter;
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); //是否输出debug日志，开启debug会影响性能。
        /**
         * 注册广播监听器
         */
        netManagerService=new NetManagerService();
        intentFilter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(netManagerService, intentFilter);

        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        //设定中心点坐标
    }
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
        unregisterReceiver(netManagerService);
    }
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        super.onLowMemory();
    }
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        super.onTrimMemory(level);
    }
}
