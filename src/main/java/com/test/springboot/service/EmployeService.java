package com.test.springboot.service;

import com.test.springboot.exception.ResourceNotFountException;
import com.test.springboot.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
	 Employee saveEmployee(Employee employee);
	 List<Employee> getAllEmployess();

	 Page<Employee> getAllEmployeePage(Pageable pageable);
	Optional<Employee> getEmployeeById(long id);

	Employee updateEmployee(Employee employee);

	void deleteEmployee(long id);
}
