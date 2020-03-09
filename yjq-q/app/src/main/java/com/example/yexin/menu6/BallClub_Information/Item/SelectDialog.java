package com.example.yexin.menu6.BallClub_Information.Item;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData.GetData;
import com.example.yexin.menu6.Ballculb.Payfaceture;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.SearchReasult;
import com.example.yexin.menu6.Index.SearchResultAdapter;
import com.example.yexin.menu6.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedList;

/**
 * Created by 23646 on 2019/11/24.
 */

public class SelectDialog extends Dialog implements SKUInterface {
    private RecyclerView rv_sku;
    private TextView ballclub_name;
    private TextView ball_kind;
    private ImageView dialog_cancel;
    private GoodsAttrsAdapter mAdapter;
    private Button btn_next;

    private GoodsAttrsBean dataBean;
    private Gson gson;

    private Context context;
    private String BallType;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private String BallClubStr;
    private String prices;

    private String placenum;
    private String Tempstr;
    private String [] placeStateString=new String [2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select);
        dialog_cancel=(ImageView)findViewById(R.id.dialog_cancel);
        btn_next=(Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("mAdapter",mAdapter.selectedValue.toString());
                if(mAdapter.selectedValue[0].equals("")){
                    Log.e("mAdapter10",mAdapter.selectedValue[0]);
                    Log.e("mAdapter11",mAdapter.selectedValue[1]);
                    Toast.makeText(context, "请选择时间", Toast.LENGTH_SHORT).show();
                }
                if(mAdapter.selectedValue[1].equals("")){
                    Toast.makeText(context, "请选择场次", Toast.LENGTH_SHORT).show();
                    Log.e("mAdapter20",mAdapter.selectedValue[0]);
                    Log.e("mAdapter21",mAdapter.selectedValue[1]);
                }
                if(!mAdapter.selectedValue[1].equals("")&&!mAdapter.selectedValue[0].equals("")){
                    Log.e("mAdapter30",mAdapter.selectedValue[0]);
                    Log.e("mAdapter31",mAdapter.selectedValue[1]);
                    Intent intent=new Intent(context, Payfaceture.class);
                    String temp[]=BallType.split(" ");
                    intent.putExtra("ballNo",temp[1]);
                    intent.putExtra("changguan",ballclub_name.getText());
                    intent.putExtra("ball_kind",ball_kind.getText());
                    intent.putExtra("changdi",mAdapter.selectedValue[1]);
                    intent.putExtra("time_quantum",mAdapter.selectedValue[0]);
                    intent.putExtra("jiage",prices);
                    context.startActivity(intent);
                }
            }
        });
        HttpGetData();


        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Window window = SelectDialog.this.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.CENTER_HORIZONTAL);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //window.setLayout(5000,200);
    }

    public SelectDialog(Context context,String BallType) {
        super(context);
        this.context=context;
        this.BallType=BallType;
        Toast.makeText(context, BallType+"zais", Toast.LENGTH_SHORT).show();
    }


    private void HttpGetData(){
        Log.e("SelectD", "搜索内容：" + BallType);
        String temp[]=BallType.split(" ");
        this.prices=temp[3];
        RequestParams params=null;
        try {
            jsonObject = new JSONObject();
            Log.e("temp",temp[0]+"  "+temp[1]);
            jsonObject.put("content_balltype",temp[0]);
            jsonObject.put("content_ballno",temp[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (temp[0]){
            case "羽毛球":
                params = new RequestParams(Web_url.URL_GetABCball);
                break;
            case "篮球":
                params = new RequestParams(Web_url.URL_GetABCball);//未设置
                break;
            case "足球":
                params = new RequestParams(Web_url.URL_GetABCball);//未设置
                break;
            case "网球":
                params = new RequestParams(Web_url.URL_GetABCball);//未设置
                break;
            case "乒乓球":
                params = new RequestParams(Web_url.URL_GetABCball);//未设置
                break;
            default:
                params=null;
        }

        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        params.setBodyContent(jsonObject.toString());//添加json内容到请求参数里

        if(params!=null){
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
                    Log.e("yjqresult:",result.toString());
                    Tempstr=result;
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("yjq1","失败");
                    Toast.makeText(context, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(CancelledException cex) {
                    Log.e("yjq","取消");
                }
                @Override
                public void onFinished() {
                    jsonObject = new JSONObject();
                    Log.e("yjq","完成");
                    try{
                        jsonArray=new JSONArray(Tempstr);
                        Log.e("jsonObject:","长度为a:"+jsonArray.length());//求出数据中的个数
                        jsonObject = (JSONObject)jsonArray.getJSONObject(jsonArray.length()-1);
                        placenum=jsonObject.getString("ballnum");
                        for(int i=0;i<placenum.length();i++){
                            String a=((JSONObject)jsonArray.getJSONObject(i)).getString(placenum.substring(i,i+1));
                            placeStateString[i]=a;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    init();
                    install();
//                        mAdapter=new SearchResultAdapter((LinkedList<SearchReasult>) mData,mContext);
//                        search_result_listview.setAdapter(mAdapter);
                    //完成时候运行
                }
            });
        }else{
            Toast.makeText(context, "请求数据错误", Toast.LENGTH_SHORT).show();
        }
    }
    private void init() {
        gson = new Gson();
        //tv_title = (TextView) findViewById(R.id.tv_title);
        rv_sku = (RecyclerView) findViewById(R.id.rv_sku);
        ballclub_name=(TextView)findViewById(R.id.ballclub_name);
        ball_kind=(TextView) findViewById(R.id.ball_kind);

       Log.e("json11","dwqd"+placenum+placeStateString[0]+placeStateString[1]);
        BallClubStr= GetData.setInfo(placenum,placeStateString);
        dataBean = gson.fromJson(BallClubStr, GoodsAttrsBean.class);
        Log.e("qwe",dataBean.toString());
        Log.e("qwe1",dataBean.getAttributes().toString());
        Log.e("qwe2",dataBean.getStockGoods().toString());
    }
    private void install() {
        mAdapter = new GoodsAttrsAdapter(context, dataBean.getAttributes(), dataBean.getStockGoods());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_sku.setLayoutManager(layoutManager);
        rv_sku.setFocusable(false);
        mAdapter.setSKUInterface(this);
        rv_sku.setAdapter(mAdapter);
        String temp[]=BallType.split(" ");
        ballclub_name.setText(temp[2]);
        ball_kind.setText(temp[0]);

    }
    @Override
    public void selectedAttribute(String[] attr) {
        String str = "";
        String ss = "";
        for (int i = 0; i < attr.length; i++) {
            str += " " + dataBean.getAttributes().get(i).getTabName() + "：";
            ss = TextUtils.isEmpty(attr[i]) ? "无" : attr[i];
            str += ss + " ";
        }
    }

    @Override
    public void uncheckAttribute(String[] attr) {
        String str = "";
        String ss = "";
        for (int i = 0; i < attr.length; i++) {
            str += " " + dataBean.getAttributes().get(i).getTabName() + "：";
            ss = TextUtils.isEmpty(attr[i]) ? "无" : attr[i];
            str += ss + " ";
        }
        //tv_test.setText(str);
        //return str;
    }
}
