package com.example.yexin.menu6.Common.Json;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by DELL on 2019/10/10.
 * Json的打包
 */

public class Web_Json {
    private static JSONObject jsonObject=null;

    /**
     *
     * @param name 账号
     * @param password 密码
     * @return 打包的Json数据字符串
     */
    public static String Login(String name,String password) {
        try {
            jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    /**
     * 解析Json数据,返回一个HashMap
     */
    public static HashMap<String,String> getJson(String read){
        HashMap<String,String> map= new HashMap<String,String>();
        try{
            jsonObject=new JSONObject(read);
            map.put("code",jsonObject.get("code").toString());
            map.put("message",jsonObject.get("message").toString());
            map.put("token",jsonObject.get("token").toString());
            map.put("Picture",jsonObject.get("Picture").toString());
            map.put("NickName",jsonObject.get("NickName").toString());
            map.put("Level",jsonObject.get("Level").toString());
            Log.e("json",jsonObject.get("code")+" " +jsonObject.get("message")+" "+ jsonObject.get("token"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 后台登入验证
     */
    public static String Login_one(String name,String token){
        try {
            jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
