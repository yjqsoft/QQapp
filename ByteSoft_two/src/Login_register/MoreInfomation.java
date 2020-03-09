package Login_register;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.table.Userinfo;

import DateBase.DateBaseInfomation;
import net.sf.json.JSONObject;

public class MoreInfomation implements Controller {
	private String NickName=null;  //昵称
	private String Signature=null;  //签名
	private int Level=0;  //等级
	private String sex=null;  //性别
	private String datetime=null;  //出生日期
	private String place=null;  //地点
	private String read=null;
	private String Picture=null;  //头像的地址
	public MoreInfomation() {}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("登入的web服务,获取信息服务");
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
	        String account=json.getString("name");
			Userinfo userinfo=DateBaseInfomation.getMoreInfomation(account);
			System.out.println("查询成功");
			NickName=userinfo.getNickname();  //昵称
			Signature=userinfo.getSignature();  //签名
			Level=userinfo.getLevels();  //等级
			sex=userinfo.getSex();  //性别
			Picture=userinfo.getNavheader();//头像
//			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			datetime = formatter.format(userinfo.getBirth());//生日
			//datetime=String.userinfo.getBirth();  
			place=userinfo.getRegion();  //地区
		}
		else {
			NickName=null;  //昵称
			Signature=null;  //签名
			Level=1;  //等级
			sex=null;  //性别
			datetime=null;  //生日
			place=null;  //地区
		}
		System.out.println("昵称"+NickName+"签名"+Signature+"等级"+Level+"性别"+sex+"生日"+"地区"+place+"头像的地址"+Picture);
		JSONObject jsonObj2 = new JSONObject();
	        jsonObj2.put("NickName",NickName );
	        jsonObj2.put("Signature",Signature);
	        jsonObj2.put("Level", Level);
	        jsonObj2.put("Sex", sex);
	        jsonObj2.put("Datetime", datetime);
	        jsonObj2.put("Place", place);
	        jsonObj2.put("Picture", Picture);
	        response.getWriter().append(jsonObj2.toString());
		return null;
	}

}
