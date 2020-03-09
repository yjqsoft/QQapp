package DateBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.table.Login;
import org.table.Userinfo;

public class DateBaseInfomation {
	public DateBaseInfomation() {}
	/**
	 * 
	 * @param name 传入的账号信息
	 * @return 
	 * @return 返回一个类对象，存有信息的类对象信息
	 */
	public static Userinfo getMoreInfomation(String name) {
		Userinfo userinfo=null;
		Session session=HibernateSessionFactory.getSession();
		userinfo=(Userinfo)session.get(Userinfo.class, name);
		return userinfo;
	}
	public static boolean ModifyInfomation(String name,String mode,String context) {
		Userinfo userinfo=null;
		try {
			 if(mode.equals("1")) {
		        System.out.println("修改头像");
		        Session session=HibernateSessionFactory.getSession();
				Transaction tran=session.beginTransaction();
				userinfo=(Userinfo)session.get(Userinfo.class, name);
		        userinfo.setNavheader(context);
		        session.update(userinfo);
				tran.commit();
				session.clear();
				session.close();
				userinfo=null;
				return true;
		       }
		       else if(mode.equals("2")) {
		        	System.out.println("修改真实姓名");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	userinfo.setReallyName(context);
		        	 session.update(userinfo);
						tran.commit();
						session.clear();
						session.close();
						userinfo=null;
						return true;
		        }
		        else if(mode.equals("3")) {
		        	System.out.println("修改昵称");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	userinfo.setNickname(context);
		        	 session.update(userinfo);
						tran.commit();
						session.clear();
						session.close();
						userinfo=null;
						return true;
		        }
		        else if(mode.equals("4")) {
		        	System.out.println("修改性别");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	userinfo.setSex(context);
		        	 session.update(userinfo);
						tran.commit();
						session.clear();
						session.close();
						userinfo=null;
						return true;
		        	
		        }else if(mode.equals("5")) {
		        	System.out.println("修改等级");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	int a=Integer.parseInt(context);
		        	userinfo.setLevels(a);
		        	 session.update(userinfo);
						tran.commit();
						session.clear();
						session.close();
						userinfo=null;
						return true;
		        	
		        }else if(mode.equals("6")) {
		        	System.out.println("修改手机号码");
		        	
		        }else if(mode.equals("7")) {
		        	System.out.println("修改出生日期");
		        	Date date=null;
		        	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	try{
		        		date = format1.parse(context);
		        		userinfo.setBirth(date);
		        	}catch(Exception e) {
		        		e.printStackTrace();
		        		userinfo=null;
		        		return false;
		        	}
		        	session.update(userinfo);
					tran.commit();
					session.clear();
					session.close();
					userinfo=null;
					return true;
		        }else if(mode.equals("8")) {
		        	System.out.println("修改银行卡");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	userinfo.setNetBanking(context);
		        	 session.update(userinfo);
						tran.commit();
						session.clear();
						session.close();
						userinfo=null;
						return true;
		        	
		        }else if(mode.equals("9")) {
		        	System.out.println("修改地区");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	userinfo.setRegion(context);
		        	 session.update(userinfo);
						tran.commit();
						session.clear();
						session.close();
						userinfo=null;
						return true;
		        	
		        }else if(mode.equals("10")) {
		        	System.out.println("修改签名");
		        	Session session=HibernateSessionFactory.getSession();
					Transaction tran=session.beginTransaction();
					userinfo=(Userinfo)session.get(Userinfo.class, name);
		        	userinfo.setSignature(context);	
		        	 session.update(userinfo);
						tran.commit();
						session.clear();
						session.close();
						userinfo=null;
						return true;
		        }
		}catch(Exception e) {
			return false;
		}
		finally {
			userinfo=null;
		}
		return false;
	}
}
