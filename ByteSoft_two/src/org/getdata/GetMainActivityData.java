package org.getdata;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.hibernate.Query;
import org.hibernate.Session;
import org.ov.HibernateSessionFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.table.Gymnasium;
import org.table.Login;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetMainActivityData implements Controller{

	public Gymnasium gymnasium;
	String content="";
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("登入的web服务,更换信息");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		String read=""; //解析的Json报文	
//		JSONObject json=null;
//		read=Json.SetJson(request.getReader());
//		try {
//			
//		      json = JSONObject.fromObject(read);
//		      System.out.println("App端的Json数据"+json);
//		      content=json.getString("content");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
  
		
		
		String Method=request.getMethod();
		System.out.println("数据请求的方法："+Method);

		JSONArray jsonArr = new JSONArray();
		if("POST".equals(Method)) {			
			System.out.println("----POST方法进入------");
			Session session=HibernateSessionFactory.getSession();
			try {
				Query query=session.createQuery("from Gymnasium");
				
				System.out.println(query);
				List list=query.list();
				if(list.size()==0) {
					System.out.println("无场馆数据");
				}
				else {					
					for (int i=0;i<list.size();i++) {	
						gymnasium=(Gymnasium)list.get(i);
						
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
				        jsonArr.add(jsonObj);
					}
					
					
			       System.out.println("JSON 址:"+jsonArr.toString());
				}				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
						
		}else {
			System.out.println("----GET方法进入------");
		}
		
	        response.getWriter().append(jsonArr.toString());		
		return null;
	}

}
