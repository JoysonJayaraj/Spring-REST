package com.te.ems.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.te.ems.entity.Employee;
import com.te.ems.entity.User;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MySQL01");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public Optional<Employee> getEmployee(String employeeId) {
		return Optional.<Employee>ofNullable(entityManager.find(Employee.class, employeeId));
	}

	@Override
	public String insertEmployee(Employee employee) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(employee);
		transaction.commit();
		return employee.getEmployeeId();
	}

	@Override
	public boolean login(String username, String password) {
		System.out.println("-> " + username);
		System.out.println("-> " + password);
		User user = entityManager.find(User.class, username);
		System.out.println("-> " + user);
		if (!user.getUsername().equals(username)
				&& user.getPassword().equals(password)) {
			System.out.println("Not Matching");
			return false;
		}
		return true;
	}

	@Override
	public boolean updateEmployeePassword(String employeeId, String password, String newPassword) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Employee employee = entityManager.find(Employee.class, employeeId);
		if (!employee.getUserCredential().getPassword().equals(password)) {
			System.out.println("Password mismatch");
			return false;
		}
		employee.getUserCredential().setPassword(newPassword);
		entityManager.persist(employee);
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteEmployee(String employeeId) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Employee employee = entityManager.find(Employee.class, employeeId);
		if (!(employee != null)) {
			System.out.println(employeeId + " not exist");
			return false;
		}
		entityManager.remove(employee);
		transaction.commit();
		return true;
	}

}
