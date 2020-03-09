package com.example.yexin.menu6.Order;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by 23646 on 2019/11/3.
 */

 class MyViewPagerAdapter extends FragmentPagerAdapter {
    private android.support.v4.app.Fragment[] mFragmentArrays;
    private String[] mTabTitles;
    public MyViewPagerAdapter(FragmentManager fm, android.support.v4.app.Fragment[] mFragmentArrays, String[] mTabTitles) {
        super(fm);
        this.mFragmentArrays=mFragmentArrays;
        this.mTabTitles=mTabTitles;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return mFragmentArrays[position];
    }

   /* @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }*/

    @Override
    public int getCount() {
        return mFragmentArrays.length;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
