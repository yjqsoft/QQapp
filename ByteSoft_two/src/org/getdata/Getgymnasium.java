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
		System.out.println("�����web����,�������");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String Method=request.getMethod();
		/**
		 * ��ȡ������������Żص���ı���
		 */
		PrintWriter out = response.getWriter(); // ��ȡ response �������
		        
		System.out.println("��������ķ�����"+Method);
		
		if(true/*"POST".equals(Method)*/)  {
			System.out.println("------POST�ķ�������------");
			String read=""; //������Json����
			DateBase_Gettable gymnasium=new DateBase_Gettable();
			//read=Json.SetJson(request.getReader());
	       // JSONObject json = JSONObject.fromObject(read);
	        //System.out.println("App�˵�Json����"+json);
	        //String num=json.getString("num");
			String num="201720180321";
	        if(gymnasium.getGymnasium(num)!=null) {
	        	/*
	        	 * ���ػ�ȡ�ĳ�����Ϣ*/
	        	System.out.println("���ݱ��е�����Ϊ:"+gymnasium.getGymnasium(num));
	        	response.getWriter().append(gymnasium.getGymnasium(num));	
	        }
	        
	        
		}else {
			System.out.println("------GET�ķ�������------");
			
		}		
		return null;
	}

}
