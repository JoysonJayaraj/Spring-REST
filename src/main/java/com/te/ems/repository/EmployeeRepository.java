package com.te.ems.repository;

import java.util.Optional;

import com.te.ems.entity.Employee;

public interface EmployeeRepository {

	Optional<Employee> getEmployee(String employeeId);

	String insertEmployee(Employee employee);

	boolean updateEmployeePassword(String employeeId, String password, String newPassword);

	boolean deleteEmployee(String employeeId);

	boolean login(String username, String password);

}
