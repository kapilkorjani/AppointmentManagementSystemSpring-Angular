package com.appointmentmgmtsyst.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.model.Appointment;
import com.appointmentmgmtsyst.model.Doctor;
import com.appointmentmgmtsyst.model.Patient;

public interface AppointmentDao {

	public int addAppointment(Appointment appointment);
	
	public void updateAppointment(Appointment appointment);
	
	public void deleteAppointment(Appointment appointment);
	
	public Optional<Appointment> getAppointmentById(int id);
	
	public List<Appointment> getAppointmentsByDate(LocalDate date);
	
	public List<Appointment> getAppointmentsByDoctor(Doctor doctor);
	
	public List<Appointment> getAppointmentsByPatient(Patient patient);
	
	public List<Appointment> getAllAppointments();
}
