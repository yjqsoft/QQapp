package com.example.yexin.menu6.Index;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yexin.menu6.R;

/**
 * A simple {@link Fragment} subclass.
 * 第二主界面
 */
public class twoFragment extends android.support.v4.app.Fragment {


    public twoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_two, container, false);
        TextView textView2=(TextView)view.findViewById(R.id.text2);
        return view;
    }



    public static twoFragment newInstance() {

        Bundle args = new Bundle();

        twoFragment fragment = new twoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
