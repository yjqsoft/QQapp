package com.example.yexin.menu6.Sideslip.Sideslip_center.Setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.yexin.menu6.R;

public class CommonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }
    public void onClick(View view){
        switch (view.getId()){
            //点击界面返回键返回设置界面
            case R.id.iv_back:
                Intent intent_one = new Intent(CommonActivity.this,SettingActivity.class);
                startActivity(intent_one);
                finish();
                break;

            //点击开启消息通知，再点击关闭消息通知
            case R.id.btn_switch:
                break;
        }
    }

    //点击手机返回键返回设置界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent = new Intent(CommonActivity.this, SettingActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
