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
 * 第三子界面
 */
public class threeFragment extends android.support.v4.app.Fragment {


    public threeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_three, container, false);
        TextView textView3=(TextView)view.findViewById(R.id.text3);
        return view;

    }

}
