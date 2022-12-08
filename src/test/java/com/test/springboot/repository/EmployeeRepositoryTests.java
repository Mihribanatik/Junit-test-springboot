package com.test.springboot.repository;

import com.test.springboot.model.Employee;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	private Employee employee;

	@BeforeEach
	public void setup(){
		employee = Employee.builder()
				.firstName("mihriban")
				.lastName("utanc")
				.email("mihriban@utanc")
				.build();
	}

	@DisplayName("Junit test for save employee operation")
	@Test
	public void  givenEmployee_whenSave_thenReturnSavedEmployed(){

		// given - predictioın or setup

		// when - action or behavior that we are going test

		Employee savedEmployee = employeeRepository.save(employee);

		// then - verify the output

		Assertions.assertThat(savedEmployee).isNotNull();
		Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
		Assertions.assertThat(savedEmployee.getFirstName()).isEqualTo("mihriban");
	}


	@DisplayName("Junit test for get all employee operation")
	@Test
	public void givenEmployeesList_whenFindAllEmployee_thenReturnEmployeeList(){

		// given - predictioın or setup

		Employee employee2 = Employee.builder()
				.firstName("zülal")
				.lastName("kılınç")
				.email("zülal@kılınç")
				.build();

		employeeRepository.save(employee);
		employeeRepository.save(employee2);


		// when - action or behavior that we are going test

		List<Employee> employeeList= employeeRepository.findAll();

		// then - verify the output

		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);
	}

	@DisplayName("Junit test for get employee by id")
	@Test
	public void givenEmployee_whenFindById_thenReturnEmployee(){

		// given - predictioın or setup
		Employee employee2 = Employee.builder()
				.firstName("zülal")
				.lastName("kılınç")
				.email("zülal@kılınç")
				.build();

		employeeRepository.save(employee);
		employeeRepository.save(employee2);


		// when - action or behavior that we are going test

		Employee byId = employeeRepository.findById(employee.getId()).get();

		// then - verify the output

		assertThat(byId).isEqualTo(employee);
	}

	@DisplayName("Junit test for get employee by email")
	@Test
	public void givenEmployee_whenFindByEmail_thenReturnEmployee(){

		// given - predictioın or setup
		employeeRepository.save(employee);

		// when - action or behavior that we are going test
		Optional<Employee> byEmail = employeeRepository.findByEmail(employee.getEmail());

		// then - verify the output
		assertThat(byEmail).isNotNull();
		assertThat(byEmail).isEqualTo(employee);
	}


	@DisplayName("Junit test for update employee object")
	@Test
	public void givenEmployee_whenUpdate_thenReturnEmployee(){

		// given - predictioın or setup
		employeeRepository.save(employee);

		// when - action or behavior that we are going test
		Employee savedEmployeed = employeeRepository.findById(employee.getId()).get();
		savedEmployeed.setEmail("zülal@kılınç");

		Employee updateEmployee = employeeRepository.save(savedEmployeed);


		// then - verify the output
		assertThat(updateEmployee).isNotNull();
		assertThat(updateEmployee.getEmail()).isEqualTo(employee.getEmail());
	}

	@DisplayName("Junit test for delete employee operation")
	@Test
	public void givenEmployee_whenDelete_thenEmpoyee(){

		// given - prediction or setup
		employeeRepository.save(employee);


		//when - behaviour or action that we are going test
		employeeRepository.delete(employee);
		Optional<Employee> employeeObject = employeeRepository.findById(employee.getId());

		//then - verify the output
		assertThat(employeeObject).isEmpty();
	}

	@DisplayName("Junit test for custom query using JPQL with index")
	@Test
	public void givenFirstnameAndLastname_whenFindByJPQL_thenReturnEmployee(){

		// given - predictioın or setup
		employeeRepository.save(employee);

		// when - action or behavior that we are going test

		String firstname = "mihriban";
		String lastName = "utanc";

		Employee byJPQL = employeeRepository.findByJPQL(firstname, lastName);

		// then - verify the output

		assertThat(byJPQL).isNotNull();
		assertThat(byJPQL).isEqualTo(employee);
	}

	@DisplayName("Junit test for custom query using JPQL with named param")
	@Test
	public void givenNamedParam_whenFindByJPQLNamed_thenReturnEmployee(){

		// given - predictioın or setup
		employeeRepository.save(employee);

		// when - action or behavior that we are going test

		String firstname = "mihriban";
		String lastName = "utanc";

		Employee byJPQL = employeeRepository.findByJPQLNamedParams(firstname, lastName);

		// then - verify the output

		assertThat(byJPQL).isNotNull();
		assertThat(byJPQL).isEqualTo(employee);
	}
}
