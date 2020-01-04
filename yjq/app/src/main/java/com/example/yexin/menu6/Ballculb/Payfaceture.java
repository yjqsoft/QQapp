package com.example.yexin.menu6.Ballculb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.R;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ballculb);
        Intent intent=getIntent();
        String  changguan1=intent.getStringExtra("changguan");//接受的值
        String  xiangmu1=intent.getStringExtra("xiangmu");//接受的值
        String  riqi1=intent.getStringExtra("riqi");//接受的值
        String  changdi1=intent.getStringExtra("changdi");//接受的值
        String  jiage1=intent.getStringExtra("jiage");//接受的值
        xiangmu=(TextView)findViewById(R.id.xiangmu);
        riqi=(TextView)findViewById(R.id.riqi);
        changdi=(TextView)findViewById(R.id.changdi);
        jiage=(TextView)findViewById(R.id.jiage);
//        changguan.setText(changguan1);
//        xiangmu.setText(xiangmu1);
//        riqi.setText(riqi1);
//        changdi.setText(changdi1);
//        jiage.setText(jiage1);
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
                if (payWay==0){
                    Toast toast = Toast.makeText(Payfaceture.this, "请选择支付方式", Toast.LENGTH_SHORT);
                    toast.show();
                }else if (payWay==1){
                    Toast toast = Toast.makeText(Payfaceture.this, "银行卡支付", Toast.LENGTH_SHORT);
                    toast.show();
                }else if (payWay==2){
                    Toast toast = Toast.makeText(Payfaceture.this, "支付宝支付", Toast.LENGTH_SHORT);
                    toast.show();
                }else if (payWay==3){
                    Toast toast = Toast.makeText(Payfaceture.this, "微信支付", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
