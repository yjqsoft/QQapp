package com.example.yexin.menu6.Sideslip.Sideslip_center.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by DELL on 2019/10/17.
 */

public class AccountAdapter extends BaseAdapter {
    private LinkedList<Account> mData;
    private Context mContext;

    public AccountAdapter(LinkedList<Account> mData,Context mContext){
        this.mData = mData;
        this.mContext = mContext;
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
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.account_list_item, parent, false);
            holder = new ViewHolder();
            holder.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.iv_verify = (ImageView) convertView.findViewById(R.id.iv_verify);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.iv_head.setBackgroundResource(mData.get(position).getIcon_head());
        holder.tv_phone.setText(mData.get(position).getPhone());
        holder.iv_verify.setBackgroundResource(mData.get(position).getIcon_verify());
        return convertView;
    }
    static class ViewHolder{
        ImageView iv_head;
        TextView tv_phone;
        ImageView iv_verify;
    }
}
