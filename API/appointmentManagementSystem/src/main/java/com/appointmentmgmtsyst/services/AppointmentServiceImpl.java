package com.appointmentmgmtsyst.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.dao.AppointmentDaoImpl;
import com.appointmentmgmtsyst.model.Appointment;
import com.appointmentmgmtsyst.model.Doctor;
import com.appointmentmgmtsyst.model.Patient;

public class AppointmentServiceImpl implements AppointmentService{

	AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
	
	@Override
	public int addAppointment(Appointment appointment) {
		return appointmentDao.addAppointment(appointment);
	}

	@Override
	public boolean updateAppointment(Appointment appointment) {
		try {
		appointmentDao.updateAppointment(appointment);
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAppointment(Appointment appointment) {
		try {
			appointmentDao.deleteAppointment(appointment);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Optional<Appointment> getAppointmentById(int id) {
		return appointmentDao.getAppointmentById(id);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentDao.getAllAppointments();
	}

	@Override
	public List<Appointment> getAppointmentsByDate(LocalDate date) {
		return appointmentDao.getAppointmentsByDate(date);
	}

	@Override
	public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
		return appointmentDao.getAppointmentsByDoctor(doctor);
	}

	@Override
	public List<Appointment> getAppointmentsByPatient(Patient patient) {
		return appointmentDao.getAppointmentsByPatient(patient);
	}

}
