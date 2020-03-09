package com.example.yexin.menu6.Index;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ye xin on 2019/9/25.
 * 主界面滑动适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    private final List<Fragment> mFragmentList=new ArrayList<>();
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        /*return mFragmentList.size();*/
        return 3;
    }

    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }





}
