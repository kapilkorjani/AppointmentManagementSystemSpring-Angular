package com.appointmentmgmtsyst.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import com.appointmentmgmtsyst.db.SessionSingleton;
import com.appointmentmgmtsyst.model.Appointment;
import com.appointmentmgmtsyst.model.Doctor;
import com.appointmentmgmtsyst.model.Patient;

@SuppressWarnings("unchecked")
public class AppointmentDaoImpl implements AppointmentDao{

	//SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	Session session = SessionSingleton.getSession().openSession();
	
	@Override
	public int addAppointment(Appointment appointment) {
		session.beginTransaction();
		int id = (int) session.save(appointment);
		session.getTransaction().commit();
		session.close();
//		SessionSingleton.getSession().beginTransaction();
//		int id = (int) SessionSingleton.getSession().save(appointment);
//		SessionSingleton.getSession().getTransaction().commit();
		return id;
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		session.beginTransaction();
		session.update(appointment);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		session.beginTransaction();
		session.delete(appointment);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Optional<Appointment> getAppointmentById(int id) {
		return Optional.ofNullable(session.get(Appointment.class, id));
	}
	
	@Override
	public List<Appointment> getAllAppointments() {
		String hql = "from Appointment";
		return session.createQuery(hql).list();
	}

	@Override
	public List<Appointment> getAppointmentsByDate(LocalDate date) {
		String hql = "from Appointment where date =:date";
		return session.createQuery(hql).setParameter("date", date).list();
	}

	@Override
	public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
		String hql = "from Appointment where doctor =:doctor";
		return session.createQuery(hql).setParameter("date", doctor).list();
	}

	@Override
	public List<Appointment> getAppointmentsByPatient(Patient patient) {
		String hql = "from Appointment where patient =:patient";
		return session.createQuery(hql).setParameter("patient", patient).list();
	}

}
