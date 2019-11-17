package com.example.yexin.menu6;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.Login_logon.Login;

import java.io.File;

/**
 * Created by DELL on 2019/10/17.
 */

public class Show extends Activity {
    private User_public user_public=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user_public=new User_public(this);
        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
        boolean isFirstIn = preferences.getBoolean("isFirst", true);
        if(isFirstIn){
            Log.e("yjq_fire","第一次登入,跳转登入界面");
            Log.e("yjq_13","创建xml文件");
            user_public.init();
            Intent intent = new Intent(Show.this,Login.class);
            startActivity(intent);
            finish();
        }
        else{
            user_public.Init();
            if(UserPublic.isUser_flag()){   //进入主界面，暂注释
                Log.e("yqj_21","不是第一次登入,并且在登入状态，进入主页查看长连接");
                /**
                 * 用户信息的初始化
                 */
                user_public.initInfo();  //初始化个人信息
                UserPublic.setFirst(true);
                Intent intent = new Intent(Show.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Log.e("yjq_22","不是第一次登入，并且不在登入状态，去登入");
                Intent intent = new Intent(Show.this,Login.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
