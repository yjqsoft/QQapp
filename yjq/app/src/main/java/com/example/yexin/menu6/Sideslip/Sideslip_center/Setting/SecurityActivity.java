package com.example.yexin.menu6.Sideslip.Sideslip_center.Setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.yexin.menu6.R;

public class SecurityActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
    }

    //设置控件点击事件
    public void onClick(View view){
        switch (view.getId()){
            //点击界面返回键返回设置界面
            case R.id.iv_back:
                Intent intent_one = new Intent(SecurityActivity.this,SettingActivity.class);
                startActivity(intent_one);
                finish();
                break;

            //点击跳转手机号码修改界面
            case R.id.rl_phone:
                Intent intent_two = new Intent(SecurityActivity.this,PhoneActivity.class);
                startActivity(intent_two);
                finish();
                break;

            //点击跳转密码修改界面
            case R.id.rl_password:
                Intent intent_three = new Intent(SecurityActivity.this,PasswordActivity.class);
                startActivity(intent_three);
                finish();
                break;
        }
    }

    //点击手机返回键返回设置界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent = new Intent(SecurityActivity.this, SettingActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
