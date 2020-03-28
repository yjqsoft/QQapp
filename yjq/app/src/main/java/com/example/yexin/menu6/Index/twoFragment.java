package com.example.yexin.menu6.Index;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yexin.menu6.Index.fragmenttwo_fourballs.Fragmenttwo_badminton;
import com.example.yexin.menu6.Index.fragmenttwo_fourballs.Fragmenttwo_basketball;
import com.example.yexin.menu6.Index.fragmenttwo_fourballs.Fragmenttwo_football;
import com.example.yexin.menu6.Index.fragmenttwo_fourballs.Fragmenttwo_tennis;
import com.example.yexin.menu6.R;

import java.util.ArrayList;

/**
 *
 * 第二主界面
 */
public class twoFragment extends android.support.v4.app.Fragment {
    private Context mcontext;
    private TabLayout twoTabLayout;
    private ViewPager twoViewPager;
    private ArrayList<Fragment> mFragmentArrays;

    private SwipeRefreshLayout swipeRefreshView;

    public twoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_two, container, false);
        twoTabLayout=(TabLayout)view.findViewById(R.id.fragmenttwo_slidingtabs);
        twoViewPager=(ViewPager)view.findViewById(R.id.fragmenttwo_viewpager);
        swipeRefreshView=(SwipeRefreshLayout)view.findViewById(R.id.gg_srfl);

        mFragmentArrays=new ArrayList<Fragment>();
        mcontext=view.getContext();
        //pulldownRefresh();

        mFragmentArrays.add(new Fragmenttwo_basketball());
        mFragmentArrays.add(new Fragmenttwo_badminton());
        mFragmentArrays.add(new Fragmenttwo_football());
        mFragmentArrays.add(new Fragmenttwo_tennis());
        MyViewPagerAdapter adapter=new MyViewPagerAdapter(getChildFragmentManager(),mFragmentArrays);
        twoViewPager.setAdapter(adapter);
        twoTabLayout.setupWithViewPager(twoViewPager);
        twoTabLayout.getTabAt(0).setCustomView(tab_icon("篮球",R.drawable.basketball));
        twoTabLayout.getTabAt(1).setCustomView(tab_icon("羽毛球",R.drawable.badminton));
        twoTabLayout.getTabAt(2).setCustomView(tab_icon("足球",R.drawable.football));
        twoTabLayout.getTabAt(3).setCustomView(tab_icon("网球",R.drawable.tennis));
        return view;
    }

//未使用刷新
    private void pulldownRefresh() {
        swipeRefreshView.setRefreshing(false);
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                //final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mList.add(0, "我是天才" + random.nextInt(100) + "号");
//                        mAdapter.notifyDataSetChanged();
                        try {
                            //http();  在这可以执行一些耗时间的程序
                        } catch (Exception e) {
                        }
                        mFragmentArrays.add(new Fragmenttwo_basketball());
                        mFragmentArrays.add(new Fragmenttwo_badminton());
                        mFragmentArrays.add(new Fragmenttwo_football());
                        mFragmentArrays.add(new Fragmenttwo_tennis());
                        //Toast.makeText(mContext, "刷新了一条数据", Toast.LENGTH_SHORT).show();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshView.setRefreshing(false);
                    }
                }, 1200);
            }

        });
    }
    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentss;

        public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragmentss=fragments;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentss.get(position);
        }


        @Override
        public int getCount() {
            return fragmentss.size();
        }
    }

    private View tab_icon(String name, int iconID){
        View newtab =  LayoutInflater.from(mcontext).inflate(R.layout.fragment_two_tabicons,null);
        TextView tv = (TextView) newtab.findViewById(R.id.tabtext);
        tv.setText(name);
        ImageView im = (ImageView)newtab.findViewById(R.id.tabicon);
        im.setImageResource(iconID);
        return newtab;
    }

    public static twoFragment newInstance() {

        Bundle args = new Bundle();

        twoFragment fragment = new twoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
