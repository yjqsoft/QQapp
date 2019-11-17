package com.example.yexin.menu6.Sideslip.Sideslip_center.Share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.R;
import com.example.yexin.menu6.Sideslip.Sideslip_center.Mynews;
import com.example.yexin.menu6.Sideslip.Sideslip_center.Setting.CommonActivity;
import com.example.yexin.menu6.Sideslip.Sideslip_center.Setting.SettingActivity;

public class Tickling extends Activity {
    private TextView tongji;
    private EditText editText1;
    private Button tijiao;
    private  int current_Length=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickling);
        tongji=(TextView)findViewById(R.id.tongji);
        editText1=(EditText)findViewById(R.id.editText1);
        tijiao=(Button)findViewById(R.id.tijiao);
        tongji.setText(0+"/300");

        editText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(current_Length <300){
                    current_Length = editText1.getText().length();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tongji.setText(current_Length+"/300");
            }
            @Override
            public void afterTextChanged(Editable s) {
                tongji.setText(current_Length+"/300");
            }
        });
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( current_Length>=5)                                                                //点击提交成功后页面跳转
                {
                    Intent intent=new Intent(Tickling.this,Mynews.class);
                    startActivity(intent);
                    finish();
                }
                else if( current_Length>0&&current_Length<5)
                {
//                    Toast toast = Toast.makeText(MainActivity.this, "请填写不少于五个字的问题描述", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 10);
//                    toast.show();
                }
                else  if(current_Length<1)
                {
//                    Toast toast = Toast.makeText(MainActivity.this, "请填写问题描述", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 10);
//                    toast.show();
                }
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent = new Intent(Tickling.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
