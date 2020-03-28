package Login_register;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.Json.Json;
import org.hibernate.Query;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.table.Login;
import org.table.Userinfo;

import DateBase.DateBaseInfomation;
import DateBase.DateBase_Login;
import DateBase.Icon_up;
import net.sf.json.JSONObject;

public class Web_login implements Controller {
	private String code = "";
	private String message = "";
	private String token="";
	private String Picture=null;
	private String NickName=null;
	private int Level=0;
	
	public Web_login() {}
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
		if( "POST".equals(Method.toString())) {
			System.out.println("登陆结果成功："+Method);
		}
		if("POST".equals(Method.toString()))  {
			String read=""; //解析的Json报文
			int login_int;  //登入状态的选择
			DateBase_Login loginDate=new DateBase_Login();
			read=Json.SetJson(request.getReader());
	        JSONObject json = JSONObject.fromObject(read);
	        System.out.println("App端的Json数据"+json);
	        String account=json.getString("name");
	        String password=json.getString("password");
//	        String account="123";
//	        String password="1245";
//	        String IconTime=json.getString("IconTime");
	        System.out.println("解析Json的账号："+account+"解析Json的密码："+password);
	        login_int=loginDate.isLogin(account, password);  //查询用户是否存在，1存在，0密码错误，-1用户不存在
	        if(login_int==-1) {
	        	code="404";
	        	message="账号密码不存在";
	        	token="";
	        	Picture=null;//头像
	        }
	        else if(login_int==0) {
	        	code="500";
	        	message="账号密码错误";
	        	token="";
	        	Picture=null;//头像
	        }
	        else {
	        	code="200";
	        	message="登入成功";
	        	Userinfo userinfo=DateBaseInfomation.getMoreInfomation(account);
				System.out.println("查询成功");
	        	token=loginDate.setToken(account);
	        	Picture=userinfo.getNavheader();//头像
	        	NickName=userinfo.getNickname();  //昵称
				Level=userinfo.getLevels();  //等级
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
		System.out.println("返回的报文：状态"+code+" 报文"+message+" 长连接"+token+" 是否更新头像"+Picture+"昵称"+NickName+"等级"+Level);
	        jsonObj2.put("code",code );
	        jsonObj2.put("message",message);
	        jsonObj2.put("token", token);
	        jsonObj2.put("Picture", Picture);
	        jsonObj2.put("NickName", NickName);
	        jsonObj2.put("Level", Level);
	        response.getWriter().append(jsonObj2.toString());        
		return null;
	}
}
