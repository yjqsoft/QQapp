package com.example.yexin.menu6.Login_logon;


import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.mob.wrappers.AnalySDKWrapper;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.File;

/**
 * Created by DELL on 2019/10/27.
 * 下载头像用的线程
 */

public class Picture_download{
    private Activity Login;
    private String UserName;
    public Picture_download(){

    }
    public Picture_download(Activity Login,String UserName){
        this.Login=Login;
        this.UserName=UserName;
    }
    void PictureDown(){
        String path=Login.getFilesDir().getAbsolutePath();
        File file1=new File(path+"/"+UserName);
        if(!file1.exists()){
            file1.mkdir();
        }

    }
//    private Activity Login;
//    private String UserName;
//    public Picture_download(Activity Login,String UserName){
//        this.Login=Login;
//        this.UserName=UserName;
//    }
//    @Override
//    public void run() {
//        String path= Login.getFilesDir().getAbsolutePath();
//        File file=new File(path+"/"+UserName);  //有登入记录就直接访问，没有登入记录的话需要重新创建
//        if(!file.exists()){
//            file.mkdirs() ;
//        }
//        /**
//         * 这里需要从本地访问远程服务，如果需要重新加载头像，就服务加载不需要直接调用本地。
//         */
//    }
}
