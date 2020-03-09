package com.example.yexin.menu6.Common.Json;

import android.util.Log;

import com.example.yexin.menu6.Common.Public_class.UserPublic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by DELL on 2019/11/5.
 */

public class Modify {
    private static JSONObject jsonObject=null;
    public Modify(){

    }
    public static String setInfo(String info,String mode){
        try {
            jsonObject = new JSONObject();
            jsonObject.put("Username", UserPublic.getUser());
            jsonObject.put("info", info);
            jsonObject.put("mode", mode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("sa", jsonObject.toString());
        return jsonObject.toString();
    }
    public static HashMap<String,String> getModify(String read){
        HashMap<String,String> map= new HashMap<String,String>();
        try{
            jsonObject=new JSONObject(read);
            map.put("code",jsonObject.get("code").toString());
//            map.put("Place",jsonObject.get("Place").toString());
//            map.put("NickName",jsonObject.get("NickName").toString());
//            map.put("Signature",jsonObject.get("Signature").toString());
//            map.put("Level",jsonObject.get("Level").toString());
//            map.put("Sex",jsonObject.get("Sex").toString());
//            map.put("Datetime",jsonObject.get("Datetime").toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    public static HashMap<String,String> GetReallyInfo(String read){
        HashMap<String,String> map= new HashMap<String,String>();
        try{
            jsonObject=new JSONObject(read);
            map.put("ReallyName",jsonObject.get("ReallyName").toString());
            map.put("Reallycard",jsonObject.get("Reallycard").toString());
            map.put("code",jsonObject.get("code").toString());
//            map.put("Place",jsonObject.get("Place").toString());
//            map.put("NickName",jsonObject.get("NickName").toString());
//            map.put("Signature",jsonObject.get("Signature").toString());
//            map.put("Level",jsonObject.get("Level").toString());
//            map.put("Sex",jsonObject.get("Sex").toString());
//            map.put("Datetime",jsonObject.get("Datetime").toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}

