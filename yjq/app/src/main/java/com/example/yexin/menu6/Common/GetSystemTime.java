package com.example.yexin.menu6.Common;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.util.Date;

/**
 * Created by Administrator on 2020/3/6.
 */

public class GetSystemTime {
    public static String GetSystemTime(int TimeStatus){
        String ReserverTime=null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        SimpleDateFormat simpleDateFormatyd = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());

        if(TimeStatus==0){
            Date ServerDate = new Date(date.getTime());
            ReserverTime=simpleDateFormatyd.format(ServerDate);
        }else if(TimeStatus==1){
            long time = 24*60*60*1000;//1天
            Date ServerDate = new Date(date.getTime() + time);
            ReserverTime=simpleDateFormatyd.format(ServerDate);

        }else if(TimeStatus==2){
            long time = 2*24*60*60*1000;//2天
            Date ServerDate = new Date(date.getTime() + time);
            ReserverTime=simpleDateFormatyd.format(ServerDate);
        }
        long time = 15*60*1000;//15分钟
        Date afterDate = new Date(date.getTime() + time);

        Log.e("timea预定",simpleDateFormatyd.format(date));

        Log.e("timeanow",simpleDateFormat.format(date));

      //  Log.e("timeabefor",simpleDateFormat.format(beforDate));

        Log.e("timeafter",simpleDateFormat.format(afterDate));
//        if(afterDate.getTime()>=date.getTime()){
//
//
//            Log.e("timeayes",simpleDateFormat.format(afterDate));
//        }
        Log.e("timearesult1",(afterDate.getTime()>=date.getTime())+"");
        Log.e("timearesult2",afterDate.getTime()+"");
        Log.e("timearesult3",date.getTime()+"");

        return ReserverTime+"￥"+simpleDateFormat.format(afterDate);
    }
}
