package com.example.yexin.menu6.Sideslip.Sideslip_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.R;
import com.example.yexin.menu6.Sideslip.Sideslip_center.Setting.SettingActivity;

public class Mynews extends Activity {
    private Button close=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynews);
        close=(Button)findViewById(R.id.button);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            //点击界面返回键返回上一级
            case R.id.button:
                Intent intent_one1 = new Intent(Mynews.this, MainActivity.class);
                startActivity(intent_one1);
                finish();
                break;
        }
    }
}
