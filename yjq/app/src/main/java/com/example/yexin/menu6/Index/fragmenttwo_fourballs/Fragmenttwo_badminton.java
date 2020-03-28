package com.example.yexin.menu6.Index.fragmenttwo_fourballs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.MainActivity;
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
 *羽毛球赛事
 */
public class Fragmenttwo_badminton extends Fragment {
    private LinkedList<fragmenttwo_info> mData=null;
    private fragmenttwo_info_adapter adapter=null;
    private ListView fragmenttwo_listview_detailinfo;
    private Context context;
    private JSONArray jsonArr;
    private JSONObject jsonObject;
    public Fragmenttwo_badminton() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragmenttwo_badminton, container, false);
        fragmenttwo_listview_detailinfo=(ListView)view.findViewById(R.id.fragmenttwo_listview_badmintoninfo);
        mData=new LinkedList<fragmenttwo_info>();


        //获取数据

        RequestParams params = new RequestParams(Web_url.URL_Getgame);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        //  params.setBodyContent(jsonObject);//添加json内容到请求参数里
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                //HashMap<String,String> map=Web_Json.getJson(result);
                Log.e("yjqresult:",result.toString());
                Log.e("yjqresult:","长度："+result.length());
                try{
                    //int jsonSize = result.length();//获取数据组的长度
                    jsonArr=new JSONArray(result);
                    for(int i=0;i<jsonArr.length();i++){

                        Log.e("yjqresult:","jsonArr长度："+jsonArr.length());
                        jsonObject = (JSONObject)jsonArr.getJSONObject(i);
                        Log.e("yjqresult:",i+"jsonObject："+jsonObject.toString());
                        Log.e("数据的变化",jsonObject.getString("id"));
                        Log.e("数据的变化",jsonObject.getString("type"));
                        Log.e("数据的变化",jsonObject.getString("title"));
                        if(jsonObject.getString("type").equals("A")){
                            mData.add(new fragmenttwo_info(jsonObject.getString("title"),jsonObject.getString("time"),jsonObject.getString("content"),
                                    jsonObject.getString("pic"),jsonObject.getString("gamedetailsid")));

                        }
                    }
                   /*
                   * 此处不能运行*/
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1","失败");
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");
                adapter=new fragmenttwo_info_adapter(mData,view.getContext());
                fragmenttwo_listview_detailinfo.setAdapter(adapter);
                //完成时候运行
            }
        });
//        mData.add(new fragmenttwo_info("一起来打羽毛球吧，我们","2020/3/9"));
//        mData.add(new fragmenttwo_info("一起来打羽毛球吧，我们","2020/3/10"));
//        mData.add(new fragmenttwo_info("一起来打羽毛球吧，我们","2020/3/11"));
        return view;
    }

    public static Fragmenttwo_badminton newInstance() {
        
        Bundle args = new Bundle();
        
        Fragmenttwo_badminton fragment = new Fragmenttwo_badminton();
        fragment.setArguments(args);
        return fragment;
    }

}
