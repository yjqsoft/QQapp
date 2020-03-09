package com.example.yexin.menu6.Index;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yexin.menu6.R;

/**
 * Created by ye xin on 2019/10/31.
 * 二级导航栏运动选项适配器
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AuthorViewHolder>{

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.item, parent, false);
        AuthorViewHolder viewHolder = new AuthorViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }







    class AuthorViewHolder extends RecyclerView.ViewHolder {


        public AuthorViewHolder(View itemView) {
            super(itemView);




        }
    }


}
