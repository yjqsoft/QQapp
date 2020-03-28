package DateBase;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.altertable.DeleteColllectionData;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.table.Collection;
import org.table.Gorder;
import org.table.Joingame;
import org.table.Login;
import org.table.Userinfo;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DateBase_Addtable {

	
	public Boolean AddGorder(JSONObject jsondate) {
		
		Gorder gorder=new Gorder();
		
		Session session=HibernateSessionFactory.getSession();		
		System.out.println("在这1AddGorder");
		try {			
			session.clear();
			Transaction tran=session.beginTransaction();
			//gorder.setOid(5);
			gorder.setOuserId(jsondate.getString("userId"));
			gorder.setOno(jsondate.getString("no"));
			gorder.setOplace(jsondate.getString("place"));
			gorder.setOtime(jsondate.getString("time"));
			gorder.setOclass(jsondate.getString("Class"));
			gorder.setOpay(jsondate.getString("pay"));

			DateFormat formatreserver = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp Nowtime = new Timestamp(formatreserver.parse(jsondate.getString("appointment")).getTime());
			System.out.println("开始时间2："+Nowtime);
			gorder.setOappointmenttime(Nowtime);
			
		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if(jsondate.getString("pay").equals("0")) {	
				Timestamp Endtime = new Timestamp(format.parse(jsondate.getString("endappointment")).getTime());
				gorder.setOendAppointmenttime(Endtime);	
				System.out.println("开始时间1："+Nowtime+"结束时间1："+Endtime);
							
			}
			
			
			gorder.setOsite(jsondate.getString("site"));
			gorder.setOmony(Float.parseFloat(jsondate.getString("money")));
			
			session.save(gorder);
			tran.commit();
			session.close();
			String temp1[]=jsondate.getString("time").split("-");
			String temp2[]=jsondate.getString("place").split("号");
		
			if(jsondate.getString("pay").equals("1")){

				//删除场馆表中的数据(放入  场馆编号，场号，修改的时间段  表类型（A,B,C..）)
				if(DataBase_Altertable.AlterABC(jsondate.getString("no"),
						temp2[0],//jsondate.getString("place").substring(0, 1)
						temp1[0],//jsondate.getString("time").substring(0, 1)
						jsondate.getString("Class"))) {
					System.out.println("修改ABC中的数据成功");				
				}else {
					System.out.println("修改ABC中的数据失败");
				}
				//删除
			}
		}catch(Exception e){
			System.out.println(e);
			return false;
		}		
		return true;
	}
	
public Boolean AddCollection(JSONObject jsondate) {
		Session session=HibernateSessionFactory.getSession();		
		System.out.println("在这1修改Collection");		
		session.clear();
		Transaction tran=session.beginTransaction();
		
		Query query=null;
		query=session.createQuery("from Collection where userId='"+
		jsondate.getString("userId")+"' and gymnasiumId='"+jsondate.getString("gymnasiumId")+"'");
		System.out.println(query);
		System.out.println(query.list().size());
		if(query.list().size()!=0){
			System.out.println("存在");
			tran.commit();
			session.close();
			return DeleteColllectionData.DeleteColllectionData(jsondate);			
		}else {	
			try {			
				Collection collection=null;
				collection=new Collection();
				//gorder.setOid(5);
				System.out.println("存在数据："+jsondate.getString("userId"));
				collection.setUserId(jsondate.getString("userId"));
				collection.setGymnasiumId(jsondate.getString("gymnasiumId"));			
				session.save(collection);
				tran.commit();
				session.close();					
			}catch(Exception e){
				System.out.println(e);
				System.out.println("在这1添加Collection失败");
				return false;
				
			}
			System.out.println("在这1添加Collection成功");
			return true;
		}
		
	}
	public  static Boolean AddUserinfo(String Account) {
		Session session=HibernateSessionFactory.getSession();		
		System.out.println("在这DataBase中修改userinfo");		
		session.clear();
		Transaction tran=session.beginTransaction();
		Userinfo userinfo=new Userinfo();

		if((userinfo=(Userinfo)session.get(Userinfo.class, Account))!=null) {
			System.out.println("账号以存在");
			session.close();
			userinfo=null;
			return false;
		}else {

			try {			
//				Collection collection=null;
//				collection=new Collection();
				//gorder.setOid(5);
				System.out.println("不存在数据!");
				userinfo.setAccount(Account);
				userinfo.setLevels(0);		
				session.save(userinfo);
				tran.commit();
				session.close();					
			}catch(Exception e){
				System.out.println(e);
				System.out.println("在这1添加userinfo失败");
				return false;			
			}
			System.out.println("在这1添加userinfo成功");
			return true;
		}
	}
	
	public int AddJoinGame(JSONObject jsondate) {
		Session session=HibernateSessionFactory.getSession();		
		System.out.println("在这1修改Joingame");		
		session.clear();
		Transaction tran=session.beginTransaction();
		
		Query query=null;
		query=session.createQuery("from Joingame where userName='"+
		jsondate.getString("userName")+"' and userPhone='"+jsondate.getString("userPhone")+"'");
		System.out.println(query);
		System.out.println(query.list().size());
		if(query.list().size()!=0){
			System.out.println("存在");
			tran.commit();
			session.close();
			return 0;			
		}else {	
			try {			
				Joingame joingame=null;
				joingame=new Joingame();
				//gorder.setOid(5);
				
				joingame.setUserName(jsondate.getString("userName"));
				joingame.setUserPhone(jsondate.getString("userPhone"));
				joingame.setGamePlace(jsondate.getString("gamePlace"));				
				joingame.setGameName(jsondate.getString("gameName"));
				joingame.setGameType(jsondate.getString("gameType"));
					
				session.save(joingame);
				tran.commit();
				session.close();					
			}catch(Exception e){
				System.out.println(e);
				System.out.println("在这1添加joingame失败");
				return 2;				
			}
			System.out.println("在这1添加joingame成功");
			return 1;
		}
		
	}
}
