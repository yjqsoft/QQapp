package com.example.yexin.menu6.Login_logon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yexin.menu6.R;

public class Register extends Activity {
    private ImageView iv_back;
    private Button btn_register;
    private EditText et_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        /**
         * 页面初始化
         */
        Layout_init();
    }
    public void Layout_init(){
        iv_back = (ImageView) findViewById(R.id.iv_back);
        btn_register = (Button) findViewById(R.id.btn_register);
        et_phone= (EditText) findViewById(R.id.et_phone);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                Intent intent_one = new Intent(Register.this,Login.class);
                startActivity(intent_one);
                finish();
                break;
            case R.id.tv_country:
                break;
            case R.id.btn_register:
                if(TextUtils.isEmpty(et_phone.getText().toString()))
                {
                    Toast.makeText(Register.this, "手机号码不能为空!", Toast.LENGTH_SHORT).show();
                }
                else if(et_phone.getText().toString().length()>11){
                    Toast.makeText(Register.this, "手机号码格式有误!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent_two = new Intent(Register.this, EnterPassword.class);
                    intent_two.putExtra("phone", et_phone.getText().toString().trim());
                    startActivity(intent_two);
                    Log.i("1234", et_phone.getText().toString());
                    finish();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
            Intent myintent=new Intent(Register.this,Login.class);
            startActivity(myintent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
