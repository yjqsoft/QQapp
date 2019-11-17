package com.example.yexin.menu6.Sideslip.Sideslip_center.Setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.yexin.menu6.R;

public class PasswordActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }

    //设置控件点击事件
    public void onClick(View view){
        switch (view.getId()){
            //点击界面返回键返回安全设置界面
            case R.id.iv_back:
                Intent intent_one = new Intent(PasswordActivity.this,SecurityActivity.class);
                startActivity(intent_one);
                finish();
                break;

            //点击更改按钮保存密码，然后返回并显示在安全设置界面
            case R.id.btn_update:
                break;
        }
    }

    //点击手机返回键返回安全设置界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent = new Intent(PasswordActivity.this,SecurityActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
