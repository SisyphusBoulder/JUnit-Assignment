package com.qa.junit.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.qa.junit.exception.EmployeeNotFoundException;
import com.qa.junit.exception.InvalidInputException;
import com.qa.junit.model.Employee;

public class RepositoryTest {
	
	/*
	 * To test Repository class
	 */
	
	Repository repository;
	
	@BeforeEach
	public void setUp() {
		this.repository = new Repository();
	}
	
	@AfterEach
	public void cleanUp() {
		this.repository = null;
	}
	
	@Test
	@DisplayName("getEmpById(id) -> Return Employee")
	public void givenExistingId_whenGetEmployeeById_thenReturnEmployee() throws EmployeeNotFoundException, InvalidInputException {
		//testing the code
		//expected vs actual
		Employee employee = this.repository.getEmployeeById(111);
		assertNotNull(employee);
		assertEquals("emp1", employee.getName());
		assertEquals(32423.23,employee.getSalary());
		
	}
	
	@Test
	@DisplayName("getEmployeeById(id) -> Throws EmployeeNotFoundException")
	public void givenNonExistingId_whenGetEmployeeById_thenThrowEmployeeNotFoundException() {
		
		assertThrows(EmployeeNotFoundException.class, () -> this.repository.getEmployeeById(888) );
	}
	
	@Test
	@DisplayName("getEmployeeById(invalid) -> Throws InvalidInputException")
	public void givenInvalidInputAsId_whenGetEmpoyeeById_thenThrowInvalidInputException() {
		assertThrows(InvalidInputException.class, () -> this.repository.getEmployeeById(-100) );
	}
	
	@Test
	@DisplayName("getAllEmployee")
	public void getAllEmployees() {
		List<Employee> empList = this.repository.getAllEmployees();
		assertNotNull(empList);
		Assertions.assertNotEquals(0,empList.size());
	}
	
	
	
	@Test
	@DisplayName("addEmployee")
	public void givenEmployee_addEmployeeToRepository_thenEmployeeNotFoundException () throws EmployeeNotFoundException, InvalidInputException, EmployeeAlreadyExistsException {
		int id = 444;
		assertThrows(EmployeeAlreadyExistsException.class, () -> this.repository.getEmployeeById(id));
		Employee employee = new Employee(id, "emp4", 52563.89);
		boolean result = this.repository.addEmployee(employee);
		assert(result);
		employee = this.repository.getEmployeeById(444);
		assertNotNull(employee);
		assertEquals("emp4", employee.getName());
		assertEquals(52563.89,employee.getSalary());
	}
	
}
