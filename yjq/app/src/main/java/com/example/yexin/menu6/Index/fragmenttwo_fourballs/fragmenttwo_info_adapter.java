package com.example.yexin.menu6.Index.fragmenttwo_fourballs;

import com.example.yexin.menu6.Index.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.Index.fragmenttwo_fourballs.fragmenttwo_info;
import com.example.yexin.menu6.Index.twoFragment;
import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by yexin on 20-3-15.
 */

public class fragmenttwo_info_adapter extends BaseAdapter {
    private LinkedList<fragmenttwo_info> mData;
    private Context mContext;

    public fragmenttwo_info_adapter(LinkedList<fragmenttwo_info> mData, Context mContext) {
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
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.fragmenttwo_detailinfo,parent,false);
            holder=new ViewHolder();
            holder.linear_area=(LinearLayout)convertView.findViewById(R.id.fragmenttwo_detailinfo_linear);
            holder.tv_detailinfo=(TextView)convertView.findViewById(R.id.fragmenttwo_detailtext_moreinfo);
            holder.tv_title=(TextView)convertView.findViewById(R.id.title);
            holder.tv_time=(TextView)convertView.findViewById(R.id.fragmenttwo_detailtext_time);
            holder.linear_area.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //界面跳转
                    Intent intent_detail = new Intent(mContext,fragmenttwo_balls_details.class);
                    intent_detail.putExtra("gamedetailsid",mData.get(position).getGamedetailsid());
                    intent_detail.putExtra("title",mData.get(position).getTv_title());
                   // intent_detail.putExtra("phone",mData.get(position).getPhone());
                    mContext.startActivity(intent_detail);
                    if (Activity.class.isInstance(mContext)) {
                        // 转化为activity，然后finish就行了
                        Activity activity = (Activity) mContext;
                        activity.finish();
                    }
                    //Toast.makeText(mContext,"跳转成功",Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.tv_title.setText(mData.get(position).getTv_title());
        holder.tv_time.setText(mData.get(position).getTv_time());
        holder.tv_detailinfo.setText(mData.get(position).getTv_detailinfo());
        return convertView;
    }


    static class ViewHolder{
        TextView tv_title;
        TextView tv_detailinfo;
        TextView tv_time;
        LinearLayout linear_area;
    }
}
