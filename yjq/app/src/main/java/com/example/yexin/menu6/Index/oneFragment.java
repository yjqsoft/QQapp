package com.example.yexin.menu6.Index;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yexin.menu6.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * A simple {@link } subclass.
 */
public class oneFragment extends android.support.v4.app.Fragment implements OnBannerListener {

    private ArrayList<String> imageTitle;
    private ArrayList<Integer> imagePath;
    private TabLayout tabLayout = null;
    private ViewPager viewPager;
    private android.support.v4.app.Fragment[] mFragmentArrays = new android.support.v4.app.Fragment[4];
    private String[] mTabTitles = new String[4];

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

    //图像化banner
    private void initView_banner(){

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

    private void initView_tab(){

    }
    private void initDate_tab(){
        mTabTitles[0] = "羽毛球";
        mTabTitles[1] = "篮球";
        mTabTitles[2] = "足球";
        mTabTitles[3] = "网球";
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);
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
}
