package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

/**
 * Created by 邱 on 2020/2/15.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONException;

public class attributes {
    private static JSONObject jsonObject_time=null;
    private static JSONObject jsonObject_place=null;
    public static String Gettime(String time[]){

        jsonObject_time = new JSONObject();
        try {
            jsonObject_time.put("tabID", 0);
            jsonObject_time.put("tabName", "时间段");
            jsonObject_time.put("attributesItem", GetattributesItem.GetattributesItemTime(time));
        }catch (JSONException e){
            e.printStackTrace();
        }
        //jsonObject.put("attributesItem", shuzu.shuzu(num));
        Log.e("sazhetime1", jsonObject_time.toString());
        return jsonObject_time.toString().replaceAll("\\\"","").replace("\\","");
    }
    public static String Getplace(String place[]){
        jsonObject_place = new JSONObject();
        try {
            jsonObject_place.put("tabID", 1);
            jsonObject_place.put("tabName", "场次");
            jsonObject_place.put("attributesItem", GetattributesItem.GetattributesItemPlace(place));

        }catch (JSONException e){
            e.printStackTrace();
        }
        //jsonObject.put("attributesItem", shuzu.shuzu(num));
        Log.e("sazhesession1", jsonObject_place.toString());
        return jsonObject_place.toString().replaceAll("\\\"","");
    }

}
