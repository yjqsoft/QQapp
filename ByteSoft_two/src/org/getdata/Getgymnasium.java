package org.getdata;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import DateBase.DateBase_Gettable;
import DateBase.DateBase_Login;
import net.sf.json.JSONObject;

public class Getgymnasium implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("登入的web服务,登入服务");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String Method=request.getMethod();
		/**
		 * 获取输出流，用来放回登入的报文
		 */
		PrintWriter out = response.getWriter(); // 获取 response 的输出流
		        
		System.out.println("数据请求的方法："+Method);
		
		if(true/*"POST".equals(Method)*/)  {
			System.out.println("------POST的方法进入------");
			String read=""; //解析的Json报文
			DateBase_Gettable gymnasium=new DateBase_Gettable();
			//read=Json.SetJson(request.getReader());
	       // JSONObject json = JSONObject.fromObject(read);
	        //System.out.println("App端的Json数据"+json);
	        //String num=json.getString("num");
			String num="201720180321";
	        if(gymnasium.getGymnasium(num)!=null) {
	        	/*
	        	 * 返回获取的场馆信息*/
	        	System.out.println("场馆表中的内容为:"+gymnasium.getGymnasium(num));
	        	response.getWriter().append(gymnasium.getGymnasium(num));	
	        }
	        
	        
		}else {
			System.out.println("------GET的方法进入------");
			
		}		
		return null;
	}

}
