package com.appointmentmgmtsyst.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionSingleton {

	
	private static SessionFactory sess;
	private SessionSingleton() {}
	public static SessionFactory getSession()
	{
		if(sess == null)
		{
			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	        return sessionFactory;
		}
		else
		{
			return sess;
		}
	}
	public static void closeSession() {
		sess.close();
	}
}
