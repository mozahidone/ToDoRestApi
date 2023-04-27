package com.mozahidone.backend;

import static org.junit.jupiter.api.Assertions.*;
import com.mozahidone.backend.controller.EmployeeRestController;
import com.mozahidone.backend.models.entities.Employee;
import com.mozahidone.backend.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootBackendApplicationTests {

	@Autowired
	private EmployeeRestController controller;

	@MockBean
	private EmployeeService employeeService;

	@Test
	void contextLoads() {
	}

	@Test
	void testFindAll() {
		// Arrange
		List<Employee> mockEmployees = Arrays.asList(
				new Employee("John",  "Doe", "john@example.com"),
				new Employee("Jane", "Doe", "jane@example.com")
		);
		Mockito.when(employeeService.findAll()).thenReturn(mockEmployees);

		// Act
		ResponseEntity<List<Employee>> response = controller.findAll();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockEmployees, response.getBody());
	}

	@Test
	void testFindById() {
		// Arrange
		Employee employee = new Employee("John",  "Doe", "john@example.com");
		Mockito.when(employeeService.findById(1)).thenReturn(employee);

		// Act
		ResponseEntity<Employee> response = controller.findById(1);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employee, response.getBody());
	}

	@Test
	void testSave() {
		// Arrange
		Employee employeeToSave = new Employee("John", "Doe", "john@x.com");
		Employee savedEmployee = new Employee("John", "Doe", "john@x.com");
		Mockito.when(employeeService.save(employeeToSave)).thenReturn(savedEmployee);

		// Act
		ResponseEntity<Employee> response = controller.save(employeeService.save(employeeToSave));

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(savedEmployee, response.getBody());
	}

	@Test
	void testUpdate() {
		// Arrange
		Employee employeeToUpdate = new Employee("John", "Doe", "j@x.com");
		Employee updatedEmployee = new Employee("John", "Doe", "j@x.com");

		Mockito.when(employeeService.update(employeeToUpdate)).thenReturn(updatedEmployee);

		// Act
		ResponseEntity<Employee> response = controller.update(employeeToUpdate);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedEmployee, response.getBody());
	}

	@Test
	void testDeleteByid() {
		// Arrange
		int employeeIdToDlete = 1;

		// Act
		ResponseEntity<?> response = controller.deleteById(employeeIdToDlete);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(employeeService, Mockito.times(1)).deleteById(employeeIdToDlete);
	}
}
