package DateBase;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.ov.HibernateSessionFactory;
import org.table.Gymnasium;
import org.table.Login;

public class GetDataMian {
	public String getMainBottom() {
		
		
		Session session=HibernateSessionFactory.getSession();
		try {
			
			Query query=session.createQuery("from Gymnasium");
			List list=query.list();
			
			if(list.size()!=0) {
				for(int i=0;i<list.size();i++) {
					Gymnasium gymnasium=new Gymnasium();
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
