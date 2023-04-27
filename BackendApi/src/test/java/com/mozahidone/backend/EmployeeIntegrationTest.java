package com.mozahidone.backend;

import com.mozahidone.backend.models.entities.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Smith");

        ResponseEntity<Employee> response = restTemplate.postForEntity("/employees", employee, Employee.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getEmployeeId());
    }

    @Test
    public void testGetEmployee() {
        ResponseEntity<Employee> response = restTemplate.getForEntity("/employees/{id}", Employee.class, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
    }

    /*@Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setName("Jane Doe");
        employee.setDepartment("HR");
        employee.setSalary(60000);

        restTemplate.put("/employees/{id}", employee, 1);

        ResponseEntity<Employee> response = restTemplate.getForEntity("/employees/{id}", Employee.class, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane Doe", response.getBody().getName());
    }

    @Test
    public void testDeleteEmployee() {
        restTemplate.delete("/employees/{id}", 1);

        ResponseEntity<Employee> response = restTemplate.getForEntity("/employees/{id}", Employee.class, 1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }*/
}
