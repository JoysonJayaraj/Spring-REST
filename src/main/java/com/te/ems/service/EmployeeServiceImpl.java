package com.te.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeLoginDTO;
import com.te.ems.dto.EmployeeRegistrationDTO;
import com.te.ems.dto.UpdateEmployeeDTO;
import com.te.ems.entity.Employee;
import com.te.ems.entity.User;
import com.te.ems.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public EmployeeDTO getEmployee(String employeeId) {
		Optional<Employee> employeeOP = employeeRepository.getEmployee(employeeId);
		
		if(employeeOP.isPresent())
		{
			Employee employee = employeeOP.get();
			return EmployeeDTO.builder()
					.employeeId(employeeId)
					.employeeName(employee.getEmployeeName())
					.employeeDOJ(employee.getEmployeeDOJ())
					.username(employee.getUserCredential().getUsername())
					.build();
		}
		return null;
	}
	
	@Override
	public String insertEmployee(EmployeeRegistrationDTO registrationDTO) {
		Employee employee = Employee.builder().employeeId(registrationDTO.getEmployeeId()).employeeName(registrationDTO.getEmployeeName())
				.employeeDOJ(registrationDTO.getEmployeeDOJ()).build();
		
		User user = User.builder().username(registrationDTO.getUsername()).password(registrationDTO.getPassword()).build();
		
		employee.setUserCredential(user);
		user.setEmployee(employee);
		
		String employeeId = employeeRepository.insertEmployee(employee);
		return employeeId;
	}

	@Override
	public boolean loginEmployee(EmployeeLoginDTO employeeLoginDTO) {
		return employeeRepository.login(employeeLoginDTO.getUsername(),employeeLoginDTO.getPassword());
	}
	
	@Override
	public boolean updateEmployeePassword(UpdateEmployeeDTO updateEmployeeDTO) {
		if(!updateEmployeeDTO.getNewPassword().equals(updateEmployeeDTO.getRetypePassword())) {
			System.out.println("Password retype mismatch ");
				return false;
		}
		return employeeRepository.updateEmployeePassword(updateEmployeeDTO.getEmployeeId(),
				updateEmployeeDTO.getPassword(),updateEmployeeDTO.getNewPassword());
	}
	
	@Override
	public boolean deleteEmployee(String employeeId) {
		return employeeRepository.deleteEmployee(employeeId);
	}
}
