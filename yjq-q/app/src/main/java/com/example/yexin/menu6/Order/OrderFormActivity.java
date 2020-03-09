package com.example.yexin.menu6.Order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.Login_logon.EnterPassword;
import com.example.yexin.menu6.Login_logon.Register;
import com.example.yexin.menu6.R;

public class OrderFormActivity extends AppCompatActivity {
    private TabLayout tabLayout = null;
    private ViewPager viewPager;
    private android.support.v4.app.Fragment[] mFragmentArrays = new android.support.v4.app.Fragment[2];
    private String[] mTabTitles = new String[2];
    private ImageView iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        initView();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_order=new Intent(OrderFormActivity.this, MainActivity.class);
                startActivity(intent_order);
                finish();
            }
        });
    }

    private void initView() {
        mTabTitles[0] = "预定";
        mTabTitles[1] = "已支付";
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);
        mFragmentArrays[0] = TabFragment_ordering.newInstance();
        mFragmentArrays[1] = TabFragment_paid.newInstance();
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),mFragmentArrays,mTabTitles);
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
            Intent myintent=new Intent(OrderFormActivity.this,MainActivity.class);
            startActivity(myintent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
