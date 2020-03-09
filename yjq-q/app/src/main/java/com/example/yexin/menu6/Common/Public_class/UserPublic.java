package com.example.yexin.menu6.Common.Public_class;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by DELL on 2019/10/29.
 */

public class UserPublic {
    private   static boolean isWifi=false;
    private   static String User=null;  //用户账号
    private   static boolean User_flag=false;  //用户的现状态
    private   static String User_str=null; //用户的长连接
    private   static boolean firsT=false;  //是否取登入界面还是主页面
    private   static boolean isFirst=false; //是否第一次登入
    private   static String Icon=null; //用户头像地址
    private   static String Icon_time=null; //用户的头像标识
    private   static String User_n=null;  //用户的昵称
    private   static String User_q=null; //用户的签名
    private   static String User_level=null; //用户的等级
    private   static String User_sex=null; //用户的性别
    private   static String User_datetime=null; //用户的生日
    private   static String User_place=null;  //用户的地区

    public static String getUser_datetime() {
        return User_datetime;
    }

    public static void setUser_datetime(String user_datetime) {
        User_datetime = user_datetime;
    }

    public static String getUser_place() {
        return User_place;
    }

    public static void setUser_place(String user_place) {
        User_place = user_place;
    }

    public static String getUser_sex() {
        return User_sex;
    }

    public static void setUser_sex(String user_sex) {
        User_sex = user_sex;
    }
    public static String getUser() {
        return User;
    }

    public static void setUser(String user) {
        User = user;
    }

    public static boolean isUser_flag() {
        return User_flag;
    }

    public static void setUser_flag(boolean user_flag) {
        User_flag = user_flag;
    }

    public static String getUser_str() {
        return User_str;
    }

    public static void setUser_str(String user_str) {
        User_str = user_str;
    }

    public static boolean isIsFirst(){
        return isFirst;
    }
    public static void setIsFirst(boolean isfirst) {
        isFirst = isfirst;
    }

    public static String getIcon() {
        return Icon;
    }

    public static void setIcon(String icon) {
        Icon = icon;
    }

    public static String getIcon_time() {
        return Icon_time;
    }

    public static void setIcon_time(String icon_time) {
        Icon_time = icon_time;
    }

    public static String getUser_n() {
        return User_n;
    }

    public static void setUser_n(String user_n) {
        User_n = user_n;
    }

    public static String getUser_q() {
        return User_q;
    }

    public static void setUser_q(String user_q) {
        User_q = user_q;
    }

    public static String getUser_level() {
        return User_level;
    }

    public static void setUser_level(String user_leveL) {
        User_level = user_leveL;
    }

    public static void setFirst(boolean first) {
        firsT = first;
    }
    public static boolean isFirst() {
        return firsT;
    }
    public static boolean isWifi() {
        return isWifi;
    }

    public static void setIsWifi(boolean isWifI) {
        UserPublic.isWifi = isWifI;
    }
}
