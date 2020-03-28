package org.getdata;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import DateBase.DateBase_Gettable;
import net.sf.json.JSONArray;

public class GetPersonalCollection implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("登入的web服务,登入服务");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		JSONArray jsonArr=null;
		
		String Method=request.getMethod();
		/**
		 * 获取输出流，用来放回登入的报文
		 */
		PrintWriter out = response.getWriter(); // 获取 response 的输出流
		        
		System.out.println("数据请求的方法："+Method);
		
		if(true/*"POST".equals(Method)*/)  {
			System.out.println("------POST的方法进入------");
			String read=""; //解析的Json报文
			DateBase_Gettable getcollection=new DateBase_Gettable();
			//read=Json.SetJson(request.getReader());
	       // JSONObject json = JSONObject.fromObject(read);
	        //System.out.println("App端的Json数据"+json);
	        //String num=json.getString("num");
			String userid="18879942330";
	        if(getcollection.getCollection(userid)!=null) {
	        	jsonArr=new JSONArray();
	        	/*
	        	 * 返回获取的场馆信息*/
	        	System.out.println("场馆表中的内容为:"+getcollection.getCollection(userid));
	        	for(int i=0;i<getcollection.getCollection(userid).size();i++) {
	        		System.out.println("id为："+DateBase_Gettable.getGymnasium(getcollection
	        				.getCollection(userid)
	        				.get(i)
	        				.toString()));
	        		
	        		 jsonArr.add(DateBase_Gettable.getGymnasium(getcollection
		        				.getCollection(userid)
		        				.get(i)
		        				.toString()));
	        	}
	        	
	        }
	        response.getWriter().append(jsonArr.toString());	 
	        
		}else {
			System.out.println("------GET的方法进入------");
			
		}		
		return null;
	}

}