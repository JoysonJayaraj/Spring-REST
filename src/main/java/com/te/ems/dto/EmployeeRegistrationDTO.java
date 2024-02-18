package com.te.ems.dto;

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
public class EmployeeRegistrationDTO {

	private String employeeId;

	private String employeeName;

	private String employeeDOJ;

	private String username;
	
	private String password;

}
