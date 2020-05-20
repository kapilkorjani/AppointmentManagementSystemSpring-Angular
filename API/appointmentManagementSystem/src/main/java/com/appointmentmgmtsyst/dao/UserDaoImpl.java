package com.appointmentmgmtsyst.dao;

import java.util.List;

import org.hibernate.Session;

import com.appointmentmgmtsyst.db.SessionSingleton;
import com.appointmentmgmtsyst.model.User;

@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao{

	Session session = SessionSingleton.getSession().openSession();
	
	@Override
	public User getUserByName(String name) {
		String hql = "from User where name =:name";
		List<User> users = session.createQuery(hql).setParameter("name", name).list();
		User user = users.size() != 0 ? users.get(0):null;
		return user;
	}

	@Override
	public User getUserById(int id) {
		return session.get(User.class, id);
	}

	@Override
	public User getUserByEmail(String email) {
		String hql = "from User where email =:email";
		List<User> users = session.createQuery(hql).setParameter("email", email).list();
		User user = users.size() != 0 ? users.get(0):null;
		return user;
	}

	@Override
	public int addUser(User user) {
		session.beginTransaction();
		int id = (int) session.save(user);
		session.getTransaction().commit();
		session.close();
		return id;
	}

	@Override
	public boolean updateUser(User user) {
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
		return true;
	}

}
