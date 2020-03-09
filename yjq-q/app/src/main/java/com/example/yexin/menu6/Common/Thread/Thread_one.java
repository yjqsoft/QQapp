package com.example.yexin.menu6.Common.Thread;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.WebMoreInfomation;
import com.example.yexin.menu6.Common.Json.Web_Json;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.Login_logon.Login;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Login_logon.MoreInformation;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DELL on 2019/10/17.
 */

public class Thread_one implements Runnable {
    private HashMap<String,String> map;
    private Activity Main;
    private TextView UserName=null;
    private TextView UserLevel=null;
    private ImageView UserHeader=null;
    private User_public user_public=null;
    public Thread_one(Activity Main, TextView UserName, TextView UserLevel, ImageView UserHeader){
        this.Main=Main;
        this.UserName=UserName;
        this.UserLevel=UserLevel;
        this.UserHeader=UserHeader;
        user_public=new User_public(Main);
    }
    @Override
    public void run() {
        String jsonObject= Web_Json.Login_one(UserPublic.getUser(),UserPublic.getUser_str());
        RequestParams params = new RequestParams(Web_url.URL_Token);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        params.setBodyContent(jsonObject);//添加json内容到请求参数里
        x.http().post(params, new Callback.CommonCallback<String>() {
            private boolean isNees=true;
            @Override
            public void onSuccess(String result) {
                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
                HashMap<String,String> map=Web_Json.getJson(result);
                if(map.get("code").equals("200")){
                    Log.e("yjq_11","长连接没有失效，并且更新了长连接");
//                   SharedPreferences preferences = Main.getSharedPreferences("user",MODE_PRIVATE);
//                   SharedPreferences.Editor editor=preferences.edit();
//                   editor.putString("User_str",map.get("token"));
//                   editor.commit();
                    user_public.setUser_str(map.get("token"));
                    UserPublic.setUser_str(map.get("token"));
                    isNees=true;
                }
                else{
                    Toast.makeText(Main, "身份过期，请重新登入", Toast.LENGTH_SHORT).show();
//                   SharedPreferences preferences = Main.getSharedPreferences("user",MODE_PRIVATE);
//                   SharedPreferences.Editor editor=preferences.edit();
//                   editor.putBoolean("User_flag",false);
//                   editor.commit();
                    user_public.setUser_flag(false);
                    UserPublic.setUser_flag(false);
                    Intent intent = new Intent(Main,Login.class);
                    Main.startActivity(intent);
                    Main.finish();
                    Log.e("yjq_12","长连接失效了重新去登入");
                }
                Log.e("yjq_four","新的长连接：code"+map.get("code")+"message"+map.get("message")+"token"+map.get("token"));
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1","失败");
                isNees=false;
                Toast.makeText(Main, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");
                if(isNees){
                    Log.e("odgoahg","长连接没有失效获取更多信息");
                    MoreInformation moreInformation=new MoreInformation(UserPublic.getUser(),Main);
                    moreInformation.getInfomation();
                }
                else{
                    Log.e("ub","不用获取信息,可能是网络不行");
                }
            }
        });
    }
}
