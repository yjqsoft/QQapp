package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

import org.json.JSONObject;

/**
 * Created by 邱 on 2020/2/15.
 */

 /*{
         "tabID": 0,
         "tabName": "时间段",
         "tabValue": "08"
         },
         {
         "tabID": 1,
         "tabName": "场地",
         "tabValue": "1号场"
         }*/
public class GetgoodsInfo {
    private static JSONObject jsonObject_time=null;
    private static JSONObject jsonObject_place=null;
    public static String getTime(String time){

        try{
            jsonObject_time=new JSONObject();
            jsonObject_time.put("tabID",0);
            jsonObject_time.put("tabName","时间段");
            jsonObject_time.put("tabValue",time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject_time.toString();
    }
    public static String getPlace(String place){
        try{
            jsonObject_place=new JSONObject();
            jsonObject_place.put("tabID",1);
            jsonObject_place.put("tabName","场地");
            jsonObject_place.put("tabValue",place);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject_place.toString();
    }
}