package com.appointmentmgmtsyst.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.appointmentmgmtsyst.db.SessionSingleton;
import com.appointmentmgmtsyst.model.Doctor;

@SuppressWarnings("unchecked")
public class DoctorDaoImpl implements DoctorDao {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	Session session = sessionFactory.openSession();
//	Session session = SessionSingleton.getSession().openSession();
	@Override
	public int addDoctor(Doctor doctor) {
		session.beginTransaction();
		int id = (int) session.save(doctor);
		session.getTransaction().commit();
		session.close();
		/*
		 * SessionSingleton.getSession().beginTransaction(); int id = (int)
		 * SessionSingleton.getSession().save(doctor);
		 * SessionSingleton.getSession().getTransaction().commit();
		 */
		return id;
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		session.beginTransaction();
		session.saveOrUpdate(doctor);
		session.getTransaction().commit();
		session.close();
	
//		SessionSingleton.getSession().beginTransaction();
//		SessionSingleton.getSession().saveOrUpdate(doctor);
//		SessionSingleton.getSession().getTransaction().commit();
	}

	@Override
	public void deleteDoctor(Doctor doctor) {
		session.beginTransaction();
		session.delete(doctor);
		session.getTransaction().commit();
		session.close();

//		SessionSingleton.getSession().beginTransaction();
//		SessionSingleton.getSession().delete(doctor);
//		SessionSingleton.getSession().getTransaction().commit();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		String hql = "from Doctor";

		return session.createQuery(hql).list();
//		return SessionSingleton.getSession().createQuery(hql).list();
	}

	@Override
	public Optional<Doctor> getDoctorByName(String name) {
		String hql = "from Doctor where doctorName =:name";
		return session.createQuery(hql).setParameter("name", name).stream().findFirst();
//		return SessionSingleton.getSession().createQuery(hql).setParameter("name", name).stream().findFirst();
	}

	@Override
	public Optional<Doctor> getDoctorById(int id) {
//		return Optional.ofNullable(SessionSingleton.getSession().get(Doctor.class, id));
		return Optional.ofNullable(session.get(Doctor.class, id));
	}

	@Override
	public Optional<Doctor> getDoctorByEmail(String email) {
		String hql = "from Doctor where doctorEmail =:email";
		return session.createQuery(hql).setParameter("email", email).stream().findFirst();
	}

}
