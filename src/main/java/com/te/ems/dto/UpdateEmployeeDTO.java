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
public class UpdateEmployeeDTO {
	private String employeeId;
	private String password;
	private String newPassword;
	private String retypePassword;
	
}
