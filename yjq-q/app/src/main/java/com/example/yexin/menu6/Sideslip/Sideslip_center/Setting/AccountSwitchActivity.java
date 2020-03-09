package com.example.yexin.menu6.Sideslip.Sideslip_center.Setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yexin.menu6.R;

import java.util.LinkedList;
import java.util.List;

public class AccountSwitchActivity extends Activity {
    private List<Account> mData = null;
    private Context mContext;
    private AccountAdapter mAdapter = null;
    private ListView list_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_switch);
        mContext = AccountSwitchActivity.this;
        list_account = (ListView)findViewById(R.id.lv_account);
        mData = new LinkedList<Account>();
        mData.add(new Account(R.drawable.back,"159******159",R.drawable.forward));
        mAdapter = new AccountAdapter((LinkedList<Account>) mData,mContext);
        list_account.setAdapter(mAdapter);

        //点击账号列表选择账号进行登录
        list_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    //设置控件单击事件
    public void onClick(View view){
        switch (view.getId()){
            //点击界面返回键返回设置界面
            case R.id.iv_back:
                Intent intent_one = new Intent(AccountSwitchActivity.this,SettingActivity.class);
                startActivity(intent_one);
                finish();
                break;

            //点击切换账号标签进行账号登录
            case R.id.rl_new_account:
                Intent intent_two = new Intent();
                startActivity(intent_two);
                finish();
                break;
        }
    }

    //点击手机返回键返回设置界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent myIntent = new Intent(AccountSwitchActivity.this, SettingActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
