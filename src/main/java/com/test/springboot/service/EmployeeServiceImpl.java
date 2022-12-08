package com.test.springboot.service;

import com.test.springboot.exception.ResourceNotFountException;
import com.test.springboot.model.Employee;
import com.test.springboot.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl implements EmployeService{

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee){

		Optional<Employee> employeeByEmail = employeeRepository.findByEmail(employee.getEmail());
		if (employeeByEmail.isPresent()){
			throw new ResourceNotFountException("Employee already exist with given email" + employee.getEmail());
		}

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployess() {
		return employeeRepository.findAll();
	}

	@Override
	public Page<Employee> getAllEmployeePage(Pageable pageable) {
		return employeeRepository.getAllEmployeePageable(pageable);
	}

	@Override
	public Optional<Employee> getEmployeeById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.deleteById(id);
	}

}
