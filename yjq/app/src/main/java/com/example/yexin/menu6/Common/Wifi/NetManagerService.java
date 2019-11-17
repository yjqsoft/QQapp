package com.example.yexin.menu6.Common.Wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Public_class.UserPublic;


/**
 * Created by DELL on 2019/10/16.
 * 网络监听器，用来监听网络的变化；
 */

public class NetManagerService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager mConnectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);  //添加网络监听器的变化变量；
        NetworkInfo mNetworkInfo = mConnectivity.getActiveNetworkInfo();//用来获取，与系统的网络变量比
//        NetworkInfo mWiFiNetworkInfo = mConnectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  //获取WiFi网络连接
//        NetworkInfo mMobileNetworkInfo = mConnectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//获取数据的网络连接
        if(mNetworkInfo==null){
            Log.e("yjq_one","无网络连接");
            UserPublic.setIsWifi(false);
            Toast.makeText(context, "请连接网络", Toast.LENGTH_SHORT).show();
        }
        else{
            if(mNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                Log.e("yjq_two","wifi已经连接");
                UserPublic.setIsWifi(true);
                Toast.makeText(context, "WiFi已连接", Toast.LENGTH_SHORT).show();
            }
            else if(mNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                Log.e("yjq_three","数据连接");
                UserPublic.setIsWifi(true);
                Toast.makeText(context, "数据连接，注意流量哦", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
