package com.example.yexin.menu6.Sideslip.Sideslip_top.Cityselect.adapter;

import android.content.Context;
import android.util.Log;
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
 *  搜索城市列表适配器，此处未用，要用的时候可以加上
 */

public class ResultListAdapter extends BaseAdapter {
    private Context mContext;
    private List<City> mCities;
    private LayoutInflater inflater;
    private CityListAdapter.OnCityClickListener onCityClickListener;

    public ResultListAdapter(Context mContext, List<City> mCities) {
        this.mCities = mCities;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void changeData(List<City> list) {
        if (mCities == null) {
            mCities = list;
        } else {
            mCities.clear();
            mCities.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public City getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Log.w("base适配器中1",mCities.get(0).getName());
        ResultViewHolder holder;
        if (view == null) {
           // view = LayoutInflater.from(mContext).inflate(R.layout.cp_activity_city_list, parent, false);
            view = LayoutInflater.from(mContext).inflate(R.layout.cp_item_search_result_listview, parent, false);
            holder = new ResultViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_item_result_listview_name);
            view.setTag(holder);
        } else {
            holder = (ResultViewHolder) view.getTag();
        }
        holder.name.setText(mCities.get(position).getName());

       /* view = inflater.inflate(R.layout.cp_item_search_result_listview, parent, false);
        TextView gridView=(TextView)view.findViewById(R.id.tv_item_result_listview_name);
        final ResultListAdapter mResultListAdapter = new ResultListAdapter(mContext,mCities);
        gridView.setAdapter(mResultListAdapter);
        mResultListAdapter.setData(mCities);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onCityClickListener != null) {
                    onCityClickListener.onCityClick(hotCityGridAdapter.getItem(position).toString());
                }
            }
        });*/
        return view;
    }

    public static class ResultViewHolder {
        TextView name;
    }
}
