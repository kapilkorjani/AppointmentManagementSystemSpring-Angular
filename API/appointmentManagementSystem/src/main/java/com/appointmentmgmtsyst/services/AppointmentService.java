package com.appointmentmgmtsyst.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.model.Appointment;
import com.appointmentmgmtsyst.model.Doctor;
import com.appointmentmgmtsyst.model.Patient;

public interface AppointmentService {

	public int addAppointment(Appointment appointment);
	
	public boolean updateAppointment(Appointment appointment);
	
	public boolean deleteAppointment(Appointment appointment);
	
	public Optional<Appointment> getAppointmentById(int id);
	
	public List<Appointment> getAllAppointments();
	
	public List<Appointment> getAppointmentsByDate(LocalDate date);
	
	public List<Appointment> getAppointmentsByDoctor(Doctor doctor);
	
	public List<Appointment> getAppointmentsByPatient(Patient patient);
	
}
