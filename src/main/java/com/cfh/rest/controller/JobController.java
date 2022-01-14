package com.cfh.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.cfh.rest.dto.EmployeesJobResponse;
import com.cfh.rest.dto.JobResponse;
import com.cfh.rest.model.JobModel;
import com.cfh.rest.services.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	@Autowired
	JobService jobService;
	
	@PostMapping()
	public JobResponse insert(@Valid @RequestBody JobModel employe){
		
		return this.jobService.insert(employe);
	}
	
	@GetMapping(path="/{id}/employees")
	public EmployeesJobResponse getEmployeesByJob(@PathVariable("id") Long jobId,
			@RequestParam("gender") Optional<Long> genderId,
			@RequestParam("sort") Optional<String> sort){
		return this.jobService.getEmployeesByJob(jobId, genderId, sort);
	}
}
