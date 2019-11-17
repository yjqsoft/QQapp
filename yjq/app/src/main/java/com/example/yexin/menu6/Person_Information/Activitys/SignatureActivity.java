package com.example.yexin.menu6.Person_Information.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.Json.Modify;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Common.Url.Web_url;
import com.example.yexin.menu6.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;

public class SignatureActivity extends Activity {
    private EditText et_edit=null;
    private String text;
    private boolean isSuccess=false;
    private User_public user_public=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        user_public=new User_public(this);
        et_edit=(EditText)findViewById(R.id.et_edit);
        et_edit.setText(UserPublic.getUser_q());  //初始化
    }

    //设置控件单击事件
    public void onClick(View view){
        text=et_edit.getText().toString().trim();
        switch (view.getId()){
            //单击界面返回键返回个人信息界面
            case R.id.iv_back:
                Intent intent1=new Intent();
                intent1.putExtra("signature","");
                setResult(10,intent1);
                finish();
                break;

            //点击保存个性签名信息，然后返回并在个人信息界面显示
            case R.id.tv_save:
                Xutils(text,"10");
//                Intent intent=new Intent();
//                intent.putExtra("signature",et_edit.getText().toString());
//                setResult(4,intent);
//                finish();
                break;
        }
    }
    private void Xutils(String content,String model) {

        String jsonObject = Modify.setInfo(content, model);
        RequestParams params = new RequestParams(Web_url.URL_Modifyinfo);
        params.addHeader("Content-Type", "application/json-rpc"); //设置请求头部
        params.setAsJsonContent(true);//设置为json内容(这句个本人感觉不加也没有影响)
        params.setBodyContent(jsonObject);//添加json内容到请求参数里
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yjq", "网络请求成功" + result);  //接收JSON的字符串
//               HashMap<String,String> map=Web_Json.getJson(result);
                HashMap<String, String> map = Modify.getModify(result);

                Log.e("124", map.get("code").toString());
                if (map.get("code").toString().equals("200")) {
                    isSuccess = true;
                } else {
                    isSuccess = false;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yjq1", "失败");
                isSuccess = false;
                Toast.makeText(SignatureActivity.this, "连接超时，请查看网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yjq", "取消");
            }

            @Override
            public void onFinished() {
                Log.e("yjq", "完成");
                if (isSuccess) {
                    Log.e("ahgia", "修改成功");
                    Intent intent=new Intent();
                    intent.putExtra("signature",text);
                    user_public.setUser_q(text);
                    UserPublic.setUser_q(text);
                    setResult(4,intent);
                    finish();
                } else {
                    Intent intent=new Intent();
                    intent.putExtra("signature","");
                    setResult(10,intent);
                    finish();
                    Toast.makeText(SignatureActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    Log.e("gaodifha", "修改失败");
                }
            }
        });
    }
    //点击手机返回键返回上一级界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1=new Intent();
            intent1.putExtra("signature","");
            setResult(10,intent1);
            this.finish();
        }
        return super.onKeyDown(keyCode,event);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e("yjq123","杨家齐，签名");
    }
}
