package com.example.yexin.menu6.BallClub_Information.Item.GetBallTypeData;

/**
 * Created by 邱 on 2020/2/15.
 */

import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public  class  GetData {
    private static JSONObject jsonObject=null;
    private static JSONObject jsonObject_wai=null;
    private static JSONArray jsonArray_attributesItem =null;
    private static JSONArray jsonArray_attributes=null;
    private static int n=1;

    public static String setInfo(String placenum,String placeStateString[]){
        //int  num [] ={1,2,3};
        try {
//            jsonObject = new JSONObject();
//
//            /*添加场次*/
//            jsonArray_attributesItem=new JSONArray();
//
//
//            /*jsonArray_attributesItem.put(1);
//            jsonArray_attributesItem.put(2);
//            jsonArray_attributesItem.put(3);*/
//
//            /*添加时间段*/
//            jsonObject.put("tabID", 0);
//            jsonObject.put("tabName", "时间段");
//            jsonObject.put("attributesItem", shuzu.shuzu(num));
//
//            /*添加外部attribute，stockGoods*/
//            jsonArray_attributes=new JSONArray();
//            jsonArray_attributes.put(jsonObject);
//
//            jsonObject_wai=new JSONObject();
//            jsonObject_wai.put("attributes",jsonArray_attributes);

         /*
         *
            "tabID": 1,
            "tabName": "场地",
            "attributesItem": [
                "1号场",
                "2号场",
                "3号场",
                "4号场",
                "5号场",
                "6号场",
                "7号场",
                "8号场",
                "9号场",
                "10号场",
                "12号场",
                "13号场"
            ]
        }*/
//            String placenum="12";
//            String placeStateString[]={"111110111111111","111111111111011"};

            String placeState[] [] =new String[placenum.length()+1][16];
            //Log.e("num12",placeState[1][1]);
            Log.e("num12",""+placenum.length()+"  "+placeStateString.length);
            String [] placeNum=new String [placenum.length()+1];
            for(int i=1;i<=placenum.length();i++){
                placeNum[i]=placenum.substring(i-1,i);
                Log.e("num",placeNum[i]);
            }

            Log.e("num12",""+placeStateString+"在这");
            jsonObject = new JSONObject();
            jsonObject.put("attributes",Getattributes.Getattributes(placeNum));

            for (int j=1;j<=placenum.length();j++) {

                Log.e("num12","placeStateString:"+j+" "+placeStateString[j-1].length()+placeStateString[j-1]);
                for(int i=1;i<=placeStateString[j-1].length();i++){
                    placeState[j][i]=placeStateString[j-1].substring(i-1,i);
                    Log.e("num1",placeState[j][i]);
                }
                Log.e("num12","place:"+placeState.length);
                jsonObject.put("stockGoods", GetstockGoods.getgoods(placeNum,placeState));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        /*jsonArray_attributes=new JSONArray();
        jsonArray_attributes.put("attributes",time_chang.shuzu());
        Log.e("sa", jsonArray_attributes.toString());*/
        //Log.e("sajsonArray", jsonArray.toString());
        String  t= jsonObject.toString().replaceAll("\\\"","");
        Log.e("sa1chang0", t.replace("\\",""));
        String a=t.replace("\\","");
        Log.e("sa1cahng1", a);

        return a;
    }

}
