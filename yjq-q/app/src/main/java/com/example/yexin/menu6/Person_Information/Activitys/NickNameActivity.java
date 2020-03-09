package com.example.yexin.menu6.Person_Information.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.Modify;
import com.example.yexin.menu6.Common.Json.WebMoreInfomation;
import com.example.yexin.menu6.Common.Json.Web_Json;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.Login_logon.Login;
import com.example.yexin.menu6.Person_Information.ModifyInfomation.Modifyinfo;
import com.example.yexin.menu6.Person_Information.ModifyInfomation.Nickname;
import com.example.yexin.menu6.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;

import static android.R.attr.host;

public class NickNameActivity extends Activity {
    private EditText et_name=null;
    private String text;
    private boolean isSuccess=false;
    private User_public user_public=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        user_public=new User_public(this);
        et_name = (EditText)findViewById(R.id.et_nickname);
        et_name.setText(UserPublic.getUser_n());  //初始化
    }

    public void onClick(View view){
        text=et_name.getText().toString().trim();
        Log.e("1","返回的值为："+text);
        switch(view.getId()){
            //点击界面返回键返回个人信息界面
            case R.id.iv_back:
                Intent intent1=new Intent();
                intent1.putExtra("nickname","");
                setResult(10,intent1);
                finish();
                break;
            //点击完成保存昵称信息，然后返回并显示在个人信息界面
            case R.id.tv_save:
                if(!TextUtils.isEmpty(text)){
                    Xutils(text,"3");
                }
                else{
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    private void Xutils(String content,String model){

        String jsonObject= Modify.setInfo(content,model);
        RequestParams params = new RequestParams(Web_url.URL_Modifyinfo);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        params.setBodyContent(jsonObject);//添加json内容到请求参数里
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                HashMap<String,String> map= Modify.getModify(result);

                Log.e("124",map.get("code").toString());
                if(map.get("code").toString().equals("200")){
                    isSuccess=true;
                }
                else{
                    isSuccess=false;
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1","失败");
                isSuccess=false;
                Toast.makeText(NickNameActivity.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");
                if(isSuccess){
                    Log.e("ahgia","修改成功");
                    Intent intent=new Intent();
                    intent.putExtra("nickname",text);
                    user_public.setUser_n(text);
                    UserPublic.setUser_n(text);
                    setResult(2,intent);
                    finish();
                }
                else{
                    Intent intent=new Intent();
                    intent.putExtra("nickname","");
                    setResult(10,intent);
                    finish();
                    Toast.makeText(NickNameActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    Log.e("gaodifha","修改失败");
                }
            }
        });
//        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
//        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//        params.setBodyContent(jsonObject);//添加json内容到请求参数里
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            private boolean isSuccess=false;
//            @Override
//            public void onSuccess(String result) {
//                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//                HashMap<String,String> map= Modify.getModify(result);
//                String code=map.get("code");
//                if(code.equals("200")){
//                    isSuccess=true;
//                }
//                else{
//                    isSuccess=false;
//                }
//            }
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.e("yjq5","失败");
////                isSuccess=false;
//                Toast.makeText(NickNameActivity.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Log.e("yjq","取消");
//            }
//            @Override
//            public void onFinished() {
//                Log.e("yjq","完成");
//                if(isSuccess){
//                    Log.e("ahgia","修改成功");
//                    Intent intent=new Intent();
//                    intent.putExtra("nickname",text);
//                    setResult(2,intent);
//                    finish();
//                }
//                else{
//                    Intent intent=new Intent();
//                    intent.putExtra("nickname","");
//                    setResult(10,intent);
//                    finish();
//                    Toast.makeText(NickNameActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
//                    Log.e("gaodifha","修改失败");
//                }
//            }
//        });
    }
    //点击手机返回键返回上一级界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1=new Intent();
            intent1.putExtra("nickname","");
            setResult(10,intent1);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e("yjq123","杨家齐，昵称的更改界面");
    }
}
