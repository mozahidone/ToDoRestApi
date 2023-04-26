package com.mozahidone.backend;

import org.junit.jupiter.api.Assertions;
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
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(mockEmployees, response.getBody());
	}
}
