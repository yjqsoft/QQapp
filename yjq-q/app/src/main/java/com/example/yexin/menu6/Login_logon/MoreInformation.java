package com.example.yexin.menu6.Login_logon;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.WebMoreInfomation;
import com.example.yexin.menu6.Common.Json.Web_Json;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.MainActivity;
import com.mob.wrappers.AnalySDKWrapper;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;


/**
 * Created by DELL on 2019/10/28.
 * 拉取详细信息
 */

public class MoreInformation {
    private String UserName=null;
    private Activity Mian=null;
    private User_public user_public=null;
    private View viewone=null;
    private TextView UserNam=null;
    private TextView UserLeve=null;
    private ImageView UserHeade=null;
    public MoreInformation(){
    }
    public MoreInformation(String UserName, Activity Main){
        this.UserName=UserName;
        this.Mian=Main;
    }
   public void getInfomation(){
       user_public=new User_public(Mian);
       RequestParams params = new RequestParams(Web_url.URL_MoreInfomation);
       String jsonObject= WebMoreInfomation.getInfomation(UserPublic.getUser());
       Log.e("Json", jsonObject);
       params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
       params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
       params.setBodyContent(jsonObject);//添加json内容到请求参数里
       x.http().post(params, new Callback.CommonCallback<String>() {
           private boolean isTrue=false;
           private String result=null;
           @Override
           public void onSuccess(String result) {
               //HashMap<String,String> map= Web_Json.getJson(result);
               Log.e("ogang","获取的结果为："+result);
               this.result=result;
               isTrue=true;
              //Log.e("数据","昵称："+UserPublic.getUser_n()+"签名："+UserPublic.getUser_q()+"等级："+UserPublic.getUser_level()+"性别："+UserPublic.getUser_sex()+"时间："+UserPublic.getUser_datetime()+"地点："+UserPublic.getUser_place()+"头像"+UserPublic.getIcon());
           }
           @Override
           public void onError(Throwable ex, boolean isOnCallback) {
               Log.e("dfag","错误");
               isTrue=false;
           }
           @Override
           public void onCancelled(CancelledException cex) {
           }
           @Override
           public void onFinished() {
               if(isTrue){
                   HashMap<String,String> map=WebMoreInfomation.getInfomationone(result);
                   user_public.setUser_n(map.get("NickName"));
                   UserPublic.setUser_n(map.get("NickName"));
                   user_public.setUser_q(map.get("Signature"));
                   UserPublic.setUser_q(map.get("Signature"));
                   user_public.setUser_level(map.get("Level"));
                   UserPublic.setUser_level(map.get("Level"));
                   user_public.setUser_sex(map.get("Sex"));
                   UserPublic.setUser_sex(map.get("Sex"));
                   user_public.setUser_datetime(map.get("Datetime"));
                   UserPublic.setUser_datetime(map.get("Datetime"));
                   user_public.setUser_place(map.get("Place"));
                   UserPublic.setUser_place(map.get("Place"));
                   user_public.setIcon(map.get("Picture"));
                   UserPublic.setIcon(map.get("Picture"));
                   Log.e("dgoadghoaij",map.toString());
               }else{

              }
               Log.e("ogaoidgo","昵称："+UserPublic.getUser_n()+"签名："+UserPublic.getUser_q()+"等级："+UserPublic.getUser_level()+"性别："+UserPublic.getUser_sex()+"时间："+UserPublic.getUser_datetime()+"地区："+UserPublic.getUser_place());
           }
       });
   }
}
