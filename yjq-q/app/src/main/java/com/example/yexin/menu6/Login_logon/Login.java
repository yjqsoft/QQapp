package com.example.yexin.menu6.Login_logon;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.Web_Json;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class Login extends Activity {
    private TextView tv_register;
    private EditText text_username;
    private EditText text_password;
    private Button button_submit;
    private String UserName=null;
    private String UserPassword=null;
    private User_public user_public=null;

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS,Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE})
    public void getSingle() {
        Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
    }
    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS,Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE})
    public void showSingleRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("为了正常使用，将获取您的一些权限信息")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//继续执行请求
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();//取消执行请求
            }
        }).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LoginPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user_public=new User_public(this);
        setContentView(R.layout.activity_login);
        LoginPermissionsDispatcher.getSingleWithPermissionCheck(this);
        Layout_init();
    }
    public void Layout_init(){
        tv_register = (TextView)findViewById(R.id.tv_register);
        text_username=(EditText)findViewById(R.id.et_username);
        text_password=(EditText)findViewById(R.id.et_password);
        button_submit=(Button)findViewById(R.id.btn_login);
        button_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(text_username.getText().toString())&& !TextUtils.isEmpty(text_password.getText().toString())) {
                    Log.e("WangJ", "都不空");
                    UserName=text_username.getText().toString();
                    UserPassword=text_password.getText().toString();
                    /**
                     * 点击登入，账号密码都不为空的时候，发起网络请求
                     */
                    String jsonObject= Web_Json.Login(UserName,UserPassword);
                    RequestParams params = new RequestParams(Web_url.URL_Login);
                    params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
                    params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
                    params.setBodyContent(jsonObject);//添加json内容到请求参数里
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                            HashMap<String,String> map=Web_Json.getJson(result);
                            user_public.setUser_flag(true);
                            UserPublic.setUser_flag(true);
                            user_public.setUser(UserName);
                            UserPublic.setUser(UserName);
                            user_public.setUser_str(map.get("token"));
                            UserPublic.setUser_str(map.get("token"));
                            user_public.setIcon(map.get("Picture"));
                            UserPublic.setIcon(map.get("Picture"));
                            user_public.setUser_n(map.get("NickName"));
                            UserPublic.setUser_n(map.get("NickName"));
                            user_public.setUser_level(map.get("Level"));
                            UserPublic.setUser_level(map.get("Level"));
                            Log.e("yjq1", "编码:"+map.get("code")+map.get("message"+map.get("token")));
                        }
                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Log.e("yjq1","失败");
                            Toast.makeText(Login.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCancelled(CancelledException cex) {
                            Log.e("yjq","取消");
                        }
                        @Override
                        public void onFinished() {
                            Log.e("yjq","完成");
                            user_public.setFirst(false);  //是从登入去的，不需要更新长连接
                            UserPublic.setFirst(false);
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
//                    RequestParams params = new RequestParams(Web_url.URL_Login);
//                    Log.w("WangJ", "运行在这个ok");
//                    String jsonObject= Web_Json.Login(UserName,UserPassword);
//                    Log.e("Json", jsonObject);
//                    params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
//                    params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//                    params.setBodyContent(jsonObject);//添加json内容到请求参数里
//                    x.http().post(params, new Callback.CommonCallback<String>() {
//                        @Override
//                        public void onSuccess(String result) {
//                            Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//                            HashMap<String,String> map=Web_Json.getJson(result);
//                            User_public.setUser_flag(true);
//                            User_public.setUser(UserName);
//                            User_public.setUser_str(map.get("token"));
////                            SharedPreferences preferences=getSharedPreferences("user",MODE_PRIVATE);
////                            SharedPreferences.Editor editor=preferences.edit();
////                            editor.putBoolean("User_flag",true);  //用户在登入状态
////                            editor.putString("User",UserName);  //用户的账号信息
////                            editor.putString("Icon",null);  //用户头像的地址
////                            editor.putString("User_str",map.get("token"));  //用户登入的长连接
////                            editor.putString("Icon_time",null);  //用户的头像缓存标识
////                            editor.commit();  //提交信息
//                            /**
//                             * 先不保存头像，登入成功后，改变状态并获取更多的个人信息
//                             */
////                            if(preferences.getString("Icon_time",null).equals(map.get("IconTime"))){
////                                editor.putBoolean("User_flag",true);  //用户在登入状态
////                                editor.putString("User",UserName);  //用户的账号信息
////                                editor.putString("Icon",null);  //用户头像的地址
////                                editor.putString("User_str",map.get("token"));  //用户登入的长连接
////                                editor.putString("Icon_time",null);  //用户的头像缓存标识
////                                editor.commit();  //提交信息
////                            }
////                            else{
////                                /**
////                                 * 更换头像
////                                 */
////
////                                editor.putBoolean("User_flag",true);  //用户在登入状态
////                                editor.putString("User",UserName);  //用户的账号信息
////                                editor.putString("Icon",null);  //用户头像的地址
////                                editor.putString("User_str",map.get("token"));  //用户登入的长连接
////                                editor.putString("Icon_time",null);  //用户的头像缓存标识
////                                editor.commit();  //提交信息
////                            }
////                            User_public.setUser(preferences.getString("User",UserName));  //公共类用户信息
////                            User_public.setUser_flag(preferences.getBoolean("User_flag",true));  //公共类用户状态
////                            User_public.setUser_str(preferences.getString("User_str",map.get("token")));  //公共类用户信息
//                            User_public.setFirst(false);  //是从登入去的，不需要更新长连接
//                            Log.e("yjq1", "编码:"+map.get("code")+map.get("message"+map.get("token")));
//                            Intent intent = new Intent(Login.this,MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        @Override
//                        public void onError(Throwable ex, boolean isOnCallback) {
//                            Toast.makeText(Login.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//                            Log.e("yjq1","失败");
//                        }
//                        @Override
//                        public void onCancelled(CancelledException cex) {
//                            Log.e("yjq","取消");
//                        }
//                        @Override
//                        public void onFinished() {
//                            Log.e("yjq","完成");
//                        }
//                    });
                } else {
//                    Intent intent = new Intent(Login.this,MainActivity.class);
//                    startActivity(intent);
//                    finish();
                    Log.e("WangJ", "都空");
                    Toast.makeText(Login.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onClick(View view){
        Intent intent = new Intent(Login.this,Register.class);
        startActivity(intent);
        finish();
    }
}
