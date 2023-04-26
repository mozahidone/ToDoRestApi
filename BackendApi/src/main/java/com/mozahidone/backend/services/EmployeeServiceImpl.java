package com.mozahidone.backend.services;

import com.mozahidone.backend.exceptions.customs.EmployeeNotFoundException;
import com.mozahidone.backend.models.entities.Employee;
import com.mozahidone.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository repo;
	
	@Autowired
	public EmployeeServiceImpl(final EmployeeRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Employee> findAll() {
		return this.repo.findAll();
	}
	
	@Override
	public Employee findById(final Integer employeeId) {
		return this.repo.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("#---------Employee is not found---------#"));
	}
	
	@Override
	public Employee save(final Employee employee) {
		employee.setEmployeeCode(UUID.randomUUID().toString());
		return this.repo.save(employee);
	}
	
	@Override
	public Employee update(final Employee employee) {
		return this.repo.save(employee);
	}
	
	@Override
	public void deleteById(final Integer employeeId) {
		this.repo.deleteById(employeeId);
	}
	
	
	
}









