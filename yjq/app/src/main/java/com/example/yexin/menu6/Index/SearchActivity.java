package com.example.yexin.menu6.Index;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.R;

import java.util.LinkedList;
import java.util.List;

public class SearchActivity extends Activity {
    private List<SearchReasult> mData=null;
    private Context mContext;
    private SearchResultAdapter mAdapter = null;
    private ListView search_result_listview;

    private TextView tv_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = SearchActivity.this;
        tv_cancel=(TextView)findViewById(R.id.tv_cancel);
        search_result_listview =(ListView)findViewById(R.id.search_result_listview);
        mData=new LinkedList<SearchReasult>();
        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
        mAdapter=new SearchResultAdapter((LinkedList<SearchReasult>) mData,mContext);
        search_result_listview.setAdapter(mAdapter);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_cancel= new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent_cancel);
                finish();
            }
        });
        search_result_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "第"+position+"项", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
            Intent myintent=new Intent(SearchActivity.this,MainActivity.class);
            startActivity(myintent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
