package com.example.yexin.menu6.Person_Information.ModifyInfomation;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.Modify;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Person_Information.Activitys.NickNameActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
/**
 * Created by DELL on 2019/11/8.
 */

public class Modifyinfo {
    private String Nickname=null;
    private Activity activity=null;
    private boolean isReturn=false;
    public Modifyinfo(){}
    public Modifyinfo(String Nickname,Activity activity){
        this.Nickname=Nickname;
        this.activity=activity;
    }
    public boolean setNickname(String content,String model){
        //
    //String jsonObject= Web_Json.Login(UserName,UserPassword);
//    String jsonObject= Modify.setInfo(content,"3");
//    RequestParams params = new RequestParams(Web_url.URL_Modifyinfo);
//        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
//        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//        params.setBodyContent(jsonObject);//添加json内容到请求参数里
//        x.http().post(params, new Callback.CommonCallback<String>() {
//        /**
//         * 只要返回状态码就行，不需要别的东西
//         * @param result
//         */
//        private boolean isSuccess=false;
//        @Override
//        public void onSuccess(String result) {
//            Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
////               HashMap<String,String> map=Web_Json.getJson(result);
//            String code=null;
//            if(code.equals("200")){
//                isSuccess=true;
//            }
//            else{
//                isSuccess=false;
//            }
//        }
//        @Override
//        public void onError(Throwable ex, boolean isOnCallback) {
//            Log.e("yjq1","失败");
//            isReturn=false;
//            Toast.makeText(activity, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//        }
//        @Override
//        public void onCancelled(CancelledException cex) {
//            Log.e("yjq","取消");
//        }
//        @Override
//        public void onFinished() {
//            Log.e("yjq","完成");
//            NickNameActivity.Operation=false;
//            if(isSuccess){
//                Log.e("ahgia","修改成功");
//                isReturn=true;
//            }
//            else{
//                Log.e("gaodifha","修改失败");
//                isReturn=false;
//            }
//        }
//    });
//        return isReturn;
        return isReturn;
}
}
