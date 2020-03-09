package com.example.yexin.menu6.Common.Public_class;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DELL on 2019/10/13.
 */

public class User_public {

    private   SharedPreferences preferences=null;
    private   Activity Show=null;
    private   String User=null;  //用户账号
    private   boolean User_flag=false;  //用户的现状态
    private   String User_str=null; //用户的长连接
    private   boolean first=false;  //是否取登入界面还是主页面
    private   boolean isFirst=false; //是否第一次登入
    private   String Icon=null; //用户头像地址
    private   String Icon_time=null; //用户的头像标识
    private   String User_n=null;  //用户的昵称
    private   String User_q=null; //用户的签名
    private   String User_level=null; //用户的等级
    private   String User_sex=null;  //用户性别
    private   String User_datetime=null; //用户的生日
    private   String User_place=null;  //用户的地区



    /**

     * 初始化问题
     * @param sharedPreferences

     */
    public User_public(Context sharedPreferences){
        this.preferences=sharedPreferences.getSharedPreferences("user",MODE_PRIVATE);
    }

    public User_public(){
    }
    public  void init(){
        SharedPreferences.Editor editor=this.preferences.edit();
        editor.putBoolean("isFirst",false);  //是否第一次登入
        editor.putBoolean("User_flag",false);  //用户状态
        editor.putString("User",null);  //用户账号
        editor.putString("Icon",null);  //用户头像地址 暂时不用
        editor.putString("User_str",null);  //用户的长连接
//        editor.putString("Icon_time",null);  //用户头像的有效暂时不用
        editor.putString("User_n",null);  //用户的昵称
        editor.putString("User_q",null);  //用户的签名
        editor.putString("User_level",null);  //用户等级
        editor.putString("User_sex",null); //用户性别
        editor.putString("User_datetime",null);  //用户生日
        editor.putString("User_place",null); //用户的地区
        editor.commit();
    }
    public  void initInfo(){
        UserPublic.setUser(preferences.getString("User",null));  //用户账号
        UserPublic.setUser_flag(preferences.getBoolean("User_flag",false));  //用户的现状态
        UserPublic.setUser_str(preferences.getString("User_str",null)); //用户的长连接
        UserPublic.setIcon(preferences.getString("Icon",null)); //用户头像地址
        //String Icon_time=preferences.getString("User",null);; //用户的头像标识
        UserPublic.setUser_n(preferences.getString("User_n",null));  //用户的昵称
        UserPublic.setUser_q(preferences.getString("User_q",null)); //用户的签名
        UserPublic.setUser_level(preferences.getString("User_level",null)); //用户的等级
        UserPublic.setUser_sex(preferences.getString("User_sex",null)); //用户的性别
        UserPublic.setUser_datetime(preferences.getString("User_datetime",null)); //用户的生日
        UserPublic.setUser_place(preferences.getString("User_place",null));  //用户的地区
//        User=preferences.getString("User",null);  //个人账号
//        User_flag=preferences.getBoolean("User_flag",false); //登入状态
//        User_str=preferences.getString("User_str",null); //长连接
//        UserPublic.setUser(User);
//        UserPublic.setUser_flag(User_flag);
//        UserPublic.setUser_str(User_str);
//        Log.e("adgadg","用户"+ UserPublic.getUser()+"状态"+UserPublic.isUser_flag()+"长连接"+UserPublic.getUser_str());
    }
    public void Init(){
        User=preferences.getString("User",null);  //个人账号
        User_flag=preferences.getBoolean("User_flag",false); //登入状态
        User_str=preferences.getString("User_str",null); //长连接
        UserPublic.setUser(User);
        UserPublic.setUser_flag(User_flag);
        UserPublic.setUser_str(User_str);
        Log.e("adgadg","用户"+ UserPublic.getUser()+"状态"+UserPublic.isUser_flag()+"长连接"+UserPublic.getUser_str());
    }
    public String getUser_sex() {
        return User_sex;
    }

