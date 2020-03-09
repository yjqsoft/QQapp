package com.example.yexin.menu6.Index;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by yexin on 19-12-7.
 * 主界面推荐场馆数据适配器
 */

public class fragmentone_stadiums_adapter extends BaseAdapter {
    public LinkedList<SearchReasult> mData;
    private Context mContext;
    private String name;

    public fragmentone_stadiums_adapter(LinkedList<SearchReasult> mData, Context mContext) {
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
    //在适配器中进行数据适配**12138
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragmentone_stadiums, parent, false);
            holder = new ViewHolder();
            holder.tv_ballclub_name = (TextView) convertView.findViewById(R.id.tv_ballclub_name);
            holder.tv_appraise = (TextView) convertView.findViewById(R.id.tv_appraise);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.btn_order=(Button)convertView.findViewById(R.id.btn_order);
             name=holder.tv_ballclub_name.getText().toString();
            //添加监听器   将数据传入 场馆界面中去 **12138
            holder.btn_order.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    /*获取当前适配条 字段  待解决*/
                    Log.e("wz","hhj1"+mData.get(position).getNo());/*场馆编号获取到*/
                    Toast.makeText(mContext,"aaav"+mData.get(position).getNo(),Toast.LENGTH_SHORT).show();



                    Intent intent=new Intent(mContext,main_stadiums.class);
                    Log.e("wz","position"+position);
                    intent.putExtra("position",""+position);
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
