package com.example.yexin.menu6.Index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by 23646 on 2019/11/16.
 */

public class SearchResultAdapter extends BaseAdapter{
    private LinkedList<SearchReasult> mData;
    private Context mContext;
    public SearchResultAdapter(LinkedList<SearchReasult> mData, Context mContext) {
        this.mData=mData;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_item, parent, false);
            holder = new ViewHolder();
            holder.tv_ballclub_name = (TextView) convertView.findViewById(R.id.tv_ballclub_name);
            holder.tv_appraise = (TextView) convertView.findViewById(R.id.tv_appraise);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv_ballclub_name.setText(mData.get(position).getBallclub_name());
        holder.tv_appraise.setText(mData.get(position).getAppraise());
        holder.tv_address.setText(mData.get(position).getAddress());
        holder.tv_distance.setText(mData.get(position).getDistance());
        holder.tv_price.setText(mData.get(position).getPrice());
        return convertView;
    }

    static class ViewHolder{
        TextView tv_ballclub_name;
        TextView tv_appraise;
        TextView tv_address;
        TextView tv_distance;
        TextView tv_price;
    }
}
