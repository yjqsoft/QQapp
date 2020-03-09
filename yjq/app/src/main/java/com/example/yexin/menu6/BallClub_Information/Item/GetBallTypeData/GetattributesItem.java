package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

import android.util.Log;

import org.json.JSONArray;

/**
 * Created by 邱 on 2020/2/15.
 */


public class GetattributesItem {
    private static JSONArray jsonArray_attributesItem =null;
    public static String GetattributesItemTime(String time[]){

        jsonArray_attributesItem=new JSONArray();
        for (int i=0;i<time.length;i++){
            jsonArray_attributesItem.put(time[i]);
        }
        return jsonArray_attributesItem.toString();
    }

    public static String GetattributesItemPlace(String place[]){
        jsonArray_attributesItem=new JSONArray();
        for (int i=1;i<place.length;i++){
            Log.e("shuzu", place.length+place[i]);
            jsonArray_attributesItem.put(place[i]+"号场");
        }
        Log.e("sazhec", jsonArray_attributesItem.toString());
        return jsonArray_attributesItem.toString().replaceAll("\\\"","");
    }
}
