package com.example.yexin.menu6.Index;


import java.lang.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yexin.menu6.Common.Json.Web_Json;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Refresh.BaiDuRefreshListView;
import com.example.yexin.menu6.Common.Refresh.RefreshDialog;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Login_logon.Login;
import com.example.yexin.menu6.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link } subclass.
 */
public class oneFragment extends android.support.v4.app.Fragment implements OnBannerListener {
    //mData,mContext,mAdapter,fragmentone_select_listview 演示数据，界面，数据适配，列表
    private LinkedList<SearchReasult> mData=null;
    private LinkedList<onefragment_tabinfo> mData2=null;
    private static Context mContext;
    private fragmentone_stadiums_adapter mAdapter = null;
    private ListView fragmentone_select_listview;
    private ArrayList<String> imageTitle;
    private ArrayList<Integer> imagePath;
    private TabLayout tabLayout = null;
    private ViewPager viewPager;
    private android.support.v4.app.Fragment[] mFragmentArrays = new android.support.v4.app.Fragment[4];
    private String[] mTabTitles = new String[4];


    /*
    * JSON数据的保存JSONObject jsonObject*/
    private JSONObject jsonObject;
    private JSONArray jsonArr;
    private String num;
    public String name;
    private static String address;
    private String score;

    private SwipeRefreshLayout swipeRefreshView;
    private RefreshDialog refreshDialog;
    private int Refresh_status=2;
    private final int Refresh_Success=1;
    private final int Refresh_Fail=0;

    public oneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_one, container, false);
        Banner banner=(Banner)view.findViewById(R.id.banner);
        tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        viewPager = (ViewPager)view.findViewById(R.id.tab_viewpager);
        swipeRefreshView=(SwipeRefreshLayout)view.findViewById(R.id.gg_srfl);

        mContext=view.getContext();
        fragmentone_select_listview=(ListView)view.findViewById(R.id.fragmentone_select_listview);
        mData=new LinkedList<SearchReasult>();

        //下拉刷新数据

        //**/

        /*
        * 将数据获取出来 **12138
        * **/

        //String jsonObject= Web_Json.Login(UserName,UserPassword);
        pulldownRefresh();
        refreshContent();
        /*
        * 先运行外边的数据， 在运行返回的数据结果集result所以交换顺序  qzj*/
        //mData.add(new SearchReasult(name,"五颗心"+score,address,"<100","￥100"));
       /* mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));*/
        //mAdapter=new fragmentone_stadiums_adapter(mData,mContext);
        //fragmentone_select_listview.setAdapter(mAdapter);

        initDate_banner();
        initDate_tab();
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imagePath);
        //设置监听
        banner.setOnBannerListener(this);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(imageTitle);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        Log.w("base","initDate()执行中8");
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        Log.w("base","initDate()执行中9");
        //banner设置方法全部调用完毕时最后调用
        banner.isAutoPlay(true);
        //start必须放在最后
        banner.start();
        return view;


    }

    private void http(){
        Log.e("one","网络请求中用户id:"+UserPublic.getUser());

        RequestParams params = new RequestParams(Web_url.URL_Getmainactivity);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
//        try {
//            jsonObject=new JSONObject();
//            jsonObject.put("userid",UserPublic.getUser());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//       params.setBodyContent(jsonObject.toString());//添加json内容到请求参数里
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
                    if(jsonArr.length()>0){
                        mData=null;
                        mData = new LinkedList<SearchReasult>();
                    }
                    for(int i=0;i<jsonArr.length();i++){

                        Log.e("yjqresult:","jsonArr长度："+jsonArr.length());
                        jsonObject = (JSONObject)jsonArr.getJSONObject(i);
                        Log.e("yjqresult:",i+"jsonObject："+jsonObject.toString());
                        Log.e("数据的变化",jsonObject.getString("场馆编号"));
                        Log.e("数据的变化",jsonObject.getString("场馆名"));
                        Log.e("数据的变化",jsonObject.getString("场馆地址"));
                        mData.add(new SearchReasult(jsonObject.getString("场馆编号"),jsonObject.getString("场馆名"),
                                jsonObject.getString("场馆地址"),"距离<100","￥100",jsonObject.getString("场馆负责人"),
                                jsonObject.getString("负责人电话"),jsonObject.getString("场馆图片"),jsonObject.getString("场馆评价"),jsonObject.getString("场馆球类型"),/*球类型未添加*/jsonObject.getString("场馆服务"),
                                jsonObject.getString("场馆介绍"),jsonObject.getString("下单量"),jsonObject.getString("地板"),jsonObject.getString("灯光"),
                                jsonObject.getString("休息区"),jsonObject.getString("售卖"),
                                jsonObject.getString("体育用品售卖"),jsonObject.getString("坐标")));
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
                Toast.makeText(mContext, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
                refreshDialog.dismiss();
                swipeRefreshView.setRefreshing(false);
                Refresh_status=Refresh_Fail;
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");
                mData.add(new SearchReasult("12138","小邱球馆",
                        "福田15号","距离<100","￥100","邱在杰",
                        "15112347784","无","6分","AB",/*球类型未添加*/"无",
                        "无","无","水泥地","无",
                        "无","无",
                        "无","0,0"));



                mAdapter=new fragmentone_stadiums_adapter(mData,mContext);
                fragmentone_select_listview.setAdapter(mAdapter);
                swipeRefreshView.setRefreshing(false);
                refreshDialog.dismiss();
                Refresh_status=Refresh_Success;
                //完成时候运行
            }
        });
    }

    private void refreshContent() {
        refreshDialog = new RefreshDialog(mContext,"正在加载...",R.mipmap.ic_dialog_loading);
        refreshDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    http();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //在之中可以终止线程
                    }
                });
            }
        }).start();
    }
    private void pulldownRefresh() {
        swipeRefreshView.setRefreshing(false);
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行

                //final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mList.add(0, "我是天才" + random.nextInt(100) + "号");
//                        mAdapter.notifyDataSetChanged();
                        try {
                            http();
                        } catch (Exception e) {
                        }
                       // Toast.makeText(mContext, "刷新了一条数据", Toast.LENGTH_SHORT).show();

                        // 加载完数据设置为不刷新状态，将下拉进度收起来

                    }
                }, 1200);
            }

        });
    }
    //初始数据banner
    private void initDate_banner(){
        imagePath=new ArrayList<>();
        imageTitle=new ArrayList<>();
        imagePath.add(R.drawable.banner1);
        imagePath.add(R.drawable.banner2);
        imagePath.add(R.drawable.banner3);
        imageTitle.add("第一张");
        imageTitle.add("第二张");
        imageTitle.add("第三张");
    }

    //banner监听事件
    @Override
    public void OnBannerClick(int position) {
        switch (position){
            case 0:
                Toast.makeText(getActivity(),"第一个",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getActivity(),"第二个",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getActivity(),"第三个",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //重写图片加载器
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    private void initDate_tab(){
        mTabTitles[0] = "羽毛球";
        mTabTitles[1] = "篮球";
        mTabTitles[2] = "足球";
        mTabTitles[3] = "网球";
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        mFragmentArrays[0] = TabFragment.newInstance();
        mFragmentArrays[1] = TabFragment.newInstance();
        mFragmentArrays[2] = TabFragment.newInstance();
        mFragmentArrays[3] = TabFragment.newInstance();

        PagerAdapter pagerAdapter=new MyViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(viewPager);
    }




    public static oneFragment newInstance() {

        Bundle args = new Bundle();

        oneFragment fragment = new oneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*final class MyViewPagerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }*/

    final class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }





//    public void login(View view){
//        refreshContent();
//    }
}
