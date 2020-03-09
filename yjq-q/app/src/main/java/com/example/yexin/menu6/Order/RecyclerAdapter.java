package com.example.yexin.menu6.Order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedList;

/**
 * Created by 23646 on 2019/10/31.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AuthorViewHolder> {
    private LinkedList<Order> mData;
    private Context mContext;

    public RecyclerAdapter(LinkedList<Order> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = inflater.inflate(R.layout.item_ordering, parent, false);
        AuthorViewHolder viewHolder = new AuthorViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, final int position) {
        holder.mOrderNumber.setText(mData.get(position).getOrder_num());
        holder.mOrderTime.setText(mData.get(position).getOrder_time());
        if(mData.get(position).getOrder_type().equals("A")){
            holder.mOrderType.setText("羽毛球");
        }
        else if(mData.get(position).getOrder_type().equals("B")){
            holder.mOrderType.setText("篮球");
        }
        else if(mData.get(position).getOrder_type().equals("C")){
            holder.mOrderType.setText("足球");
        }
        else if(mData.get(position).getOrder_type().equals("D")){
            holder.mOrderType.setText("D球");
        }
        else if(mData.get(position).getOrder_type().equals("E")){
            holder.mOrderType.setText("E球");
        }
        else if(mData.get(position).getOrder_type().equals("F")){
            holder.mOrderType.setText("F球");
        }
        else{
            holder.mOrderType.setText(null);
        }
        holder.mOrderLocation.setText(mData.get(position).getOrder_location());
        holder.mOrderAppointmentTime.setText(mData.get(position).getOrder_appointment_time());
        holder.mOrderSiteNumber.setText(mData.get(position).getOrder_site_num());
        holder.mOrderPrice.setText(mData.get(position).getOrder_price());


        holder.mOrderStatus.setText("未支付");
        holder.mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("order","sdfdghjk"+mData.get(position).getOrder_location());
                JSONObject jsonObject=null;
                RequestParams params = new RequestParams(Web_url.URL_URL_Alterorder);
                params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
                params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
                try {
                    jsonObject=new JSONObject();
                    jsonObject.put("order_no",mData.get(position).getOrder_num());
                    jsonObject.put("pay",mData.get(position).getOrder_status());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                params.setBodyContent(jsonObject.toString());//添加json内容到请求参数里
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("yjq","网络请求成功order: "+result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                        //HashMap<String,String> map=Web_Json.getJson(result);
//                        Log.e("yjqresult:",result.toString());
//                        Log.e("yjqresult:",result.length()+"");
//                        try{
//
//                   /*
//                   * 此处不能运行*/
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("yjq1","失败");
                        Toast.makeText(mContext, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.e("yjq","取消");
                    }
                    @Override
                    public void onFinished() {
                        Log.e("yjq","完成");


                        //完成时候运行
                    }
                });
            }
        });
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
        TextView mOrderStatus;
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
            mOrderStatus=(TextView) itemView.findViewById(R.id.order_status);
            mBtnPay = (Button)itemView.findViewById(R.id.btn_pay);


        }
    }
}