    public void setUser_sex(String user_sex) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_sex = user_sex;
        editor.putString("User_sex",user_sex);
        editor.commit();  //提交信息
        //UserPublic.setUser_sex(user_sex);
    }
    public  String getUser() {
        return User;
    }

    public  void setUser(String user) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User = user;
        editor.putString("User",user);
        editor.commit();  //提交信息
        //UserPublic.setUser(user);
    }

    public  boolean isUser_flag() {
        return User_flag;
    }

    public  void setUser_flag(boolean user_flag) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_flag = user_flag;
        editor.putBoolean("User_flag",user_flag);
        editor.commit();  //提交信息
        //UserPublic.setUser_flag(user_flag);
    }

    public  String getUser_str() {
        return User_str;
    }

    public  void setUser_str(String user_str) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_str = user_str;
        editor.putString("User_str",user_str);
        editor.commit();  //提交信息
       //UserPublic.setUser_str(user_str);
    }
    public  Boolean isIsFirst(){
        return isFirst;
    }
    public  void setIsFirst(boolean isFirst) {
        SharedPreferences.Editor editor=this.preferences.edit();
        this.isFirst = isFirst;
        editor.putBoolean("isFirst",isFirst);
        editor.commit();  //提交信息
        //UserPublic.setIsFirst(isFirst);
    }

    public  String getIcon() {
        return Icon;
    }

    public  void setIcon(String icon) {
        SharedPreferences.Editor editor=this.preferences.edit();
        Icon = icon;
        editor.putString("Icon",icon);
        editor.commit();  //提交信息
       // UserPublic.setIcon(Icon);
    }

    public  String getIcon_time() {
        return Icon_time;
    }

    public  void setIcon_time(String icon_time) {
        SharedPreferences.Editor editor=this.preferences.edit();
        Icon_time = icon_time;
        editor.putString("Icon_time",icon_time);
        editor.commit();  //提交信息
        //UserPublic.setIcon_time(Icon_time);
    }

    public  String getUser_n() {
        return User_n;
    }

    public  void setUser_n(String user_n) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_n = user_n;
        editor.putString("User_n",user_n);
        editor.commit();  //提交信息
       // UserPublic.setUser_n(User_n);
    }

    public  String getUser_q() {
        return User_q;
    }

    public  void setUser_q(String user_q) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_q = user_q;
        editor.putString("User_q",user_q);
        editor.commit();  //提交信息
        //UserPublic.setUser_q(User_q);
    }

    public  String getUser_level() {
        return User_level;
    }

    public  void setUser_level(String user_level) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_level = user_level;
        editor.putString("User_level",user_level);
        editor.commit();  //提交信息
        //UserPublic.setUser_level(User_level);
    }
    public  void setFirst(boolean first) {
        this.first = first;
        //UserPublic.setFirst(first);
    }

    public  boolean isFirst() {
        return first;
    }


    public String getUser_datetime() {
        return User_datetime;
    }

    public void setUser_datetime(String user_datetime) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_datetime = user_datetime;
        editor.putString("User_datetime",user_datetime);
        editor.commit();  //提交信息
        //UserPublic.setUser_datetime(user_datetime);

    }

    public String getUser_place() {
        return User_place;
    }

    public void setUser_place(String user_place) {
        SharedPreferences.Editor editor=this.preferences.edit();
        User_place = user_place;
        editor.putString("User_datetime",user_place);
        editor.commit();  //提交信息
        //UserPublic.setUser_place(user_place);

    }
//    public static boolean isFirst() {
//        return first;
//    }
//
//    public static void setFirst(boolean network) {
//        first = network;
//    }
//
//
//    public static String getUser() {
//        return User;
//    }
//
//    public static void setUser(String user) {
//        User = user;
//    }
//
//    public static boolean isUser_flag() {
//        return User_flag;
//    }
//
//    public static void setUser_flag(boolean user_flag) {
//        User_flag = user_flag;
//    }
//
//    public static String getUser_str() {
//        return User_str;
//    }
//
//    public static void setUser_str(String user_str) {
//        User_str = user_str;
//    }
}
