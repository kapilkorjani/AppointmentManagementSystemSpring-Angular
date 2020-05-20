package com.appointmentmgmtsyst.dao;

import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.model.Patient;

public interface PatientDao {

	
	public int addPatient(Patient patient);
	
	public void updatePatient(Patient patient);
	
	public void deletePatient(Patient patient);
	
	public Optional<Patient> getPatientByName(String name);
	
	public Optional<Patient> getPatientById(int id);
	
	public Optional<Patient> getPatientByEmail(String email);
	
	public List<Patient> getAllPatients();
	
	
}
