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
	
	
	/*��ȡ���ݱ����Ϣ*/
	public String getGymnasium(String num) {
	
		Gymnasium gymnasium=new Gymnasium();
		Session session=HibernateSessionFactory.getSession();
		System.out.println("��ѯ���ݱ��Ϊ:"+num);
		if((gymnasium=(Gymnasium)session.get(Gymnasium.class, num))!=null) {
			
			System.out.println("��ѯ���ݳɹ�");
			try {
				session.clear();
				Transaction tran=session.beginTransaction();
				Query query=session.createQuery("from Gymnasium where vno='"+num+"'");
				query.setCacheable(false);
				List list=query.list();
				tran.commit();
				session.close();
				gymnasium=(Gymnasium) list.get(0);
				System.out.println("��ȡ�ĳ�����Ϣ;"+gymnasium.getVaddress());					
			}
			catch(Exception e){
				System.out.println(e);
			}
			return gymnasium.getVaddress();
		}
		else {
			//if(table.getPassword().equals(password)) {
			
			System.out.println("��ѯ������ó��ݱ�Ų�����");
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
		System.out.println("����1");
		try {			
			session.clear();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Gorder");//where ouserId='"+userid+"'"ouserId
			System.out.println("����2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			for(int i=0;i<list.size();i++) {
				gorder=(Gorder)list.get(i);//����Ϣȡ����
				
				System.out.println("�������:"+gorder.getOid());
				System.out.println("��������:"+gorder.getOclass());	
				System.out.println("�������:"+gorder.getOmony());
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", gorder.getOid());			
				jsonObj.put("no",gorder.getOno());
		        jsonObj.put("time",gorder.getOtime());
		        jsonObj.put("type",gorder.getOclass());
		        jsonObj.put("appointmenttime", gorder.getOappointment().toString());
		        jsonObj.put("site",gorder.getOsite());//�ص�  λ��
		        jsonObj.put("money",gorder.getOmony());
		        jsonObj.put("pay",gorder.getOpay()); 
		        jsonObj.put("place",gorder.getOplace());
				
		        jsonArr.add(jsonObj);
			}		
			System.out.println("��ȡ��������Ϣ��"+jsonArr);
			session.close();
		}
		catch(Exception e){
			System.out.println(e);
		}		
		return jsonArr.toString();
	}
	
}
