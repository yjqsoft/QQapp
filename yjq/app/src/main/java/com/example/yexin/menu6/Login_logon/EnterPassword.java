package com.example.yexin.menu6.Login_logon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.Web_Json;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/*注册输入密码，验证码功能，密码校对*/
public class EnterPassword extends Activity {
    private ImageView iv_back;
    private Button btn_verification_code;
    private Button btn_final_register;
    private EditText et_verification_code;
    private EditText et_password_one;
    private EditText editText3;
    private String phone=null;
    private String number=null;
    private String Pwd_one=null;
    private String Pwd_two=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        /**
         * 界面初始化
         */
        Layout_init();
        /**
         * 验证码功能模块
         */
        SMSSDK.registerEventHandler(handler);
    }
    public void Layout_init(){
        iv_back = (ImageView)findViewById(R.id.iv_back);
        btn_verification_code = (Button)findViewById(R.id.btn_verification_code);
        btn_final_register = (Button)findViewById(R.id.btn_final_register);
        et_verification_code = (EditText)findViewById(R.id.et_verification_code);
        et_password_one=(EditText)findViewById(R.id.et_password_one);
        editText3=(EditText)findViewById(R.id.editText3);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                Intent intent1 = new Intent(EnterPassword.this, Register.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btn_verification_code:
                Intent intent = getIntent();
                phone = intent.getStringExtra("phone");
                //获取验证码
                SMSSDK.getVerificationCode("86",phone, (OnSendMessageHandler) null);
                break;
            case R.id.btn_final_register:
                number = et_verification_code.getText().toString();
                Pwd_one=et_password_one.getText().toString();
                Pwd_two=editText3.getText().toString();
                if(TextUtils.isEmpty(Pwd_one))
                    Toast.makeText(EnterPassword.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                else if(!Pwd_one.equals(Pwd_two))
                    Toast.makeText(EnterPassword.this,"密码不一致",Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(number))
                    Toast.makeText(EnterPassword.this,"请填写验证码",Toast.LENGTH_SHORT).show();
                else {
                    Log.i("1234", phone + "," + number);
                    SMSSDK.submitVerificationCode("86", phone, number);
                    //String url = "http://172.22.165.167:8080/ByteSoft_two/Register";
                    RequestParams params = new RequestParams(Web_url.URL_Register);
                    // params2 = new RequestParams(URL1);
                    Log.w("WangJ", "运行在这个ok"+params.toString());
                    Log.w("WangJ", "运行在这个ok");
                    String jsonObject= Web_Json.Login(phone.toString(),Pwd_one.toString());
                    params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部\
                    params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
                    params.setBodyContent(jsonObject);//添加json内容到请求参数里
                   /* params.addParameter("name", "abc");
                    params.addParameter("password", "123");*/
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.w("yjq",result);//接收JSON的字符串
                            //Toast.makeText(x.app(),result,Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                        }
                        @Override
                        public void onCancelled(CancelledException cex) {
                        }
                        @Override
                        public void onFinished() {
                        }
                    });
                }
                break;
        }
    }
    EventHandler handler = new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE){

                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EnterPassword.this,"验证成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if (event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EnterPassword.this,"语音验证发送",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EnterPassword.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    Log.i("test","test");
                }
            }else{
                ((Throwable)data).printStackTrace();
                Throwable throwable = (Throwable) data;
                throwable.printStackTrace();
                Log.i("1234",throwable.toString());
                try {
                    JSONObject obj = new JSONObject(throwable.getMessage());
                    final String des = obj.optString("detail");
                    if (!TextUtils.isEmpty(des)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EnterPassword.this,des,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
            Intent myintent=new Intent(EnterPassword.this,Register.class);
            startActivity(myintent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
