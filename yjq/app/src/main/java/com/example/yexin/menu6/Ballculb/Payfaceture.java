package com.example.yexin.menu6.Ballculb;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.GetSystemTime;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.Index.SearchReasult;
import com.example.yexin.menu6.Index.SearchResultAdapter;
import com.example.yexin.menu6.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by DELL on 2019/11/24.
 */

public class Payfaceture extends Activity {
    private TextView changguan;
    private TextView xiangmu;
    private TextView riqi;
    private TextView  changdi;
    private TextView jiage;
    private RadioGroup radioGroup;
    private RadioButton alipay;
    private RadioButton bank;
    private RadioButton weChat;
    private int payWay = 0;
    private Button sure;

    String chunkdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ballculb);
        Intent intent=getIntent();
        String ballNo=intent.getStringExtra("ballNo");
        String  changguan1=intent.getStringExtra("changguan");//接受的值
        String  ball_kind=intent.getStringExtra("ball_kind");//接受的值
       // String  date=intent.getStringExtra("date");//接受的值
        String  changdi1=intent.getStringExtra("changdi");//接受的值
        String  jiage1=intent.getStringExtra("jiage");//接受的值
        String time_quantum=intent.getStringExtra("time_quantum");
        Log.e("ballNo",ballNo);
        Log.e("ball_time_quantum",time_quantum);
        String SystemTime=GetSystemTime.GetSystemTime();
        String price[]=jiage1.split("￥");
        Log.e("time",jiage1.replaceAll("￥", ""));
        Log.e("time",(jiage1.replaceAll("￥", "")).length()+"长度");
        Log.e("time1",ExchangeBallType(ball_kind));
        chunkdate=setdate("18879942330",ballNo,changdi1,time_quantum,ExchangeBallType(ball_kind),SystemTime,changguan1,jiage1.replaceAll("￥", ""));
        init();



        changguan.setText(changguan1);
        xiangmu.setText(ball_kind);
        riqi.setText(SystemTime);
        changdi.setText(changdi1);
        jiage.setText(jiage1);
        //选择支付方式
        sure=(Button)findViewById(R.id.sure);
        alipay = (RadioButton)findViewById(R.id.alipay);
        bank = (RadioButton)findViewById(R.id.bank);
        weChat = (RadioButton)findViewById(R.id.weChat);
        radioGroup = (RadioGroup)findViewById(R.id.payWay_choose);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bank.getId()==i){
                    payWay = 1;
                }
                if (alipay.getId()==i){
                    payWay = 2;
                }
                if (weChat.getId()==i){
                    payWay = 3;
                }
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {           //点击按钮后获得支付方式。
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams(Web_url.URL_AddGorderData);
                params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
                params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
                if (payWay==0){
                    Toast toast = Toast.makeText(Payfaceture.this, "请选择支付方式", Toast.LENGTH_SHORT);
                    //提交数据到数据库中 删除表中的场次
                    //生成订单  为支付成功 将数据添加到订单表中  支付成功  添加到已支付表中
                    //
                    params.setBodyContent(chunkdate);//添加json内容到请求参数里
                    http(params);


                    toast.show();
                }else if (payWay==1){
                    Toast toast = Toast.makeText(Payfaceture.this, "银行卡支付成功", Toast.LENGTH_SHORT);
                    ExchangeJson(params);
                    toast.show();
                }else if (payWay==2){
                    Toast toast = Toast.makeText(Payfaceture.this, "支付宝支付成功", Toast.LENGTH_SHORT);
                    ExchangeJson(params);
                    toast.show();
                }else if (payWay==3){
                    Toast toast = Toast.makeText(Payfaceture.this, "微信支付成功", Toast.LENGTH_SHORT);
                    ExchangeJson(params);
                    toast.show();
                }
            }
        });
    }

    public void init(){
        changguan=(TextView)findViewById(R.id.changguan);
        xiangmu=(TextView)findViewById(R.id.xiangmu);
        riqi=(TextView)findViewById(R.id.riqi);
        changdi=(TextView)findViewById(R.id.changdi);
        jiage=(TextView)findViewById(R.id.jiage);

    }
    public String setdate(String userId,String no,String place,String time,String Class,String appointment,String site,String money){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("userId",userId);
            jsonObject.put("no",no);
            jsonObject.put("place",place);
            jsonObject.put("time",time);
            jsonObject.put("Class",Class);
            jsonObject.put("appointment",appointment);
            jsonObject.put("site",site);
            jsonObject.put("money",money);
            jsonObject.put("pay","0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    public String ExchangeBallType(String changestring){
         String ball_type=null;
        switch (changestring){
            case "羽毛球":
                ball_type="A";
                break;
            case "篮球":
                ball_type="B";//未设置
                break;
            case "足球":
                ball_type="C";//未设置
                break;
            case "网球":
                ball_type="D";//未设置
                break;
            case "乒乓球":
                ball_type="E";//未设置
                break;
            default:
                ball_type=null;
        }
        return ball_type;
    }
    public void http(RequestParams params){
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq","网络请求成功"+result);  //接收JSON的字符串
                Log.e("yjqresult:",result.toString());
                try{
                    //int jsonSize = result.length();//获取数据组的长度
                   /*
                   * 此处不能运行*/
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1","失败");
                Toast.makeText(Payfaceture.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq","取消");
            }
            @Override
            public void onFinished() {
                Log.e("yjq","完成");

                //完成时候运行
            }
        });
    }
    public void ExchangeJson(RequestParams params) {

        try {
            JSONObject json=new JSONObject(chunkdate);
            json.put("pay","1");
            chunkdate=json.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        params.setBodyContent(chunkdate);//添加json内容到请求参数里
        http(params);
    }


}
