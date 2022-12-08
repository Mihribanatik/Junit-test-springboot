package com.test.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.model.Employee;
import com.test.springboot.service.EmployeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeService employeService;

	@Autowired
	private ObjectMapper objectMapper;

	Employee employee;

	void setup(){
		employee= Employee.builder().firstName("mihriban").lastName("atunc").build();
	}

	@Test
	@DisplayName("Junit test for create method ")
	public void givenEmployee_whenCreateEmployee_thenEmployee() throws Exception {

		Employee employee =Employee.builder()
				.firstName("mihri")
				.lastName("utanc")
				.build();

		when(employeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
				.thenReturn(employee);

		RequestBuilder request =
				MockMvcRequestBuilders
						.post("/api/employees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee));

		mockMvc.perform(request)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(jsonPath("$.firstName", CoreMatchers.is("mihri")))
				.andExpect(jsonPath("$.lastName",CoreMatchers.is("utanc")))
				.andReturn();

		Mockito.verify(employeService,times(1)).saveEmployee(employee);

	}

}
