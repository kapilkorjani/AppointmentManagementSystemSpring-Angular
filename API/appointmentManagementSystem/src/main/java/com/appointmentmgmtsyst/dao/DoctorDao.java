package com.appointmentmgmtsyst.dao;

import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.model.Doctor;

public interface DoctorDao {

	public int addDoctor(Doctor doctor);
	
	public void updateDoctor(Doctor doctor);
	
	public void deleteDoctor(Doctor doctor);
	
	public List<Doctor> getAllDoctors();
	
	public Optional<Doctor> getDoctorByName(String name);

	public Optional<Doctor> getDoctorByEmail(String email);
	
	public Optional<Doctor> getDoctorById(int id);
}
