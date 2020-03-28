package org.altertable;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Json.Json;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.table.A;
import org.table.Gorder;

import DateBase.DataBase_Altertable;
import DateBase.DateBase_Addtable;
import DateBase.DateBase_Gettable;
import net.sf.json.JSONObject;

public class Alterorder implements Controller {
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("登入的web服务,更换信息");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String Method=request.getMethod();
		System.out.println("数据请求的方法："+Method);
		PrintWriter out = response.getWriter(); // 获取 response 的输出流	
		JSONObject json=null;
		String read=Json.SetJson(request.getReader());
		try {			
		      json = JSONObject.fromObject(read);
		      System.out.println("App端的Json数据"+json);
		}catch (Exception e) {
			e.printStackTrace();
		}       
		if("POST".equals(Method)) {	
			System.out.println("---POST方法进入---");
			if(json!=null) {
				
				if(json.getString("pay").equals("0")){

					   Session session=HibernateSessionFactory.getSession();
						Transaction tran=session.beginTransaction();
					try {
																		     
						Gorder gorder =(Gorder)session.get(Gorder.class, Integer.parseInt(json.getString("order_no")));
						
						String stadium_no=gorder.getOno();
						String place=gorder.getOplace().substring(0, 1);
						String time=gorder.getOtime().substring(0, 1);
						String table_type=gorder.getOclass();
						
						gorder.setOpay("1");
				        session.update(gorder);
						tran.commit();
						session.clear();
						session.close();
						
						System.out.print("修改ABC数据为："+stadium_no+" "+place+" "+time+" "+table_type);
						if(DataBase_Altertable.AlterABC(stadium_no,place,time,table_type)) {
							System.out.print("修改ABC数据成功");
						}
						} catch (Exception e) {
							tran.rollback();
							e.printStackTrace();
						}
					//删除
					response.getWriter().append("SUCCESS");	
				}
		}else {
			System.out.println("---GET方法进入---");
		}		
		
		}
		return null;
	}
}
