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
public class User {
	@Id
	private String username;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "userCredential")
	private Employee employee;

}
