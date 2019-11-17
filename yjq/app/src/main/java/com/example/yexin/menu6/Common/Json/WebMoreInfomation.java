package com.example.yexin.menu6.Common.Json;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by DELL on 2019/10/28.
 */

public class WebMoreInfomation {
    private static JSONObject jsonObject=null;
    public static String getInfomation(String name) {
        try {
            jsonObject = new JSONObject();
            jsonObject.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("sa", jsonObject.toString());
        return jsonObject.toString();
    }

    /**
     * 返回信息，用来返回需要的基本信息；
     * @param
     * @return 返回一个数组
     */
    public static HashMap<String,String> getInfomationone(String read){
        HashMap<String,String> map= new HashMap<String,String>();
        try{
            jsonObject=new JSONObject(read);
            Log.e("数据的变化",jsonObject.get("Place").toString());
            map.put("Place",jsonObject.get("Place").toString());
            map.put("NickName",jsonObject.get("NickName").toString());
            map.put("Picture",jsonObject.get("Picture").toString());
            map.put("Signature",jsonObject.get("Signature").toString());
            map.put("Level",jsonObject.get("Level").toString());
            map.put("Sex",jsonObject.get("Sex").toString());
            map.put("Datetime",jsonObject.get("Datetime").toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
