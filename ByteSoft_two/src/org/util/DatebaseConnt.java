package org.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DatebaseConnt {
	private SessionFactory sessionFactory;
	public DatebaseConnt() {}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		Session session=sessionFactory.openSession();
		return session;
	}
}
