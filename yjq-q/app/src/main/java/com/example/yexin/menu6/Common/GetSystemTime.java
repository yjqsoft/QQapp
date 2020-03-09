package com.example.yexin.menu6.Common;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

/**
 * Created by Administrator on 2020/3/6.
 */

public class GetSystemTime {
    public static String GetSystemTime(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
