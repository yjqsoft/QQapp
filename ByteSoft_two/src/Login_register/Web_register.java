package Login_register;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import DateBase.DateBase_Addtable;
import DateBase.DateBase_Login;
import net.sf.json.JSONObject;

public class Web_register implements Controller {
	private String code="";
	private String message="";
	private String token="";
	public Web_register() {}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				System.out.println("登入的web服务,注册服务");
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				String Method=request.getMethod();
				/**
				 * 获取输出流，用来放回登入的报文
				 */
				PrintWriter out = response.getWriter(); // 获取 response 的输出流
				System.out.println("数据请求的方法："+Method);
				System.out.println("结果："+"POST".equals(Method));
				if("POST".equals(Method.toString()))  {
					String read="";
					DateBase_Login RegisterDate=new DateBase_Login();
					System.out.println("POST请求");
					read=Json.SetJson(request.getReader());
			        System.out.println("打印read"+read);
			        JSONObject json = JSONObject.fromObject(read);
			        System.out.println("json"+json);
			        String account=json.getString("name");
			        String password=json.getString("password");
			        if(RegisterDate.isRegister(account, password)) {
			        	System.out.println("注册成功");
			        	code="200";
			        	message="注册成功";
			        	//DateBase_Addtable.AddUserinfo(account); qiu  添加Userinfo 表的数据  先注释 
			        }
			        else {
			        	System.out.println("注册失败");
			        	code="500";
			        	message="注册失败,账号存在";
			        }
					System.out.println("账号："+account+"密码："+password);
				}
			else/* if("GET".equals(Method))*/{
					System.out.println("GET请求");
					String query=request.getQueryString();
					System.out.println("GET请求的数据："+query);
					if(query==null) {
						System.out.println("数据请求出问题，没有数据源");
					}
					else {
						System.out.println("账号："+request.getParameter("name")+"密码："+request.getParameter("password"));
					}
				}
				/*
				 * 返回的报文，数据输出流返回
				 */
				JSONObject jsonObj2 = new JSONObject();
			       // jsonObj2.put("id",id);
				System.out.println("返回数据"+code+" "+message+" "+token);
			        jsonObj2.put("code",code );
			        jsonObj2.put("message",message);
			        response.getWriter().append(jsonObj2.toString());
		return null;
	}

}
