package com.appointmentmgmtsyst.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.appointmentmgmtsyst.db.SessionSingleton;
import com.appointmentmgmtsyst.model.Patient;


@SuppressWarnings("unchecked")
public class PatientDaoImpl implements PatientDao{

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();	
	Session session = sessionFactory.openSession();
//	Session session = SessionSingleton.getSession().openSession();
	@Override
	public int addPatient(Patient patient) {
		session.beginTransaction();
		int id = (int) session.save(patient);
		session.getTransaction().commit();
		session.close();
		
		/*
		 * SessionSingleton.getSession().beginTransaction(); int id = (int)
		 * SessionSingleton.getSession().save(patient);
		 * SessionSingleton.getSession().getTransaction().commit();
		 */
		return id;
	}

	@Override
	public void updatePatient(Patient patient) {
		session.beginTransaction();
		session.saveOrUpdate(patient);
		session.getTransaction().commit();
		session.clear();
	}

	@Override
	public void deletePatient(Patient patient) {
		session.beginTransaction();
		session.delete(patient);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Optional<Patient> getPatientByName(String name) {
		String hql = "from Patient where patientName =:name";
		return session.createQuery(hql).setParameter("name", name).stream().findFirst();
	}

	@Override
	public Optional<Patient> getPatientById(int id) {
		return Optional.ofNullable(session.get(Patient.class, id));
	}
	
	@Override
	public List<Patient> getAllPatients() {
		String hql = "from Patient";
		List<Patient> patients = session.createQuery(hql).list();
		return patients;
	}

	@Override
	public Optional<Patient> getPatientByEmail(String email) {
		String hql = "from Patient where patientEmail =:email";
		return session.createQuery(hql).setParameter("email", email).stream().findFirst();
	}

	
}
