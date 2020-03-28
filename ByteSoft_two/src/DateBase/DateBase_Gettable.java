package DateBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.table.Collection;
import org.table.Game;
import org.table.Gamedetails;
import org.table.Gorder;
import org.table.Gymnasium;
import org.table.Login;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class DateBase_Gettable {
	public DateBase_Gettable() {}
	
	
	/*��ȡ���ݱ����Ϣ*/
	public static JSONObject getGymnasium(String num) {
	
		Gymnasium gymnasium=new Gymnasium();
		Session session=HibernateSessionFactory.getSession();
		System.out.println("��ѯ���ݱ��Ϊ:"+num);
		if((gymnasium=(Gymnasium)session.get(Gymnasium.class, num))!=null) {
			JSONObject jsonObj = new JSONObject();
			System.out.println("��ѯ���ݳɹ�");
			try {
				session.clear();
				Transaction tran=session.beginTransaction();
				Query query=session.createQuery("from Gymnasium where vno='"+num+"'");
				query.setCacheable(false);
				List list=query.list();
				tran.commit();			
				session.close();				
				for (int i=0;i<list.size();i++) {	
					gymnasium=(Gymnasium)list.get(i);
					System.out.println("���ݱ��:"+gymnasium.getVno());
					System.out.println("��������:"+gymnasium.getVname());	
					System.out.println("��������:"+gymnasium.getVscore());		
					System.out.println("���ݵ�ַ:"+gymnasium.getVaddress());
					
					jsonObj.put("���ݱ��",gymnasium.getVno());
			        jsonObj.put("������",gymnasium.getVname());
			        jsonObj.put("���ݵ�ַ",gymnasium.getVaddress());        
			        jsonObj.put("���ݸ�����",gymnasium.getVfunctioinary());
			        jsonObj.put("�����˵绰",gymnasium.getVphone());
			        jsonObj.put("����ͼƬ",gymnasium.getVpicture());
			        jsonObj.put("��������", gymnasium.getVscore());
			        jsonObj.put("����������", gymnasium.getVball());		        
			        jsonObj.put("���ݷ���",gymnasium.getVserve());
			        jsonObj.put("���ݽ���",gymnasium.getVintroduce());
			        jsonObj.put("�µ���",gymnasium.getVsum());			        
			        jsonObj.put("�ذ�", gymnasium.getVfloor());						       
			        jsonObj.put("�ƹ�",gymnasium.getVlighting());
			        jsonObj.put("��Ϣ��",gymnasium.getVrest());
			        jsonObj.put("����",gymnasium.getVsell());			        
			        jsonObj.put("������Ʒ����", gymnasium.getVsellsport());			        
			        jsonObj.put("����", gymnasium.getVlocation());			        
				}				
				System.out.println("��ȡ�ĳ�����Ϣ;"+jsonObj.toString());					
			}
			catch(Exception e){
				System.out.println(e);
			}
			return jsonObj;
		}
		else {		
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
				
				Date date=new Date();
				date=(Date)gorder.getOendAppointmenttime();
			
				try {
					System.out.println("���ݿ���ʱ�䣺"+date.getTime());
				}catch (Exception e) {
					System.out.println("����");
					e.printStackTrace();
				}
				
				//��ȡ��ǰʱ��
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss		        
			    Date dateSystem = new Date(System.currentTimeMillis());
			    //simpleDateFormat.format(dateSystem);
			    try {
					System.out.println("ϵͳ��ʱ�䣺"+dateSystem.getTime());
				}catch (Exception e) {
					
					e.printStackTrace();
				}
			      
			    System.out.println("ϵͳʱ�䣺"+simpleDateFormat.format(dateSystem));
				if(date.getTime()<=dateSystem.getTime()) {
					System.out.println("ϵͳʱ���ִ��continue");
					if(DataBase_Altertable.DelectGorder(gorder.getOid())) {
						System.out.println("ɾ��gorder�ɹ���");
					}
					continue;
				}
				if(date.getTime()>dateSystem.getTime()) {
					System.out.println("ϵͳʱ��Сִ��continue");

				}		
				//continue;
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", gorder.getOid());			
				jsonObj.put("no",gorder.getOno());
		        jsonObj.put("time",gorder.getOtime());
		        jsonObj.put("type",gorder.getOclass());
		        jsonObj.put("appointmenttime", gorder.getOappointmenttime().toString().substring(0, 10));
		        jsonObj.put("endappointmenttime", gorder.getOendAppointmenttime().toString());
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
	
	public static String getGame() {
		
		Game game;
		JSONArray jsonArr=new JSONArray();
			
		
		Session session=HibernateSessionFactory.getSession();
		System.out.println("����1");
		try {			
			session.clear();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Game");//where ouserId='"+userid+"'"ouserId
			System.out.println("����2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			for(int i=0;i<list.size();i++) {
				game=(Game)list.get(i);//����Ϣȡ����
				
				System.out.println("�������:"+game.getId());
				System.out.println("��������:"+game.getGameType());	
				System.out.println("��������:"+game.getGameTitle());
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", game.getId());			
		        jsonObj.put("type",game.getGameType());
		        jsonObj.put("title", game.getGameTitle());
		        jsonObj.put("time", game.getTime().toString());
		        jsonObj.put("pic",game.getPicture());//�ص�  λ��
		        jsonObj.put("content",game.getContent());
		        jsonObj.put("gamedetailsid",game.getGamedetailsid());
		        
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
	
	
public static String gameDetailsid(String gamedetailsid) {
		
		Gamedetails gamedetails;
		JSONArray jsonArr=new JSONArray();
			
		
		Session session=HibernateSessionFactory.getSession();
		System.out.println("����1");
		try {			
			session.clear();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Gamedetails where id='"+gamedetailsid+"'");//where ouserId='"+userid+"'"ouserId
			System.out.println("����2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			for(int i=0;i<list.size();i++) {
				gamedetails=(Gamedetails)list.get(i);//����Ϣȡ����
				
				System.out.println("���±��:"+gamedetails.getId());
				System.out.println("��������:"+gamedetails.getGametype());	
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", gamedetails.getId());			
		        jsonObj.put("type",gamedetails.getGametype());
		        jsonObj.put("hosttime", gamedetails.getHosttime().toString());
		        jsonObj.put("hostplace", gamedetails.getHostplace());
		        jsonObj.put("applytime",gamedetails.getApplytime());//�ص�  λ��
		        jsonObj.put("organizer",gamedetails.getOrganizer());
		        jsonObj.put("activeobject",gamedetails.getActiveobject());
		        jsonObj.put("games",gamedetails.getGames());
		        jsonObj.put("phone",gamedetails.getPhone());
		        
		        jsonArr.add(jsonObj);
			}		
			System.out.println("��ȡ���µ���Ϣ��"+jsonArr);
			session.close();
		}
		catch(Exception e){
			System.out.println(e);
		}		
		return jsonArr.toString();
	}
	
	public List getCollection(String userid) {
		Session session=HibernateSessionFactory.getSession();
		session.clear();
			
		if(userid!=null) {
			Collection collection=new Collection();
			
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("select gymnasiumId from Collection where UserId='"+userid+"'");//where ouserId='"+userid+"'"ouserId
			System.out.println("����2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			return list;
			
		}else {			
			System.out.println("��ѯ������ó��ݱ�Ų�����");
			session.close();
			return null;						
			}
	
	}
	
}
