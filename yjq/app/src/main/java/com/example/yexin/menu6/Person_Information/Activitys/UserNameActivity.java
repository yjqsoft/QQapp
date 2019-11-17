//package com.example.yexin.menu6.Person_Information.Activitys;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.example.yexin.menu6.R;
//
//public class UserNameActivity extends Activity {
//    private EditText et_edit=null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_username);
//        et_edit=(EditText)findViewById(R.id.et_edit);
//    }
//
//    //设置控件单击事件
//    public void onClick(View view){
//        switch (view.getId()){
//            //点击界面返回键返回个人信息界面
//            case R.id.iv_back:
//                Intent intent1=new Intent();
//                intent1.putExtra("name","");
//                setResult(1,intent1);
//                finish();
//                break;
//            //点击完成保存真实姓名，然后返回并显示在个人信息界面
//            case R.id.tv_save:
//                Intent intent=new Intent();
//                intent.putExtra("name",et_edit.getText().toString());
//                setResult(1,intent);
//                finish();
//                break;
//        }
//    }
//    //点击手机返回键返回上一级界面
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event){
//        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            Intent intent1=new Intent();
//            intent1.putExtra("name","");
//            setResult(1,intent1);
//            this.finish();
//        }
//        return super.onKeyDown(keyCode,event);
//    }
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        Log.e("yjq123","杨家齐，姓名");
//    }
//}
