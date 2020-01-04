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

import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by yexin on 19-12-8.
 */

public class stadiums_evalutes_adapter extends BaseAdapter {
    public LinkedList<userinfo_adapter> mData;
    private Context mContext;
    public stadiums_evalutes_adapter(LinkedList<userinfo_adapter> mData, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.stadiums_evalutes, parent, false);
            holder = new ViewHolder();

            holder.tv_username = (TextView) convertView.findViewById(R.id.tv_user_name);
            holder.tv_user_evalutes = (TextView) convertView.findViewById(R.id.tv_user_evalutes);

            /*holder.btn_order.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //界面跳转
                    Toast.makeText(mContext,"aaa",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mContext,main_stadiums.class);
                    mContext.startActivity(intent);
                }
            });*/
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tv_username.setText(mData.get(position).getUsername());
        holder.tv_user_evalutes.setText(mData.get(position).getUser_evalutes());
        return convertView;
    }
    static class ViewHolder{
        TextView tv_username;
        TextView tv_user_evalutes;


    }
}
