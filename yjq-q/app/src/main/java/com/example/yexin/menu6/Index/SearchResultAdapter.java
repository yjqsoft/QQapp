package com.example.yexin.menu6.Index;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by 23646 on 2019/11/16.
 * 搜素结果适配器
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragmentone_stadiums, parent, false);
            holder = new ViewHolder();
            holder.tv_ballclub_name = (TextView) convertView.findViewById(R.id.tv_ballclub_name);
            holder.tv_appraise = (TextView) convertView.findViewById(R.id.tv_appraise);
             holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.btn_order=(Button)convertView.findViewById(R.id.btn_order);
           final String  name=holder.tv_ballclub_name.getText().toString();
            //在搜索的item中添加监听器   将数据传入场馆界面  实现跳转**12138
            holder.btn_order.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    /**/
                    Log.e("wz","hhj1"+mData.get(position).getNo());/*场馆编号获取到*/
                    Toast.makeText(mContext,"aaav"+mData.get(position).getNo(),Toast.LENGTH_SHORT).show();


                    Intent intent=new Intent(mContext,main_stadiums.class);
                    Log.e("wz","phone"+mData.get(position).getPhone());
                    intent.putExtra("position",""+position);
                    intent.putExtra("phone",mData.get(position).getPhone());
                    main_stadiums.setmData(mData,mContext);
                    //intent.setClass(mContext,mData);
                    mContext.startActivity(intent);
                }
            });
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
        Button btn_order;
    }
}
