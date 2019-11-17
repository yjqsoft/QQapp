package com.example.yexin.menu6.Sideslip.Sideslip_center.Share;


import android.content.Intent;

/**
 * Created by 邱 on 2019/10/19.
 */

public class Sideslip_share {
    private static  Intent  intent = new Intent();
    public static Intent share()
    {

        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"分享的内容");
        intent.setType("text/plain");         //设置分享的类型
        return intent;

    }

}
