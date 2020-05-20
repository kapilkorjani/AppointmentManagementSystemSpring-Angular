package com.appointmentmgmtsyst.services;

import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.model.Patient;

public interface PatientService {

	public int addPatient(Patient patient);
	
	public boolean updatePatient(Patient patient);
	
	public boolean deletePatient(Patient patient);
	
	public Optional<Patient> getPatientByName(String name);
	
	public Optional<Patient> getPatientById(int id);
	
	public Optional<Patient> getPatientByEmail(String email);
	
	public List<Patient> getAllPatients();
	
}
