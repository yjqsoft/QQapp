package com.example.yexin.menu6.Common.App;

import android.content.Context;

/**
 * Created by DELL on 2019/11/21.
 */

public class StorageCache {
    /**
     *
     * @param context 界面的上下文
     * @return用户头像的保存地址，在data文件夹下面
     */
    public static String UserCache(Context context){
        String user_cache=context.getCacheDir().getPath()+"/App_cache";
        return user_cache;
    }
}
