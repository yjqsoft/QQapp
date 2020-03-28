package com.example.yexin.menu6.Index.fragmenttwo_fourballs;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;//下拉选择框
import android.widget.TextView;
import android.widget.Toast;


import com.example.yexin.menu6.Common.Refresh.RefreshDialog;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.R;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class Fragmenttwo_balls_commit extends Activity {
    private JSONObject jsonObject=null;
    private ImageView iv_back;
    private TextView et_user_name;
    private EditText et_phone;
    private EditText et_game_name;
    private TextView tv_place;
    private Spinner spinner_game_type;
    private Button btn_commmit;

    private RefreshDialog refreshDialog;
    private int Refresh_status=2;
    private final int Refresh_Success=1;
    private final int Refresh_Fail=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenttow_ball_commit);
        Intent intent=getIntent();
        try{
            jsonObject=new JSONObject(intent.getStringExtra("jsonData"));
            Log.e("jsonData",jsonObject.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
        init();
        try{
            String type[]=jsonObject.getString("gameType").split("，");
            spinner(type);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    private void init(){
        iv_back=(ImageView)findViewById(R.id.iv_back);
        et_user_name=(TextView)findViewById(R.id.et_user_name);
        et_phone=(EditText) findViewById(R.id.et_phone);
        et_game_name=(EditText)findViewById(R.id.et_game_name);
        tv_place=(TextView)findViewById(R.id.tv_place);
        spinner_game_type=(Spinner) findViewById(R.id.spinner_game_type);
        btn_commmit=(Button)findViewById(R.id.btn_commit);
        try{
            et_phone.setText(jsonObject.getString("userPhone"));
            et_game_name.setText((jsonObject.getString("gameName")));
            tv_place.setText(jsonObject.getString("gamePlace"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public void onClick( View view){
       switch (view.getId()){
           case R.id.iv_back:
//               Intent intent =new Intent(Fragmenttwo_balls_commit.this,fragmenttwo_balls_details.class);
//               startActivity(intent);
//               this.finish();
//               Instrumentation inst = new Instrumentation();
//               inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
               new Thread() {
                   public void run() {
                       try {
                           Instrumentation inst = new Instrumentation();
                           inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);//启动返回按键
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               }.start();

               break;
           case R.id.btn_commit:
               if(et_user_name.getText().toString().isEmpty()){
                   Toast.makeText(Fragmenttwo_balls_commit.this,"姓名不能为空！",Toast.LENGTH_SHORT).show();
                   break;
               }else if(et_phone.getText().toString().isEmpty()){
                   Toast.makeText(Fragmenttwo_balls_commit.this,"电话不能为空！",Toast.LENGTH_SHORT).show();
                   break;
               }else if(et_game_name.getText().toString().isEmpty()){
                   Toast.makeText(Fragmenttwo_balls_commit.this,"比赛名字不能为空！",Toast.LENGTH_SHORT).show();
                   break;
               }else if(tv_place.getText().toString().isEmpty()){
                   Toast.makeText(Fragmenttwo_balls_commit.this,"参赛地不能为空！",Toast.LENGTH_SHORT).show();
                   break;
               }else if(spinner_game_type.getSelectedItem().toString().isEmpty()){
                   Toast.makeText(Fragmenttwo_balls_commit.this,"参赛类型不能为空！",Toast.LENGTH_SHORT).show();
                   break;
               }else{
                   try{
                       jsonObject.put("userName",et_user_name.getText());
                       jsonObject.put("userPhone",et_phone.getText());
                       jsonObject.put("gameName",et_game_name.getText());
                       jsonObject.put("gamePlace",tv_place.getText());
                       jsonObject.put("gameType",spinner_game_type.getSelectedItem().toString());
                   }catch(Exception e){
                       e.printStackTrace();
                   }
                   Log.e("jsonObjectdata",jsonObject.toString());
                   refreshContent(Web_url.URL_AddJoinGame,jsonObject);
               }
               break;
           default:
               break;
       }
    }
    public void http(final String URL, final JSONObject jsonObjecthttp){

        RequestParams params = new RequestParams(URL);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)

        params.setBodyContent(jsonObjecthttp.toString());//添加json内容到请求参数里
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                //HashMap<String,String> map=Web_Json.getJson(result);
                Log.e("yjqresult:",result.toString());
                Log.e("yjqresult:","长度："+result.length());
                if(result!=null){
                    if(Web_url.URL_Getgamedetails.equals(URL)){
                        //AddData(result);
                    }else if(Web_url.URL_AddJoinGame.equals(URL)){
                        Log.e("addjoingame","结果："+result);
                        if(result.toString().equals("EXIST")){
                            Toast.makeText(Fragmenttwo_balls_commit.this,"不能重复报名！",Toast.LENGTH_SHORT).show();
                        }else if(result.toString().equals("SUCCEED")){
                            Toast.makeText(Fragmenttwo_balls_commit.this,"报名成功！",Toast.LENGTH_SHORT).show();
                        }else if(result.toString().equals("ERROR")){
                            Toast.makeText(Fragmenttwo_balls_commit.this,"数据错误！",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1","失败");
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");
                refreshDialog.dismiss();
                //完成时候运行
            }
        });
    }
    private void refreshContent(final String url,final JSONObject data) {
        refreshDialog = new RefreshDialog(Fragmenttwo_balls_commit.this,"正在提交...",R.mipmap.ic_dialog_loading);
        refreshDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    http(url,data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                (Fragmenttwo_balls_commit.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //在之中可以终止线程
                    }
                });
            }
        }).start();
    }
    private void spinner(String type[]){
        ArrayList<String> list = new ArrayList<String>();
        if(type.length>0){
            for(int i=0;i<type.length;i++){
                Log.e("type","type:"+type[i]);
                list.add(type[i]);
            }
        }

        //为下拉列表定义一个适配器
        final ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //设置下拉菜单样式。
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //添加数据
        spinner_game_type.setAdapter(ad);
        //点击响应事件
        spinner_game_type.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

}
