package com.mozahidone.backend.services;

import com.mozahidone.backend.models.entities.Employee;

import java.util.List;

public interface EmployeeService {
	
	List<Employee> findAll();
	Employee findById(final Integer employeeId);
	Employee save(final Employee employee);
	Employee update(final Employee employee);
	void deleteById(final Integer employeeId);
	
}






