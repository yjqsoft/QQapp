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

import com.example.yexin.menu6.Ballculb.Payfaceture;
import com.example.yexin.menu6.R;
import com.google.gson.Gson;

/**
 * Created by 23646 on 2019/11/24.
 */

public class SelectDialog extends Dialog implements SKUInterface {
    private RecyclerView rv_sku;
    private ImageView dialog_cancel;
    private GoodsAttrsAdapter mAdapter;
    private Button btn_next;

    private GoodsAttrsBean dataBean;
    private Gson gson;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select);
        dialog_cancel=(ImageView)findViewById(R.id.dialog_cancel);
        btn_next=(Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Payfaceture.class);
                context.startActivity(intent);
            }
        });
        init();
        install();
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

    public SelectDialog(Context context) {
        super(context);
        this.context=context;
    }
    private void init() {
        gson = new Gson();
        //tv_title = (TextView) findViewById(R.id.tv_title);
        rv_sku = (RecyclerView) findViewById(R.id.rv_sku);
        dataBean = gson.fromJson(context.getResources().getString(R.string.jsonData), GoodsAttrsBean.class);
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
        //tv_test.setText(str);
        //return str;
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
