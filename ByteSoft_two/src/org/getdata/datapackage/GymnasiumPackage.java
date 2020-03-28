package org.getdata.datapackage;
/*
功能暂未使用....
*/
import org.table.Gymnasium;

import net.sf.json.JSONObject;

public class GymnasiumPackage {
	public static JSONObject GymnasiumPackage(Gymnasium gymnasium) {
		
		System.out.println("场馆编号:"+gymnasium.getVno());
		System.out.println("场馆名字:"+gymnasium.getVname());	
		System.out.println("场馆评分:"+gymnasium.getVscore());
		
		System.out.println("场馆地址:"+gymnasium.getVaddress());
		
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("场馆编号",gymnasium.getVno());
        jsonObj.put("场馆名",gymnasium.getVname());
        jsonObj.put("场馆地址",gymnasium.getVaddress());
        
       
        
        jsonObj.put("场馆负责人",gymnasium.getVfunctioinary());
        jsonObj.put("负责人电话",gymnasium.getVphone());
        jsonObj.put("场馆图片",gymnasium.getVpicture());
        jsonObj.put("场馆评价", gymnasium.getVscore());
        jsonObj.put("场馆球类型", gymnasium.getVball());
        
        jsonObj.put("场馆服务",gymnasium.getVserve());
        jsonObj.put("场馆介绍",gymnasium.getVintroduce());
        jsonObj.put("下单量",gymnasium.getVsum());			        
        jsonObj.put("地板", gymnasium.getVfloor());
		
       
        jsonObj.put("灯光",gymnasium.getVlighting());
        jsonObj.put("休息区",gymnasium.getVrest());
        jsonObj.put("售卖",gymnasium.getVsell());			        
        jsonObj.put("体育用品售卖", gymnasium.getVsellsport());
        
        jsonObj.put("坐标", gymnasium.getVlocation());
		return jsonObj;
	}
}
