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
	
	
	/*获取场馆表的信息*/
	public static JSONObject getGymnasium(String num) {
	
		Gymnasium gymnasium=new Gymnasium();
		Session session=HibernateSessionFactory.getSession();
		System.out.println("查询场馆编号为:"+num);
		if((gymnasium=(Gymnasium)session.get(Gymnasium.class, num))!=null) {
			JSONObject jsonObj = new JSONObject();
			System.out.println("查询场馆成功");
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
					System.out.println("场馆编号:"+gymnasium.getVno());
					System.out.println("场馆名字:"+gymnasium.getVname());	
					System.out.println("场馆评分:"+gymnasium.getVscore());		
					System.out.println("场馆地址:"+gymnasium.getVaddress());
					
					jsonObj.put("场馆编号",gymnasium.getVno());
			        jsonObj.put("场馆名",gymnasium.getVname());
			        jsonObj.put("场馆地址",gymnasium.getVaddress());        
			        jsonObj.put("场馆负责人",gymnasium.getVfunctioinary());
			        jsonObj.put("负责人电话",gymnasium.getVphone());
			        jsonObj.put("场馆图片",gymnasium.getVpicture());
			        jsonObj.put("场馆评价", gymnasium.getVscore());
			        jsonObj.put("场馆球类型", gymnasium.getVball());		        
			        jsonObj.put("场馆服务",gymnasium.getVserve());
			        jsonObj.put("场馆介绍",gymnasium.getVintroduce());
			        jsonObj.put("下单量",gymnasium.getVsum());			        
			        jsonObj.put("地板", gymnasium.getVfloor());						       
			        jsonObj.put("灯光",gymnasium.getVlighting());
			        jsonObj.put("休息区",gymnasium.getVrest());
			        jsonObj.put("售卖",gymnasium.getVsell());			        
			        jsonObj.put("体育用品售卖", gymnasium.getVsellsport());			        
			        jsonObj.put("坐标", gymnasium.getVlocation());			        
				}				
				System.out.println("获取的场馆信息;"+jsonObj.toString());					
			}
			catch(Exception e){
				System.out.println(e);
			}
			return jsonObj;
		}
		else {		
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
				
				Date date=new Date();
				date=(Date)gorder.getOendAppointmenttime();
			
				try {
					System.out.println("数据库中时间："+date.getTime());
				}catch (Exception e) {
					System.out.println("报错");
					e.printStackTrace();
				}
				
				//获取当前时间
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss		        
			    Date dateSystem = new Date(System.currentTimeMillis());
			    //simpleDateFormat.format(dateSystem);
			    try {
					System.out.println("系统中时间："+dateSystem.getTime());
				}catch (Exception e) {
					
					e.printStackTrace();
				}
			      
			    System.out.println("系统时间："+simpleDateFormat.format(dateSystem));
				if(date.getTime()<=dateSystem.getTime()) {
					System.out.println("系统时间大执行continue");
					if(DataBase_Altertable.DelectGorder(gorder.getOid())) {
						System.out.println("删除gorder成功！");
					}
					continue;
				}
				if(date.getTime()>dateSystem.getTime()) {
					System.out.println("系统时间小执行continue");

				}		
				//continue;
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", gorder.getOid());			
				jsonObj.put("no",gorder.getOno());
		        jsonObj.put("time",gorder.getOtime());
		        jsonObj.put("type",gorder.getOclass());
		        jsonObj.put("appointmenttime", gorder.getOappointmenttime().toString().substring(0, 10));
		        jsonObj.put("endappointmenttime", gorder.getOendAppointmenttime().toString());
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
	
	public static String getGame() {
		
		Game game;
		JSONArray jsonArr=new JSONArray();
			
		
		Session session=HibernateSessionFactory.getSession();
		System.out.println("在这1");
		try {			
			session.clear();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Game");//where ouserId='"+userid+"'"ouserId
			System.out.println("在这2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			for(int i=0;i<list.size();i++) {
				game=(Game)list.get(i);//将信息取出来
				
				System.out.println("比赛编号:"+game.getId());
				System.out.println("比赛类型:"+game.getGameType());	
				System.out.println("比赛标题:"+game.getGameTitle());
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", game.getId());			
		        jsonObj.put("type",game.getGameType());
		        jsonObj.put("title", game.getGameTitle());
		        jsonObj.put("time", game.getTime().toString());
		        jsonObj.put("pic",game.getPicture());//地点  位置
		        jsonObj.put("content",game.getContent());
		        jsonObj.put("gamedetailsid",game.getGamedetailsid());
		        
		        jsonArr.add(jsonObj);
			}		
			System.out.println("获取比赛的信息："+jsonArr);
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
		System.out.println("在这1");
		try {			
			session.clear();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Gamedetails where id='"+gamedetailsid+"'");//where ouserId='"+userid+"'"ouserId
			System.out.println("在这2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			for(int i=0;i<list.size();i++) {
				gamedetails=(Gamedetails)list.get(i);//将信息取出来
				
				System.out.println("赛事编号:"+gamedetails.getId());
				System.out.println("赛事类型:"+gamedetails.getGametype());	
				
				JSONObject jsonObj = new JSONObject();
				
				
				jsonObj.put("id", gamedetails.getId());			
		        jsonObj.put("type",gamedetails.getGametype());
		        jsonObj.put("hosttime", gamedetails.getHosttime().toString());
		        jsonObj.put("hostplace", gamedetails.getHostplace());
		        jsonObj.put("applytime",gamedetails.getApplytime());//地点  位置
		        jsonObj.put("organizer",gamedetails.getOrganizer());
		        jsonObj.put("activeobject",gamedetails.getActiveobject());
		        jsonObj.put("games",gamedetails.getGames());
		        jsonObj.put("phone",gamedetails.getPhone());
		        
		        jsonArr.add(jsonObj);
			}		
			System.out.println("获取赛事的信息："+jsonArr);
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
			System.out.println("在这2"+query);
			//query.setCacheable(false);
			List list=query.list();
			tran.commit();
			//session.close();			
			System.out.println("query:"+query);			
			return list;
			
		}else {			
			System.out.println("查询结果：该场馆编号不存在");
			session.close();
			return null;						
			}
	
	}
	
}
