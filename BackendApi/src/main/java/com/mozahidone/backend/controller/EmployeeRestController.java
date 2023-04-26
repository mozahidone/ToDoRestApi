package com.mozahidone.backend.controller;

import com.mozahidone.backend.models.entities.Employee;
import com.mozahidone.backend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = {"/api/v1/employees"})
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(final EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

    @GetMapping(value = {"", "/", "/all"})
	public ResponseEntity<List<Employee>> findAll() {
		return new ResponseEntity<>(this.employeeService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = {"/{employeeId}", "/get/{employeeId}"})
	public ResponseEntity<Employee> findById(@PathVariable("employeeId") final Integer employeeId) {
		return new ResponseEntity<>(this.employeeService.findById(employeeId), HttpStatus.OK);
	}

	@PostMapping(value = {"", "/", "/save"})
	public ResponseEntity<Employee> save(@RequestBody final Employee employee) {
		return new ResponseEntity<>(this.employeeService.save(employee), HttpStatus.OK);
	}

	@PutMapping(value = {"/{employeeId}", "/update/{employeeId}"})
	public ResponseEntity<Employee> update(@RequestBody final Employee employee) {
		return new ResponseEntity<>(this.employeeService.update(employee), HttpStatus.OK);
	}

	@DeleteMapping(value = {"/{employeeId}", "/delete/{employeeId}"})
	public ResponseEntity<?> deleteById(@PathVariable("employeeId") final Integer employeeId) {
		this.employeeService.deleteById(employeeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}



}










