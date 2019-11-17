//package com.example.yexin.menu6.Common.Xutil3;
//
//import android.content.Intent;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.yexin.menu6.Common.Json.Web_Json;
//import com.example.yexin.menu6.Common.Public_class.User_public;
//import com.example.yexin.menu6.Common.Thread.Thread_one;
//import com.example.yexin.menu6.Common.Url.Web_url;
//import com.example.yexin.menu6.Index.MainActivity;
//import com.example.yexin.menu6.Login_logon.Login;
//
//import org.xutils.common.Callback;
//import org.xutils.http.RequestParams;
//import org.xutils.x;
//
//import java.util.HashMap;
//
///**
// * Created by DELL on 2019/10/28.
// * 对Xutil3进行封装
// */
//
//public class Xutil {
//
//    private static final  int SUCCESS=1;//请求网络成功
//    private  static final  int FAILED=0;//请求网络失败
//    public  static int Flag=-1;
//    private  static XutilThread xutilThread=null;
//    private  static Thread thread=null;
//    private static String jsonObject=null;
//    private static String Url=null;
//
//    public  static void setXutil(String jsonObject,String Url){
//        Log.e("czw12","setXutil,马上循环");
////        RequestParams params = new RequestParams(Url);
////        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
////        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
////        params.setBodyContent(jsonObject);//添加json内容到请求参数里
////        x.http().post(params, new Callback.CommonCallback<String>() {
////            @Override
////            public void onSuccess(String result) {
////                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//////               HashMap<String,String> map=Web_Json.getJson(result);
////                xutilThread.setResult(result);
////            }
////            @Override
////            public void onError(Throwable ex, boolean isOnCallback) {
////                Log.e("yjq1","失败");
////            }
////            @Override
////            public void onCancelled(CancelledException cex) {
////                Log.e("yjq","取消");
////            }
////            @Override
////            public void onFinished() {
////                Log.e("yjq","完成");
////                XutilThread.Filash=false;
////            }
////        });
////        while(XutilThread.Filash){
////            Log.e("aga",""+XutilThread.Filash+"reslut"+XutilThread.Result);
////            try{
////                Thread.sleep(10000);
////            }catch (Exception e){
////                e.printStackTrace();
////            }
////        }
//        Log.e("xez","循环退出");
//        Log.e("adg","返回的result"+ XutilThread.getResult());
//    }
//}
