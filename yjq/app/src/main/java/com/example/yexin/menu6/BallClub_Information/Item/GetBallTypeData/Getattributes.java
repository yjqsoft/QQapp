package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

import android.util.Log;

import org.json.JSONArray;

/**
 * Created by 邱 on 2020/2/15.
 */


public class Getattributes {
    private static JSONArray jsonArray_attributes =null;


    public static String Getattributes(String place[]){
        String  time [] ={"7∶00-8∶00","8∶00-9∶00","9∶00-10∶00","10∶00-11∶00","11∶00-12∶00",
                "12∶00-13∶00","13∶00-14∶00","14∶00-15∶00","15∶00-16∶00","16∶00-17∶00",
                "17∶00-18∶00","18∶00-19∶00","19∶00-20∶00","20∶00-21∶00","21∶00-22∶00"};
        //String place []={"1号场","2号场","3号场"};
        jsonArray_attributes=new JSONArray();
        jsonArray_attributes.put(attributes.Gettime(time));
        jsonArray_attributes.put(attributes.Getplace(place));

        Log.e("sazhe ", jsonArray_attributes.toString());
        return jsonArray_attributes.toString();

    }
}