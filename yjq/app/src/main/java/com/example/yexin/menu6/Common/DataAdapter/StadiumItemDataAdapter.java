package com.example.yexin.menu6.Common.DataAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.SearchReasult;
import com.example.yexin.menu6.Index.fragmentone_stadiums_adapter;
import com.example.yexin.menu6.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedList;

/**
 * Created by 邱 on 2019/12/28.
 */

public class StadiumItemDataAdapter {

//    private fragmentone_stadiums_adapter mAdapter = null;
//    private JSONObject jsonObject;
//    private JSONArray jsonArr;
//    private LinkedList<SearchReasult> mData=null;
//
//    public void StadiumItemDataAdapter(String searchcontent,final Context m1Context){
//        mData=new LinkedList<SearchReasult>();
//        if(searchcontent==null)
//        {
//            RequestParams params = new RequestParams(Web_url.URL_Getmainactivity);
//            params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
//            params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//            //  params.setBodyContent(jsonObject);//添加json内容到请求参数里
//            x.http().post(params, new Callback.CommonCallback<String>() {
//                @Override
//                public void onSuccess(String result) {
//                    Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
////               HashMap<String,String> map=Web_Json.getJson(result);
//                    //HashMap<String,String> map=Web_Json.getJson(result);
//                    Log.e("yjqresult:",result.toString());
//
//                    try{
//                        //int jsonSize = result.length();//获取数据组的长度
//                        for(int i=0;i<result.length();i++){
//                            jsonArr=new JSONArray(result);
//                            jsonObject = (JSONObject)jsonArr.getJSONObject(i);
//                            Log.e("数据的变化",jsonObject.getString("场馆编号"));
//                            Log.e("数据的变化",jsonObject.getString("场馆名"));
//                            Log.e("数据的变化",jsonObject.getString("场馆地址"));
//                            mData.add(new SearchReasult(jsonObject.getString("场馆编号"),jsonObject.getString("场馆名"),
//                                    jsonObject.getString("场馆地址"),"<100","￥100",jsonObject.getString("场馆负责人"),
//                                    jsonObject.getString("负责人电话"),jsonObject.getString("场馆图片"),jsonObject.getString("场馆评价"),jsonObject.getString("场馆球类型"),/*球类型未添加*/jsonObject.getString("场馆服务"),
//                                    jsonObject.getString("场馆介绍"),jsonObject.getString("下单量"),jsonObject.getString("地板"),jsonObject.getString("灯光"),
//                                    jsonObject.getString("休息区"),jsonObject.getString("售卖"),
//                                    jsonObject.getString("体育用品售卖")));
//                        }
//                   /*
//                   * 此处不能运行*/
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onError(Throwable ex, boolean isOnCallback) {
//                    Log.e("yjq1","失败");
//                    Toast.makeText(m1Context, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//                }
//                @Override
//                public void onCancelled(CancelledException cex) {
//                    Log.e("yjq","取消");
//                }
//                @Override
//                public void onFinished() {
//                    Log.e("yjq","完成");
//                    mAdapter=new fragmentone_stadiums_adapter(mData,m1Context);
//                   // fragmentone_select_listview.setAdapter(mAdapter);
//                    //完成时候运行
//                }
//            });
//        }
//        if(searchcontent!=null) {
//            RequestParams params = new RequestParams(Web_url.URL_Getmainactivity);
//            params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
//            params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//            //  params.setBodyContent(jsonObject);//添加json内容到请求参数里
//            x.http().post(params, new Callback.CommonCallback<String>() {
//                @Override
//                public void onSuccess(String result) {
//                    Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
////               HashMap<String,String> map=Web_Json.getJson(result);
//                    //HashMap<String,String> map=Web_Json.getJson(result);
//                    Log.e("yjqresult:",result.toString());
//
//                    try{
//                        //int jsonSize = result.length();//获取数据组的长度
//                        for(int i=0;i<result.length();i++){
//                            jsonArr=new JSONArray(result);
//                            jsonObject = (JSONObject)jsonArr.getJSONObject(i);
//                            Log.e("数据的变化",jsonObject.getString("场馆编号"));
//                            Log.e("数据的变化",jsonObject.getString("场馆名"));
//                            Log.e("数据的变化",jsonObject.getString("场馆地址"));
//                            mData.add(new SearchReasult(jsonObject.getString("场馆编号"),jsonObject.getString("场馆名"),
//                                    jsonObject.getString("场馆地址"),"<100","￥100",jsonObject.getString("场馆负责人"),
//                                    jsonObject.getString("负责人电话"),jsonObject.getString("场馆图片"),jsonObject.getString("场馆评价"),jsonObject.getString("场馆球类型"),/*球类型未添加*/jsonObject.getString("场馆服务"),
//                                    jsonObject.getString("场馆介绍"),jsonObject.getString("下单量"),jsonObject.getString("地板"),jsonObject.getString("灯光"),
//                                    jsonObject.getString("休息区"),jsonObject.getString("售卖"),
//                                    jsonObject.getString("体育用品售卖")));
//                        }
//                   /*
//                   * 此处不能运行*/
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onError(Throwable ex, boolean isOnCallback) {
//                    Log.e("yjq1","失败");
//                   // Toast.makeText(mContext, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
//                }
//                @Override
//                public void onCancelled(CancelledException cex) {
//                    Log.e("yjq","取消");
//                }
//                @Override
//                public void onFinished() {
//                    Log.e("yjq","完成");
//                   // mAdapter=new fragmentone_stadiums_adapter(mData,mContext);
//                   // fragmentone_select_listview.setAdapter(mAdapter);
//                    //完成时候运行
//                }
//            });
//        }
//
//    }
}
