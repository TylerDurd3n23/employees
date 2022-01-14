package com.cfh.rest.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfh.rest.dto.EmployeeResponse;
import com.cfh.rest.dto.EmployeesJobResponse;
import com.cfh.rest.dto.JobResponse;
import com.cfh.rest.dto.Job;
import com.cfh.rest.dto.Gender;
import com.cfh.rest.dto.Employee;
import com.cfh.rest.model.EmployeeModel;
import com.cfh.rest.model.JobModel;
import com.cfh.rest.repository.JobRepository;
import com.cfh.rest.utils.EmployeesSorter;

import ch.qos.logback.classic.Logger;

@Service
public class JobService {
	private static Logger logger  = (Logger) LoggerFactory.getLogger(EmployeeService.class);
	public static boolean SUCCESS = true;
	public static boolean UNSUCCESS = false;
	public static String DESC = "desc";

	@Autowired
	JobRepository jobRepository;
	public JobResponse insert(JobModel job) {
		JobResponse response = JobResponse.builder().id(null).success(this.UNSUCCESS).build();
		JobModel jobModel = this.jobRepository.save(job);
		if(jobModel.getId() > 0) {
			logger.info("Se guardo el puesto "+jobModel.getId());
			response = JobResponse.builder().id(jobModel.getId()+"").success(this.SUCCESS).build();
		}
		return response;
	}
	
	public EmployeesJobResponse getEmployeesByJob(Long jobId, Optional<Long>filterGender, Optional<String> sort) {
		EmployeesJobResponse response = EmployeesJobResponse.builder().employees(null).success(this.UNSUCCESS).build(); 
		Optional<JobModel> job = this.jobRepository.findById(jobId);
		 if(!job.isEmpty()) {
			 ArrayList<Employee> employeeList = this.mapEmployees(job.get());
			 
			 response = EmployeesJobResponse.builder()
					 .employees(this.sortEmployees(this.filterEmployees(employeeList, filterGender), sort))
					 .success(this.SUCCESS)
					 .build();
		 }
		return response;
	}
	
	private ArrayList<Employee> sortEmployees(ArrayList<Employee> employeeList, Optional<String> sort){
		if(!sort.isEmpty() && sort.get().equals(this.DESC)) {
			employeeList.sort(new EmployeesSorter());
		}
		else {
			employeeList.sort(new EmployeesSorter().reversed());
		}
		return employeeList;
	}
	
	private ArrayList<Employee> filterEmployees(ArrayList<Employee> employeeList, Optional<Long> gender){
		if(gender.isEmpty()) {
			return employeeList;
		}
		else {
			logger.warn("Filtrando por genero :"+gender.get());
			ArrayList<Employee> employeeListFiltered = (ArrayList<Employee>) employeeList.stream().
					 filter(em -> em.getGender().getId() == gender.get()).
					 collect(Collectors.toList());
			return employeeListFiltered;
		}
	}
	
	private ArrayList<Employee> mapEmployees(JobModel jm){
		ArrayList<Employee> employeeList = new ArrayList<>();
		 for (EmployeeModel model : jm.getEmployees())
		 {
			 Job jobDto = Job.builder()
					 .id(model.getJobId())
					 .name(model.getJob().getName())
					 .salary(model.getJob().getSalary()).build();
			 Gender genderDto = Gender.builder()
					 .id(model.getGenderId())
					 .name(model.getGender().getName())
					 .build();
			 Employee employeeDto = Employee.builder()
					 .id(model.getId())
					 .name(model.getName())
					 .lastName(model.getLastName())
					 .birthdate(model.getBirthdate())
					 .job(jobDto)
					 .gender(genderDto)
					 .build();
			 employeeList.add(employeeDto);
			 logger.info("Esta iterando "+model.getName());
		 }
		 
		 return employeeList;
	}

}
