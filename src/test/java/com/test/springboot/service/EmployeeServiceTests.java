package com.test.springboot.service;


import com.test.springboot.exception.ResourceNotFountException;
import com.test.springboot.model.Employee;
import com.test.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTests {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	private Employee employee ;

	@Mock
	private Page<Employee> employeePage;

	@BeforeEach
	public void setup(){
		employee = Employee.builder()
				.firstName("zülal")
				.lastName("kılınç")
				.email("zülal@kılınç")
				.build();

		given(employeeRepository.save(employee)).willReturn(employee);

	}

	@DisplayName("Junit test for save employee operation")
	@Test
	@Order(1)
	public void givenEmployees_whenSaveEmployee_thenReturnEmployee(){

		// given - prediction or setup

		// given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
		// when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.empty());
		given(employeeRepository.save(employee)).willReturn(employee);

		// when - action or behavior that we are going test
		Employee savedEmployee = employeeService.saveEmployee(employee);

		// then - verify the output
		assertThat(savedEmployee).isNotNull();
	}

	@DisplayName("Junit test for save employee operation which throw exception")
	@Test
	@Order(2)
	public void givenEmployees_whenSaveEmployee_thenThrowException(){

		// given - prediction or setup
		given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

		// when - action or behavior that we are going test
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFountException.class,() -> {
			employeeService.saveEmployee(employee);
		});
		// then - verify the output
		verify(employeeRepository,never()).save(any(Employee.class));
	}

	@DisplayName("Junit test for get all employee operation ")
	@Test
	@Order(3)
	public void givenEmployeesList_whenGetAllEmployee_thenEmployeeList(){

		// given - prediction or setup
		Employee employee1 = Employee.builder()
				.firstName("vildan")
				.lastName("vildan")
				.email("vildan@vildan")
				.build();
		List<Employee> employeeList =Arrays.asList(employee,employee1);

		given(employeeRepository.findAll()).willReturn(employeeList);

		// when - action or behavior that we are going test
		List<Employee> employees = employeeService.getAllEmployess();

		// then - verify the output
		assertThat(employees).isNotNull();
		assertThat(employees.size()).isEqualTo(2);
	}

	@DisplayName("Junit test for get all employee operation (negative senario)")
	@Test
	@Order(4)
	public void givenEmptyEmployeesList_whenGetAllEmployee_thenReturnEmptyEmployeeList(){

	}


	@DisplayName("Junit test for get employee by id")
	@Test
	@Order(5)
	public void givenEmployeeId_whenGetEmployeeById_thenEmployee(){

		// given - setup or prediction
		given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));

		//when - action or behavior
		Employee getEmployeById = employeeService.getEmployeeById(employee.getId()).get();

		//then - verify the output
		assertThat(employee.getFirstName()).isEqualTo("zülal");
		assertThat(employee).isNotNull();
	}

	@DisplayName("Junit test for update employee method")
	@Test
	@Order(6)
	public void givenEmployee_whenUpdateEmployee_thenUpdatedEmployee(){

		// given - setup or prediction

		//TODO ??
		//employee.setFirstName("mihriban");
		given(employeeRepository.save(employee)).willReturn(employee);
		employee.setFirstName("mihriban");
		employee.setEmail("mihriban@mihriban");

		//when - action or behavior
		Employee updatedEmployee = employeeService.updateEmployee(employee);

		//then - verify the output
		assertThat(employee.getFirstName()).isEqualTo("mihriban");
	}

	@DisplayName("Junit test for delete method")
	@Test
	@Order(7)
	public void givenEmployeeId_whenDleteEmployee_then(){

		long employeeId =1L;
		// given - setup or prediction
		willDoNothing().given(employeeRepository).deleteById(employeeId);

		//when - action or behavior
		employeeService.deleteEmployee(employeeId);
		//then - verify the output
		verify(employeeRepository,times(1)).deleteById(employeeId);
	}

	@DisplayName("Junit test for get all employee by pageable operation ")
	@Test
	@Order(8)
	public void givenEmployeesList_whenGetAllEmployeePage_thenEmployeeList(){

		// given - prediction or setup
		List<Employee> employeeList = Arrays.asList(employee);
		Pageable pageable = PageRequest.of(0, 15);

		Page<Employee> pageEm = new PageImpl<>(employeeList, pageable, 1);

		given(employeeRepository.getAllEmployeePageable(pageable)).willReturn(pageEm);

		// when - action or behavior that we are going test
		Page<Employee> employeeList1 = employeeService.getAllEmployeePage(pageable);

		// then - verify the output

		assertThat(employeeList1.getTotalElements()).isEqualTo(1);
	}

}
