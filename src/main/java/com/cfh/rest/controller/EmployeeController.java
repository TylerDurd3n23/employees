package com.cfh.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cfh.rest.dto.EmployeeResponse;
import com.cfh.rest.dto.EmployeesResponse;
import com.cfh.rest.exceptions.BadRequest;
import com.cfh.rest.model.EmployeeModel;
import com.cfh.rest.services.EmployeeService;
import com.cfh.rest.validator.EmployeeValidator;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeValidator employeValidator;
	@PostMapping()
	public EmployeeResponse insert(@Valid @RequestBody EmployeeModel employe) throws BadRequest{
		this.employeValidator.validate(employe);
		return this.employeeService.insert(employe);
	}
	
	@GetMapping()
	public EmployeesResponse getEmployees(@RequestParam("employee_id") Optional<String> employeesIds,
			@RequestParam("start_date") Optional<String> startDate,
			@RequestParam("end_date") Optional<String> endDate){
		return this.employeeService.getEmployees(employeesIds, startDate, endDate);
	}
}
