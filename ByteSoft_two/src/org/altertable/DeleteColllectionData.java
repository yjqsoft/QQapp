package org.altertable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ov.HibernateSessionFactory;
import org.table.Collection;

import net.sf.json.JSONObject;

public class DeleteColllectionData {

	public static Boolean DeleteColllectionData(JSONObject jsondate) {
		
		
		
		Session session=HibernateSessionFactory.getSession();		
		System.out.println("在这1删除Collection");		
		
		
		session.clear();
		Transaction tran=session.beginTransaction();
		
		Query query=null;
		
		
		//String hql = "update a set a"+time+"='0' where avno= ? and acno= ? ";
		
		String hql = "Delete from Collection where userId=? and gymnasiumId=?";
		
		System.out.println(hql);	
		query=session.createQuery(hql);
		int i = session.createSQLQuery(hql)
				.setParameter(0, jsondate.getString("userId"))
				.setParameter(1, jsondate.getString("gymnasiumId"))
				.executeUpdate();				
			System.out.println(i);
		System.out.println(query);		
		
		tran.commit();
		session.close();					
			
		System.out.println("在这1删除Collection成功");
		return true;
		
	}
	
}
