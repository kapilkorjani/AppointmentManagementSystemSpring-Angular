package com.appointmentmgmtsyst.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Entity
@Data
public class User {

	@Id
	@GeneratedValue
	private int userId;
	private String name;
	private String email;
	private String password;
	private String salt;
	private String mobile;
	
}
