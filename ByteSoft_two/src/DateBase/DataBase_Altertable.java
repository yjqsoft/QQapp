package DateBase;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.table.*;



public class DataBase_Altertable {
	//ɾ�����ݱ��е�����(����  ���ݱ�ţ����ţ��޸ĵ�ʱ���  �����ͣ�A,B,C..��)
	public static boolean AlterABC(String stadium_no, String place, String time, String table_type) {
		//201720180321 �� ���ţ�1�� ʱ��Σ�7..������A,B,C..
		
		switch(table_type) {
		case "A":
			//A a=new A();
			try {
//				"update TUser u set u.userName=?,u.age=? where u.id=?"; +table_type + 
//				"update News set title='�����ϻ�' where content=32323"
//		        String hql = "update A a set a.a:time='0' where a.avno=:stadium_no and a.acno=:place";
		        //System.out.println("hql:"+hql);
//				Session session=HibernateSessionFactory.getSession();		
//				System.out.println("�����޸�");			
//				session.clear();
//				Transaction tran=session.beginTransaction();
//				String hql="select * from A where avno='"+stadium_no+"' and acno='"+place+"'";
//				System.out.println("hql:"+hql);
//		        query= session.createQuery(hql);
//		        List list=query.list();
//		        tran.commit();
//		        query.executeUpdate();
				
				
				
				
				Session session=HibernateSessionFactory.getSession();
				Transaction tr = session.beginTransaction();
			//"update a set stuname = ?,stusex = ? where stuid = ? "
				String hql = "update a set a"+time+"='0' where avno= ? and acno= ? ";
				System.out.println("hql:"+hql);
				int i = session.createSQLQuery(hql)
					.setParameter(0, stadium_no)
					.setParameter(1, place)
					.executeUpdate();				
				System.out.println(i);
				
				//����ع�������
				try {
					tr.commit();
				} catch (Exception e) {
					tr.rollback();
					e.printStackTrace();
				}finally{
					session.close();
				}

				
				
//				session.beginTransaction();
//				A t = (A) session.get(A.class, 2);
//				t.setA7(0);
//				session.update(t);
//				
//				session.getTransaction().commit();
				
				
				
//		        Session session=HibernateSessionFactory.getSession();
//				Transaction tran=session.beginTransaction();
//				A=(A)session.get(A.class, name);
//		        userinfo.setNavheader(context);
//		        session.update(userinfo);
//				tran.commit();
//				session.clear();
//				session.close();
//				userinfo=null;
							
			}catch(Exception e){
				System.out.println(e);
				return false;
			}
			break;
		default:
			return false;	
		}
				
		
	return true;
	}

}
