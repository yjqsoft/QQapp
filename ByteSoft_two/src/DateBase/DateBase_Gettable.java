package DateBase;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.table.Gorder;
import org.table.Gymnasium;
import org.table.Login;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class DateBase_Gettable {
	public DateBase_Gettable() {}
	
	
	/*获取场馆表的信息*/
	public String getGymnasium(String num) {
	
		Gymnasium gymnasium=new Gymnasium();
		Session session=HibernateSessionFactory.getSession();
		System.out.println("查询场馆编号为:"+num);
		if((gymnasium=(Gymnasium)session.get(Gymnasium.class, num))!=null) {
			
			System.out.println("查询场馆成功");
			try {
				session.clear();
				Transaction tran=session.beginTransaction();
				Query query=session.createQuery("from Gymnasium where vno='"+num+"'");
				query.setCacheable(false);
				List list=query.list();
				tran.commit();
				session.close();
				gymnasium=(Gymnasium) list.get(0);
				System.out.println("获取的场馆信息;"+gymnasium.getVaddress());					
			}
			catch(Exception e){
				System.out.println(e);
			}
			return gymnasium.getVaddress();
		}
		else {
			//if(table.getPassword().equals(password)) {
			
			System.out.println("查询结果：该场馆编号不存在");
			session.close();
			gymnasium=null;
			return null;
							
			}
	}
	public String getReserveOrder(String userid) {
	
		Gorder gorder;
		JSONArray jsonArr=new JSONArray();
		
		
		
		Session session=HibernateSessionFactory.getSession();
		
//		if((gorder=(Gorder)session.get(Gorder.class, userid))!=null) {
//			
//		}
		System.out.println("在这1");
		try {			
			session.clear();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Gorder");//where ouserId='"+userid+"'"ouserId
			System.out.println("在这2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			for(int i=0;i<list.size();i++) {
				gorder=(Gorder)list.get(i);//将信息取出来
				
				System.out.println("订单编号:"+gorder.getOid());
				System.out.println("订单类型:"+gorder.getOclass());	
				System.out.println("订单金额:"+gorder.getOmony());
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", gorder.getOid());			
				jsonObj.put("no",gorder.getOno());
		        jsonObj.put("time",gorder.getOtime());
		        jsonObj.put("type",gorder.getOclass());
		        jsonObj.put("appointmenttime", gorder.getOappointment().toString());
		        jsonObj.put("site",gorder.getOsite());//地点  位置
		        jsonObj.put("money",gorder.getOmony());
		        jsonObj.put("pay",gorder.getOpay()); 
		        jsonObj.put("place",gorder.getOplace());
				
		        jsonArr.add(jsonObj);
			}		
			System.out.println("获取订单的信息："+jsonArr);
			session.close();
		}
		catch(Exception e){
			System.out.println(e);
		}		
		return jsonArr.toString();
	}
	
}
