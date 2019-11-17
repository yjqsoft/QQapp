package com.example.yexin.menu6.Order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yexin.menu6.R;

import java.util.LinkedList;

/**
 * Created by 23646 on 2019/11/5.
 */

public class RecyclerAdapter_two extends RecyclerView.Adapter<RecyclerAdapter_two.AuthorViewHolder> {
    private LinkedList<Order> mData;
    private Context mContext;

    public RecyclerAdapter_two(LinkedList<Order> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.item_paid, parent, false);
        AuthorViewHolder viewHolder = new AuthorViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        holder.mOrderNumber.setText(mData.get(position).getOrder_num());
        holder.mOrderTime.setText(mData.get(position).getOrder_time());
        holder.mOrderType.setText(mData.get(position).getOrder_type());
        holder.mOrderLocation.setText(mData.get(position).getOrder_location());
        holder.mOrderAppointmentTime.setText(mData.get(position).getOrder_appointment_time());
        holder.mOrderSiteNumber.setText(mData.get(position).getOrder_site_num());
        holder.mOrderPrice.setText(mData.get(position).getOrder_price());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView mOrderNumber;
        TextView mOrderTime;
        TextView mOrderType;
        TextView mOrderLocation;
        TextView mOrderAppointmentTime;
        TextView mOrderSiteNumber;
        TextView mOrderPrice;
        Button mBtnPay;
        public AuthorViewHolder(View itemView) {
            super(itemView);
            mOrderNumber = (TextView) itemView.findViewById(R.id.order_num);
            mOrderTime = (TextView) itemView.findViewById(R.id.order_time);
            mOrderType = (TextView) itemView.findViewById(R.id.order_kind);
            mOrderLocation = (TextView) itemView.findViewById(R.id.location);
            mOrderAppointmentTime = (TextView) itemView.findViewById(R.id.appointment_time);
            mOrderSiteNumber = (TextView) itemView.findViewById(R.id.site_num);
            mOrderPrice = (TextView) itemView.findViewById(R.id.order_price);
            mBtnPay = (Button)itemView.findViewById(R.id.btn_pay);

        }
    }
}