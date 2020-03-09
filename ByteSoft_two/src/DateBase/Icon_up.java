package DateBase;

import org.hibernate.Session;
import org.ov.HibernateSessionFactory;
import org.table.Userinfo;

public class Icon_up {
	public Icon_up() {}
	public static String  getIcon(String name) {
		Userinfo userinfo=null;
		Session session=HibernateSessionFactory.getSession();
		userinfo=(Userinfo)session.get(Userinfo.class, name);
		System.out.println("头像的信息为："+userinfo.getNavheader());
		return null;
	}
	static void setIcon() {
		
	}
}
