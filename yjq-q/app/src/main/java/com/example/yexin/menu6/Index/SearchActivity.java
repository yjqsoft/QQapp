package com.example.yexin.menu6.Index;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.List;

public class SearchActivity extends Activity {
    private List<SearchReasult> mData=null;
    private Context mContext;
    private SearchResultAdapter mAdapter = null;
    private ListView search_result_listview;
    private TextView tv_cancel;
    private TextView t;
    private EditText et_searchmain;
    private ImageView iv_clear;
    private TextView tv_mainsearch;

    private JSONObject jsonObject=null;
    private JSONArray jsonArr=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = SearchActivity.this;

        init();


//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_cancel= new Intent(SearchActivity.this,MainActivity.class);
//                startActivity(intent_cancel);
//                finish();
//            }
//        });
        //搜索框的监听器

//        mData=new LinkedList<SearchReasult>();
//        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
//        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
//        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
//        mData.add(new SearchReasult("东华理工","五颗心","观澜大道","<100","￥100"));
//        mAdapter=new SearchResultAdapter((LinkedList<SearchReasult>) mData,mContext);
       // search_result_listview.setAdapter(mAdapter);

        et_searchmain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    //mResultListView.setVisibility(View.GONE);
                    iv_clear.setVisibility(View.GONE);
                }else{
                    iv_clear.setVisibility(View.VISIBLE);
                }
            }
        });
        //加入  主页搜索的监听器  将数据从服务器取出  放入MData中**12138
        tv_mainsearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mData=null;
                mData=new LinkedList<SearchReasult>();
                if (!TextUtils.isEmpty(et_searchmain.getText())) {
                    Log.e("ssnr", "搜索内容：" + et_searchmain.getText());
                    RequestParams params = new RequestParams(Web_url.URL_Getsearchdata);
                    try {
                        jsonObject = new JSONObject();
                        jsonObject.put("content",et_searchmain.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
                    params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
                    params.setBodyContent(jsonObject.toString());//添加json内容到请求参数里

                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
                            Log.e("yjqresult:",result.toString());

                            try{
                                //int jsonSize = result.length();//获取数据组的长度
                                for(int i=0;i<result.length();i++){
                                    jsonArr=new JSONArray(result);
                                    jsonObject = (JSONObject)jsonArr.getJSONObject(i);
                                    Log.e("数据的变化",jsonObject.getString("场馆编号"));
                                    Log.e("数据的变化",jsonObject.getString("场馆名"));
                                    Log.e("数据的变化",jsonObject.getString("场馆地址"));

                                    mData.add(new SearchReasult(jsonObject.getString("场馆编号"),jsonObject.getString("场馆名"),
                                            jsonObject.getString("场馆地址"),"<100","￥100",jsonObject.getString("场馆负责人"),
                                            jsonObject.getString("负责人电话"),jsonObject.getString("场馆图片"),jsonObject.getString("场馆评价"),jsonObject.getString("场馆球类型"),/*球类型未添加*/jsonObject.getString("场馆服务"),
                                            jsonObject.getString("场馆介绍"),jsonObject.getString("下单量"),jsonObject.getString("地板"),jsonObject.getString("灯光"),
                                            jsonObject.getString("休息区"),jsonObject.getString("售卖"),
                                            jsonObject.getString("体育用品售卖"),jsonObject.getString("坐标")));
                                }
                   /*
                   * 此处不能运行*/
                            }catch (Exception e){
                                e.printStackTrace();
                            }
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
                            mAdapter=new SearchResultAdapter((LinkedList<SearchReasult>) mData,mContext);
                            search_result_listview.setAdapter(mAdapter);
                            //完成时候运行
                        }
                    });
                }
            }
        });
        iv_clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //et_searchmain.setText("");
                //et_searchmain.clearFocus();
                et_searchmain.setText("");
                iv_clear.setVisibility(View.GONE);

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
    public void init() {//iv_search

        //tv_cancel=(TextView)findViewById(R.id.tv_cancel);//取消按钮
        //iv_clear=(ImageView)findViewById(R.id.iv_clear);//清除按钮
        search_result_listview =(ListView)findViewById(R.id.search_result_listview);
        iv_clear=(ImageView)findViewById(R.id.iv_search_clear);
        et_searchmain=(EditText)findViewById(R.id.et_main_searchBox);
        tv_mainsearch=(TextView)findViewById(R.id.tv_mainsearch);
    }
}
