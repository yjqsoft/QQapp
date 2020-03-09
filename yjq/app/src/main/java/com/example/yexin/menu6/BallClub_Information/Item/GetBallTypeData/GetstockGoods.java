package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by 邱 on 2020/2/15.
 */

/*{
        "goodsID": 1,

        "goodsInfo": [
        {
        "tabID": 0,
        "tabName": "时间段",
        "tabValue": "08"
        },
        {
        "tabID": 1,
        "tabName": "场地",
        "tabValue": "1号场"
        }*/
public class GetstockGoods {

    private static JSONObject jsonArray_goodsInfo =null;
    private static JSONArray jsonArr_school =null;
    private static JSONArray jsonArr_stockGoods =null;
    private static JSONObject jsonObject_goods=null;
    public static String getgoods(String placeNum[],String placeState[][]){//场状态
        int n=1;
        try{
            jsonArr_stockGoods=new JSONArray();
            Log.e("size","length:"+placeNum.length);
            for(int j=1;j<=placeNum.length-1;j++){
                Log.e("size",j+":"+placeState[j].length);
                for(int i=1;i<=15;i++){
                    if("1".equals(placeState[j][i])) {//if场次存在为1
                        jsonObject_goods=new JSONObject();
                        jsonObject_goods.put("goodsID", n++);//编号
                        jsonObject_goods.put("goodsInfo", GetgoodsInfoID.getgoodsInfoId(placeNum[j]+"",i+6+""));//场情况  {tabID:0,tabName:时间段,tabValue:7-8},{tabID:1,tabName:场地,tabValue:1号场}
                        Log.e("sa111px", jsonObject_goods.toString());
                        jsonArr_stockGoods.put(jsonObject_goods.toString().replaceAll("\\\"","").replace("\\",""));
                    }
                    else
                        continue;
                }
            }

            Log.e("jsonArr_stockGoods",jsonArr_stockGoods.toString());
            Log.e("jsonArr_stockGoods","长度为:"+jsonArr_stockGoods.length());

        }catch (Exception e){
            e.printStackTrace();
        }
        Log.e("sa111good","内容为:"+jsonObject_goods.toString().replaceAll("\\\"","").replace("\\",""));
        return jsonArr_stockGoods.toString();
    }
}