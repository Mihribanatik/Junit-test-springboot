package com.test.springboot.controller;


import com.test.springboot.model.Employee;
import com.test.springboot.service.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeService employeService;

	@PostMapping
	public Employee createEmploye(@RequestBody Employee employee){
		return employeService.saveEmployee(employee);
	}

}
