package org.Infomation;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.table.Userinfo;

import DateBase.DateBaseInfomation;
import net.sf.json.JSONObject;

public class ModifyInfomation implements Controller {
	private String read=null;
	private String code=null;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("登入的web服务,更换信息");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String Method=request.getMethod();
		System.out.println("数据请求的方法："+Method);
		PrintWriter out = response.getWriter(); // 获取 response 的输出流
		if("POST".equals(Method))  {
			read=Json.SetJson(request.getReader());
	        System.out.println("打印read"+read);
	        JSONObject json = JSONObject.fromObject(read);
	        System.out.println("打包的json"+json);
	        String name=json.getString("Username");
	        String mode=json.getString("mode");
	        String context=json.getString("info");
	        if(DateBaseInfomation.ModifyInfomation(name, mode, context)) {
	        	code="200";
	        }
	        else {
	        	code="500";
	        }
		}
		else {
			
		}
		JSONObject jsonObj2 = new JSONObject();
		System.out.println("返回的报文：状态"+code);
	        jsonObj2.put("code",code );
	        response.getWriter().append(jsonObj2.toString());
		return null;
	}

}
