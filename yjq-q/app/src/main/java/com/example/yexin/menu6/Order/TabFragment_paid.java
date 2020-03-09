package com.example.yexin.menu6.Order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Public_class.User_public;
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
import java.util.List;

/**
 * Created by 23646 on 2019/11/5.
 */

public class TabFragment_paid extends Fragment{

    private List<Order> mData = null;
    private JSONObject jsonObject;
    private JSONArray jsonArr;
//    private static String data;
    public static Fragment newInstance() {
        TabFragment_paid fragment = new TabFragment_paid();
        return fragment;
    }

//    public  static void GetOrderData(String data) {
//        TabFragment_paid.data=data;
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_two, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mData = new LinkedList<Order>();
        InitData(recyclerView);
       //recyclerView.setAdapter(new RecyclerAdapter_two((LinkedList<Order>)mData,getActivity()));
        return rootView;

    }

    public void InitData(final RecyclerView recyclerView){


/**
 * 请求订单的信息*/

//        User_public user=new User_public();
//        params.setBodyContent(user.getUser());//添加json内容到请求参数里
        String Account="18879942330";//用户账号
        RequestParams params = new RequestParams(Web_url.URL_GetReserveOrder);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        try {
            jsonObject=new JSONObject();
            jsonObject.put("content",Account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.setBodyContent(jsonObject.toString());//添加json内容到请求参数里
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq","网络请求成功order: "+result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                //HashMap<String,String> map=Web_Json.getJson(result);
                Log.e("yjqresult:",result.toString());
                try{
                    //int jsonSize = result.length();//获取数据组的长度
                    for(int i=0;i<result.length();i++){
                        jsonArr=new JSONArray(result);
                        jsonObject = (JSONObject)jsonArr.getJSONObject(i);
                        if(jsonObject.getString("pay").equals("1")) {
                            mData.add( new Order(jsonObject.getString("id"),jsonObject.getString("appointmenttime"),
                                    jsonObject.getString("type"),jsonObject.getString("site"),
                                    jsonObject.getString("time"),jsonObject.getString("place"),
                                    jsonObject.getString("money"),jsonObject.getString("pay")));
                        }
                    }
                   /*
                   * 此处不能运行*/
                }catch (Exception e){
                    e.printStackTrace();
                }
                mData.add(new Order("121385","54644846","拍球","抚州","8:39~10:00","5号场","￥110.00","待支付"));
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1","失败");
                Toast.makeText(getContext(), "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");
                recyclerView.setAdapter(new RecyclerAdapter_two((LinkedList<Order>)mData,getActivity()));

                //完成时候运行
            }
        });



    }
}
