package Login_register;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import DateBase.DateBase_Login;
import net.sf.json.JSONObject;

public class Web_token implements Controller {
	private String code="";
	private String message="";
	private String token="";
	public Web_token() {}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n\n登入的web服务,更换长连接");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String Method=request.getMethod();
		/**
		 * 获取输出流，用来放回登入的报文
		 */
		PrintWriter out = response.getWriter(); // 获取 response 的输出流
		/*
		 * 定义变量用来获取Json解析后验证的报文
		 */
		System.out.println("数据请求的方法："+Method);
		if("POST".equals(Method))  {
			String read="";
			DateBase_Login TokenDate=new DateBase_Login();
			read=Json.SetJson(request.getReader());
	        JSONObject json = JSONObject.fromObject(read);
	        System.out.println("App端的Json数据："+json);
	        String account=json.getString("name");
	        String token1=json.getString("token");
	        System.out.println("解析Json的账号："+account+"解析的Json长连接"+token1);
	        if(TokenDate.isToken(account, token1)) {
	        	code="200";
	        	message="没有失效";
	        	token=TokenDate.setToken(account);
	        	System.out.println("更新的长连接："+token);
	        }
	        else {
	        	System.out.println("长连接失效了，重新去登入");
	        	code="500";
	        	message="失效";
	        	token=null;
	        }
		}
		else {
			System.out.println("GET请求");
			String query=request.getQueryString();
			System.out.println("GET请求的数据："+query);
			if(query==null) {
				System.out.println("数据请求出问题，没有数据源");
				code = "300";
				message = "账号密码错误，注意大小写";
				System.out.println("登入失败！");
			}
			else {
				
				code = "200";
				message = "登入成功";
				System.out.println("登入成功！");
				System.out.println("账号："+request.getParameter("name")+"密码："+request.getParameter("password"));
			}
			
		}
		/*
		 * 返回的报文，数据输出流返回
		 */
		JSONObject jsonObj2 = new JSONObject();
	       // jsonObj2.put("id",id);
		System.out.println("返回的数据：状态："+code+" 报文"+message+" 长连接"+token);
	        jsonObj2.put("code",code );
	        jsonObj2.put("message",message);
	        jsonObj2.put("token", token);
	       // String a=jsonObj2.toString();
	        response.getWriter().append(jsonObj2.toString());
		return null;
	}

}
