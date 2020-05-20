package com.appointmentmgmtsyst.services;

import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.dao.DoctorDaoImpl;
import com.appointmentmgmtsyst.model.Doctor;

public class DoctorServiceImpl implements DoctorService{

	DoctorDaoImpl doctorDaoImpl = new DoctorDaoImpl();
	
	@Override
	public int addDoctor(Doctor doctor) {
		return doctorDaoImpl.addDoctor(doctor);
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {
		try {
		doctorDaoImpl.updateDoctor(doctor);
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDoctor(Doctor doctor) {
		try {
		doctorDaoImpl.deleteDoctor(doctor);
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDaoImpl.getAllDoctors();
	}

	@Override
	public Optional<Doctor> getDoctorByName(String name) {
		return doctorDaoImpl.getDoctorByName(name);
	}

	@Override
	public Optional<Doctor> getDoctorById(int id) {
		return doctorDaoImpl.getDoctorById(id);
	}

	@Override
	public Optional<Doctor> getDoctorByEmail(String email) {
		return doctorDaoImpl.getDoctorByEmail(email);
	}

}
