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
import net.sf.json.JSONObject;

public class Alterorder implements Controller {
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("�����web����,������Ϣ");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String Method=request.getMethod();
		System.out.println("��������ķ�����"+Method);
		PrintWriter out = response.getWriter(); // ��ȡ response �������	
		JSONObject json=null;
		String read=Json.SetJson(request.getReader());
		try {			
		      json = JSONObject.fromObject(read);
		      System.out.println("App�˵�Json����"+json);
		}catch (Exception e) {
			e.printStackTrace();
		}       
		if("POST".equals(Method)) {	
			System.out.println("---POST��������---");
			if(json!=null) {
				
				if(json.getString("pay").equals("0")){

					   Session session=HibernateSessionFactory.getSession();
						Transaction tran=session.beginTransaction();
					try {
																		     
						Gorder gorder =(Gorder)session.get(Gorder.class, Integer.parseInt(json.getString("order_no")));
						gorder.setOpay("1");
				        session.update(gorder);
						tran.commit();
						session.clear();
						
						
						} catch (Exception e) {
							tran.rollback();
							e.printStackTrace();
						}finally{
							session.close();
						}
					//ɾ��
				}
		}else {
			System.out.println("---GET��������---");
		}		
		
		}
		return null;
	}
}
