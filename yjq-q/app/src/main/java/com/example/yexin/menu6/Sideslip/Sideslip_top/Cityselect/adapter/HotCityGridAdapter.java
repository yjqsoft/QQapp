package com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yexin.menu6.R;
import com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.bean.City;

import java.util.List;

/**
 * Created by ye xin on 2019/10/13.
 * 热门城市列表适配器
 */

public class HotCityGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<City> mCities;

    public HotCityGridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<City> data) {
        mCities = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public Object getItem(int position) {
        return mCities == null ? null : mCities.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        HotCityViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.cp_item_hot_city_gridview, parent, false);
            holder = new HotCityViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_hot_city_name);
            view.setTag(holder);
        } else {
            holder = (HotCityViewHolder) view.getTag();
        }
        holder.name.setText(mCities.get(position).getName());
        return view;
    }

    public static class HotCityViewHolder {
        TextView name;
    }
}
