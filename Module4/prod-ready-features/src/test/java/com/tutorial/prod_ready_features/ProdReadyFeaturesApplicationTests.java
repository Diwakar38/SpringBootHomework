package com.tutorial.prod_ready_features;

import com.tutorial.prod_ready_features.clients.EmployeeClient;
import com.tutorial.prod_ready_features.dtos.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	public void getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeClient.getAllEmployees();
		System.out.println(employeeDtoList);
	}

	@Test
	public void getEmployeeById() {
		EmployeeDto employeeDto1 = employeeClient.getEmployeesById(1L);
		System.out.println(employeeDto1);
		EmployeeDto employeeDto2 = employeeClient.getEmployeesById(2L);
		System.out.println(employeeDto2);
	}

	@Test
	public void createNewEmployee() {
//		EmployeeDto employeeDto = new EmployeeDto(null, "Diwakar", "diwakar@email.com", 26, "USER", 100000.0, LocalDate.of(2024,1,1), true, new BigInteger("100000000019"), "@Password1234");
		EmployeeDto employeeDto = new EmployeeDto(null, "Diwakar", "diwakar@email.com", 2, "USER", 100000.0, LocalDate.of(2024,1,1), true, new BigInteger("100000000019"), "@Password1234");
		EmployeeDto savedEmployee = employeeClient.createNewEmployee(employeeDto);
		System.out.println(savedEmployee);
	}

}
