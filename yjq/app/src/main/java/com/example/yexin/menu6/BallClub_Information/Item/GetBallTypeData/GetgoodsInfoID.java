package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

import org.json.JSONArray;

/**
 * Created by 邱 on 2020/2/15.
 */

/*"goodsInfo": [
        {
        "tabID": 0,
        "tabName": "时间段",
        "tabValue": "08"
        },
        {
        "tabID": 1,
        "tabName": "场地",
        "tabValue": "1号场"
        }
        ]*/

public class GetgoodsInfoID {
    private static JSONArray jsonArray_goodsInfo =null;
    public static String getgoodsInfoId(String placenum,String placetiem){

        jsonArray_goodsInfo=new JSONArray();
        jsonArray_goodsInfo.put(GetgoodsInfo.getTime(placetiem+"∶00-"+(1+Integer.parseInt(placetiem))+"∶00"));
        jsonArray_goodsInfo.put(GetgoodsInfo.getPlace(placenum+"号场"));

        return jsonArray_goodsInfo.toString();
    }

}
//    for(int i=1;i<=15;i++){
//            if("1".equals(placeState[i]))
//            jsonArray_goodsInfo.put(getgoodID.getTime(""+i+6));
//            else
//            break;
//            }