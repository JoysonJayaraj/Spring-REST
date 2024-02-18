package com.te.ems.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Employee {
	@Id
	private String employeeId;
	
	private String employeeName;
	
	private String employeeDOJ;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User userCredential;

}
