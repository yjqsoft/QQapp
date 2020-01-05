package com.example.yexin.menu6.Index;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.BallClub_Information.Item.SelectDialog;
import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by yexin on 19-12-7.
 * 场馆主界面球类数据适配器
 */

public class stadiums_balls_adapter extends BaseAdapter {
    public LinkedList<stadiums_balls> mData;
    private Context mContext;
    public stadiums_balls_adapter(LinkedList<stadiums_balls> mData, Context mContext) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.stadiums_balls, parent, false);
            holder = new ViewHolder();

            holder.tv_ballclub_ballname = (TextView) convertView.findViewById(R.id.tv_ballclub_ballname);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_ballclub_ballsprice);
            holder.btn_order=(Button)convertView.findViewById(R.id.btn_order);
            holder.btn_order.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //界面跳转
                    final SelectDialog selectDialog=new SelectDialog(mContext);
                    selectDialog.show();
                    //Toast.makeText(mContext,"那个第一步",Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(mContext,main_stadiums.class);
                    //mContext.startActivity(intent);
                }
            });
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();

        }

        holder.tv_ballclub_ballname.setText(mData.get(position).getBallclub_ballname());
        holder.tv_price.setText(mData.get(position).getBallclub_prices());
        return convertView;

    }
    static class ViewHolder{
        TextView tv_ballclub_ballname;
        TextView tv_price;
        Button btn_order;

    }
}
