package com.te.ems.service;

import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeLoginDTO;
import com.te.ems.dto.EmployeeRegistrationDTO;
import com.te.ems.dto.UpdateEmployeeDTO;

public interface EmployeeService {

	EmployeeDTO getEmployee(String employeeId);

	String insertEmployee(EmployeeRegistrationDTO registrationDTO);

	boolean updateEmployeePassword(UpdateEmployeeDTO updateEmployeeDTO);

	boolean deleteEmployee(String employeeId);

	boolean loginEmployee(EmployeeLoginDTO employeeLoginDTO);


} 
