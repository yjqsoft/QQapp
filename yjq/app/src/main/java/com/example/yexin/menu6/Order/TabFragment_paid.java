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
    public static Fragment newInstance() {
        TabFragment_paid fragment = new TabFragment_paid();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_two, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mData = new LinkedList<Order>();
        InitData();
        recyclerView.setAdapter(new RecyclerAdapter_two((LinkedList<Order>)mData,getActivity()));
        return rootView;

    }

    public void InitData(){


/**
 * 请求订单的信息*/
        User_public user=new User_public();
        RequestParams params = new RequestParams(Web_url.URL_Getgymnasium);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        params.setBodyContent(user.getUser());//添加json内容到请求参数里
        //Log.e("user",user.getUser());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                //HashMap<String,String> map=Web_Json.getJson(result);
                Log.e("yjqresult:",result.toString());

               /* try{
                    jsonObject=new JSONObject(result.toString());
                    num=jsonObject.getString("场馆编号");
                    name=jsonObject.getString("场馆名");
                    address=jsonObject.getString("场馆地址");
                    score=jsonObject.getString("场馆评价");
                    Log.e("数据的变化其中",name+"ss");
                    Log.e("数据的变化其中",address+"ss");
                    Log.e("数据的变化",jsonObject.getString("场馆编号"));
                    Log.e("数据的变化",jsonObject.getString("场馆名"));
                    Log.e("数据的变化",jsonObject.getString("场馆地址"));
                    mData.add(new SearchReasult(name,"五颗心"+score,address,"<100","￥100"));
                    mAdapter=new fragmentone_stadiums_adapter(mData,mContext);
                    fragmentone_select_listview.setAdapter(mAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }*/


            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1","失败");
                //Toast.makeText(, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");
                //完成时候运行
            }
        });




        mData.add(new Order("5155","54644846","篮球","南昌","8:39~10:00","5"+"号场","￥"+"110.00","已支付"));
        mData.add(new Order("5155","54644846","足球","福州","8:39~10:00","5号场","￥"+"110.00","已支付"));
        mData.add(new Order("5155","54644846","拍球","抚州","8:39~10:00","5号场","￥"+"110.00","已支付"));


    }
}
