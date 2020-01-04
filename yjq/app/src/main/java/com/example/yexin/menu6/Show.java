package com.example.yexin.menu6;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yexin.menu6.Common.App.StorageCache;
import com.example.yexin.menu6.Common.Public_class.UserPublic;
import com.example.yexin.menu6.Common.Public_class.User_public;
import com.example.yexin.menu6.Index.MainActivity;
import com.example.yexin.menu6.Login_logon.Login;

import java.io.File;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by DELL on 2019/10/17.
 * @OnPermissionDenied注解：这个也不是必须的注解，用于标注如果权限请求失败，
 * 但是用户没有勾选不再询问的时候执行的方法，注解括号里面有参数，传入想要申请的权限。
 * 也就是说，我们可以在这个方法做申请权限失败之后的处理，如像用户解释为什么要申请，或者重新申请操作等。
 * @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO})//一旦用户拒绝了
 *  public void multiDenied() {
 *  Toast.makeText(this, "已拒绝一个或以上权限", Toast.LENGTH_SHORT).show();
 *  }
 * @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)//一旦用户拒绝了
 * public void StorageDenied() {
 * Toast.makeText(this, "已拒绝WRITE_EXTERNAL_STORAGE权限", Toast.LENGTH_SHORT).show();
 *}
 *
 * @OnNeverAskAgain注解：这个也不是必须的注解，用于标注如果权限请求失败,而且用户勾选不再询问的时候执行的方法，注解括号里面有参数，传入想要申请
 * 的权限。也就是说，我们可以在这个方法做申请权限失败并选择不再询问之后的处理。例如，可以告诉作者想开启权限的就从手机设置里面开启。
 * @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO})//用户选择的不再询问
 * public void multiNeverAsk() {
 * Toast.makeText(this, "已拒绝一个或以上权限，并不再询问", Toast.LENGTH_SHORT).show();
 * }
 * @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)//用户选择的不再询问
 * public void StorageNeverAsk() {
 * Toast.makeText(this, "已拒绝WRITE_EXTERNAL_STORAGE权限，并不再询问", Toast.LENGTH_SHORT).show();
 * }
 */

public class Show extends Activity {
    private User_public user_public=null;
    private ImageView imageView=null;
    /**
     * 权限申请
     * android.permission.BROADCAST_SMS，当收到短信时触发一个广播
     * android.permission.BRICK，能够禁用手机，非常危险，顾名思义就是让手机变成砖头
     * android.permission.CALL_PHONE，允许程序从非系统拨号器里输入电话号码
     * android.permission.CALL_PRIVILEGED，允许程序拨打电话，替换系统的拨号器界面
     * android.permission.CAMERA，允许访问摄像头进行拍照
     * android.permission.READ_CONTACTS，允许应用访问联系人通讯录信息
     * android.permission.READ_FRAME_BUFFER，读取帧缓存用于屏幕截图
     * android.permission.READ_SMS，读取短信内容
     * android.permission.RECEIVE_SMS，接收短信
     * android.permission.WRITE_EXTERNAL_STORAGE 用于写入缓存数据到扩展存储卡
     * android.permission.READ_EXTERNAL_STORAGE 于读取扩展存储卡
     * android.permission.INTERNET 用于访问网络，网络定位需要上网
     * android.permission.ACCESS_FINE_LOCATION 用于访问GPS定位  允许一个程序访问精良位置(如GPS)
     */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        imageView=(ImageView)findViewById(R.id.show);
        /**
         * 在第一次登入的时候，在xml文件中，标识已经是第一次登入，不用在次运行代码，
         * 创建用户在data目录下的文件夹，用来保存用户的头像和信息。mastheader文件夹下面
         */
//        new Thread(){
//            public void run(){

//            }
//        }.start();
//        Bitmap mCropBitmap;
//        int abb=3;
//        if(abb==1){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.banner2);
//            imageView.setImageBitmap(mCropBitmap);
////        }
//        else if(abb==2){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a2);
//            imageView.setImageBitmap(mCropBitmap);
//        }else if(abb==3){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a3);
//            imageView.setImageBitmap(mCropBitmap);
//        }else if(abb==4){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a4);
//            imageView.setImageBitmap(mCropBitmap);
//        }else if(abb==5){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a5);
//            imageView.setImageBitmap(mCropBitmap);
//        }else if(abb==6){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a6);
//            imageView.setImageBitmap(mCropBitmap);
//        }else if(abb==7){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a7);
//            imageView.setImageBitmap(mCropBitmap);
//        }else if(abb==8){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a8);
//            imageView.setImageBitmap(mCropBitmap);
//        }else if(abb==9){
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a9);
//            imageView.setImageBitmap(mCropBitmap);
//        }
//        else{
//            mCropBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a1);
//            imageView.setImageBitmap(mCropBitmap);
//        }
        function();
    }
    private void function(){
        user_public=new User_public(this);
        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
        boolean isFirstIn = preferences.getBoolean("isFirst", true);
        if(isFirstIn){
            Log.e("yjq_fire","第一次登入,跳转登入界面");
            Log.e("yjq_13","创建xml文件");
            user_public.init();    //创建xml文件
//            String sdPath = Environment.getExternalStorageDirectory().getPath();
//            String UserinfoPath;
//            try{
//                File file=new File(sdPath+"/yjq");
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            Log.e("qew",sdPath);
//            try{
//                File file=new File(StorageCache.UserCache(this));
//            }catch(Exception e){
//                e.printStackTrace();
//            }
            Intent intent = new Intent(Show.this,Login.class);
            startActivity(intent);
            finish();
        }
        else{
            user_public.Init();
            if(UserPublic.isUser_flag()){   //进入主界面，暂注释
                Log.e("yqj_21","不是第一次登入,并且在登入状态，进入主页查看长连接");
                /**
                 * 用户信息的初始化
                 */
                user_public.initInfo();  //初始化个人信息
                UserPublic.setFirst(true);
                Intent intent = new Intent(Show.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Log.e("yjq_22","不是第一次登入，并且不在登入状态，去登入");
                Intent intent = new Intent(Show.this,Login.class);
                startActivity(intent);
                finish();
            }
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e("show1","show界面已经注销，申请权限");
    }
}
