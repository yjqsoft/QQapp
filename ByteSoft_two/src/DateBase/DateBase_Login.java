package DateBase;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.table.Login;
import org.table.Userinfo;

public class DateBase_Login {
	public DateBase_Login() {}
	/**
	 * 登入验证
	 * @param name 账号
	 * @param Password 密码
	 * @return -1账号不存在，0账号密码错误，1登入成功
	 * 
	 */
	public int isLogin(String name,String password) {
		Login table=new Login();
		Session session=HibernateSessionFactory.getSession();
		if((table=(Login)session.get(Login.class, name))==null) {
			System.out.println("查询结果：该账号不存在");
			session.close();
			table=null;
			return -1;
		}
		else {
			if(table.getPassword().equals(password)) {
				System.out.println("查询登入成功");
				session.close();
				return 1;
			}
			else {
				System.out.println("账号密码错误");
				session.close();
				table=null;
				return 0;
			}
		}
	}
	/**
	 * 注册验证
	 * @param name 账号
	 * @param Password 密码
	 * @return token长连接，注册失败，账号存在放回null，不然放回token
	 */
	public boolean isRegister(String name,String Password) {
		Login table=new Login();
		Session session=HibernateSessionFactory.getSession();
		session.clear();
		if((table=(Login)session.get(Login.class, name))!=null) {
			System.out.println("账号以存在");
			session.close();
			table=null;
			return false;
		}
		else {
			//String token=UUID.randomUUID().toString();
			int picture=(int) (1+Math.random()*6);//"http://172.22.70.227:8080/Images/";//man.png"
			Userinfo info=new Userinfo();
			Login login=new Login();
			login.setAccount(name);    //账号
			login.setPassword(Password);   //密码
			login.setValidation(null);   //长连接
			info.setAccount(name);  //账号
			info.setNickname("一个小虎牙");  //昵称
			info.setLevels(1);  //等级
			info.setNavheader("http://172.22.70.227:8080/Images/"+picture);  //头像
			Transaction tran=session.beginTransaction();
			session.save(login);
			tran.commit();
			session.save(info);
			tran.commit();
			session.close();
			return true;
		}
	}
	/**
	 * 因为token经常换，所以定义单独函数
	 * @return 放回更换token的结果
	 */
	public String setToken(String name) {
		Login login=new Login();
		String token=UUID.randomUUID().toString();
		Session session=HibernateSessionFactory.getSession();
		Transaction tran=session.beginTransaction();
		login=(Login)session.get(Login.class, name);
		login.setValidation(token);
		session.update(login);
		tran.commit();
		session.clear();
		session.close();;
		login=null;
		return token;
	}
	/*
	 * 判断长连接是否失效
	 */
	public boolean isToken(String name,String token) {
		Login login=new Login();
		//SQLQuery sqlQuery = economicContractDao.getSession().createSQLQuery(sql.toString()).addEntity(EconomicContractEntity.class);
//		sqlQuery.setCacheable(false);
		try {
			Session session=HibernateSessionFactory.getSession();
			session.clear();
			Transaction tran=session.beginTransaction();
			Query query=session.createQuery("from Login where account='"+name+"'");
			query.setCacheable(false);
			List list=query.list();
			tran.commit();
			session.close();
			login=(Login) list.get(0);
			System.out.println("获取的账号;"+login.getAccount()+"获取的长连接;"+login.getValidation());
			System.out.println("app的账号"+name+"app的长连接："+token);
		}
		catch(Exception e){
			System.out.println(e);
		}
		if(login.getValidation().equals(token)) {
			System.out.println("长连接匹配成功,表示在登入状态，更新长连接");
			login=null;
			return true;
		}
		else {
			System.out.println("长连接失败，表示已经失效了");
			login=null;
			return false;
		}
//		System.out.println("账号："+login.getAccount()+"长连接"+login.getValidation());
//		System.out.println(login.getValidation().equals(token));
//		Iterator iterator=list.iterator();
//		if(iterator.hasNext()) {
//			Login login=(Login) iterator.next();
//			System.out.println("长连接"+name+"账号："+token);
//			System.out.println("账号："+login.getAccount()+"长连接"+login.getValidation());
//			System.out.println(login.getValidation().equals(token));
//			return true;
//		}
//		else {
//			return false;
//		}
//		Login login=(Login)session.get(Login.class, name);
//		session.close();
//		System.out.println("长连接"+name+"账号："+token);
//		System.out.println("账号："+login.getAccount()+"长连接"+login.getValidation());
//		System.out.println(login.getValidation().equals(token));
//		if(login.getValidation().equals(token)) {
//			/**
//			 * 没有失效
//			 */
//			System.out.println("匹配成功");
//			return true;
//		}
//		else
//			return false;
	}
}
