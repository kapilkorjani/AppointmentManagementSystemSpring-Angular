package com.appointmentmgmtsyst.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
@Entity
public class Patient {

	@Id
	@GeneratedValue
	private int patientId;
	private String patientName;
	private String patientEmail;
	private String patientMobile;
}
