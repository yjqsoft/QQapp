package com.example.yexin.menu6.Order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yexin.menu6.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 23646 on 2019/10/31.
 */

public class TabFragment_ordering extends Fragment{

    private List<Order> mData = null;
    public static Fragment newInstance() {
        TabFragment_ordering fragment = new TabFragment_ordering();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mData = new LinkedList<Order>();
        InitData();
        recyclerView.setAdapter(new RecyclerAdapter((LinkedList<Order>)mData,getActivity()));
        return rootView;
    }

    public void InitData(){
        mData.add(new Order("5155","54644846","篮球","南昌","8:39~10:00","5号场","￥110.00","待支付"));
        mData.add(new Order("5155","54644846","足球","福州","8:39~10:00","5号场","￥110.00","待支付"));
        mData.add(new Order("5155","54644846","拍球","抚州","8:39~10:00","5号场","￥110.00","待支付"));


    }
}
