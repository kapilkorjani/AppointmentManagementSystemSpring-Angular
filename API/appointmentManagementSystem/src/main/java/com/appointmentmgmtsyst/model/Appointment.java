package com.appointmentmgmtsyst.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
@Entity
public class Appointment {

	@Id
	@GeneratedValue
	private int appointmentId;
	@ManyToOne(fetch = FetchType.EAGER)
	private Doctor doctor;
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	private LocalDate date;
	private String time;
	private String issue;
	private String description;
	private int criticality;
	
}
