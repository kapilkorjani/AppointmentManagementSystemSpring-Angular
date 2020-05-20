package com.appointmentmgmtsyst.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Entity
@Data
public class Doctor {

	@Id
	@GeneratedValue
	private int doctorId;
	private String doctorName;
	private String doctorMobile;
	private String specialization;
	private String doctorEmail;

}
