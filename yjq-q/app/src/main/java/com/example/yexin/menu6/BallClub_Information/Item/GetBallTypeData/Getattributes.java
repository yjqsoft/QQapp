package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

import android.util.Log;

import org.json.JSONArray;

/**
 * Created by 邱 on 2020/2/15.
 */


public class Getattributes {
    private static JSONArray jsonArray_attributes =null;


    public static String Getattributes(String place[]){
        String  time [] ={"7-8","8-9","9-10","10-11","11-12","12-13","13-14","14-15","15-16","16-17","17-18","18-19","19-20","20-21","21-22"};
        //String place []={"1号场","2号场","3号场"};
        jsonArray_attributes=new JSONArray();
        jsonArray_attributes.put(attributes.Gettime(time));
        jsonArray_attributes.put(attributes.Getplace(place));

        Log.e("sazhe ", jsonArray_attributes.toString());
        return jsonArray_attributes.toString();

    }
}