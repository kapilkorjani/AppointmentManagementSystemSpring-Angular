package com.appointmentmgmtsyst.services;

import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.model.Doctor;

public interface DoctorService {

public int addDoctor(Doctor doctor);
	
	public boolean updateDoctor(Doctor doctor);
	
	public boolean deleteDoctor(Doctor doctor);
	
	public List<Doctor> getAllDoctors();
	
	public Optional<Doctor> getDoctorByName(String name);

	public Optional<Doctor> getDoctorByEmail(String email);
	
	public Optional<Doctor> getDoctorById(int id);
	
}
