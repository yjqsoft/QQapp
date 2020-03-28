package org.getdata;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import DateBase.DateBase_Gettable;
import net.sf.json.JSONObject;

public class Getgamedetails implements Controller{

	String gamedetailsid=null;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub


		System.out.println("登入的web服务,更换信息");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String read=""; //解析的Json报文	
		JSONObject json=null;
		read=Json.SetJson(request.getReader());
		try {
			
		      json = JSONObject.fromObject(read);
		      System.out.println("App端的Json数据"+json);
		      gamedetailsid=json.getString("gamedetailsid");
		}catch (Exception e) {
			e.printStackTrace();
		}
		String Method=request.getMethod();
		/**
		 * 获取输出流，用来放回登入的报文
		 */
		PrintWriter out = response.getWriter(); // 获取 response 的输出流
		        
		System.out.println("数据请求的方法："+Method);
		
		if(true/*"POST".equals(Method)*/)  {
			System.out.println("------POST的方法进入------");
			
	        if(DateBase_Gettable.gameDetailsid(gamedetailsid)!=null) {
	        	/*
	        	 * 返回获取的场馆信息*/
	        	System.out.println("比赛表中的内容为:"+DateBase_Gettable.gameDetailsid(gamedetailsid));
	        	response.getWriter().append(DateBase_Gettable.gameDetailsid(gamedetailsid).toString());	
	        }
	        
	        
		}else {
			System.out.println("------GET的方法进入------");
			
		}		
		return null;
	}
}
