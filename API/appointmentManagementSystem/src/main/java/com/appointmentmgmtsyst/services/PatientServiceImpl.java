package com.appointmentmgmtsyst.services;

import java.util.List;
import java.util.Optional;

import com.appointmentmgmtsyst.dao.PatientDaoImpl;
import com.appointmentmgmtsyst.model.Patient;

public class PatientServiceImpl implements PatientService{
	
	PatientDaoImpl patientDao = new PatientDaoImpl();

	@Override
	public int addPatient(Patient patient) {
		return patientDao.addPatient(patient);
	}

	@Override
	public boolean updatePatient(Patient patient) {
		try {
		patientDao.updatePatient(patient);
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletePatient(Patient patient) {
		try {
			patientDao.deletePatient(patient);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Optional<Patient> getPatientByName(String name) {
		return patientDao.getPatientByName(name);
	}

	@Override
	public Optional<Patient> getPatientById(int id) {
		return patientDao.getPatientById(id);
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientDao.getAllPatients();
	}

	@Override
	public Optional<Patient> getPatientByEmail(String email) {
		return patientDao.getPatientByEmail(email);
	}

}
