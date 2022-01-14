package com.cfh.rest.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfh.rest.dto.Employee;
import com.cfh.rest.dto.EmployeeResponse;
import com.cfh.rest.dto.Employees;
import com.cfh.rest.dto.EmployeesResponse;
import com.cfh.rest.dto.Gender;
import com.cfh.rest.dto.Job;
import com.cfh.rest.model.EmployeeModel;
import com.cfh.rest.model.JobModel;
import com.cfh.rest.repository.EmployeeRepository;

import ch.qos.logback.classic.Logger;

@Service
public class EmployeeService {
	private static Logger logger  = (Logger) LoggerFactory.getLogger(EmployeeService.class);
	public static boolean SUCCESS = true;
	public static boolean UNSUCCESS = false;

	@Autowired
	EmployeeRepository employeeRepository;
	
	public EmployeesResponse getEmployees(Optional<String> employeesIds, Optional<String> startDate, Optional<String> endDate){
		EmployeesResponse response = EmployeesResponse.builder().employees(null).success(this.UNSUCCESS).build(); 
		ArrayList employeesList = new ArrayList<Employees>();;
		if(!employeesIds.isEmpty()) {
			String[] ids = employeesIds.get().split(",");
			Employees employee = null;
			for (String id : ids) {
				try {
					employee = this.async(id, startDate, endDate);
					if(employee != null) {
						employeesList.add(employee);
					}
				} catch (InterruptedException e) {
					logger.warn("Ocurrio un error en los threads");
				} catch (ExecutionException e) {
					logger.warn("Ocurrio un error en la ejecucion");
				}
			}
			response = EmployeesResponse.builder().employees(employeesList).success(this.SUCCESS).build();
		}
		return response;
	}
	
	public EmployeeResponse insert(EmployeeModel employee) {
		EmployeeResponse response = EmployeeResponse.builder().id(null).success(this.UNSUCCESS).build();
		EmployeeModel employeModel =  this.employeeRepository.save(employee);
		if(employeModel.getId() > 0) {
			logger.info("Se guardo el empleado "+employeModel.getId());
			response = EmployeeResponse.builder().id(employeModel.getId()+"").success(this.SUCCESS).build();
		}
		return response;
	}
	
	private Employees async(String id,Optional<String> startDate, Optional<String> endDate) throws InterruptedException, ExecutionException {
		CompletableFuture<Employees> future = CompletableFuture.supplyAsync(() -> {
			EmployeeModel employeeModel;
			Optional<EmployeeModel> optionalEM;
			Employees employeeDto = null;
		    try {
		        TimeUnit.MILLISECONDS.sleep(100);
		    } catch (InterruptedException e) {
		        throw new IllegalStateException(e);
		    }
		    logger.info("ids a buscar" + id);
			if(startDate.isEmpty() && endDate.isEmpty()) {
				optionalEM = employeeRepository.findById(Long.parseLong(id));
				logger.info("result " + optionalEM);
			}
			else {
				Date start;
				try {
					start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.get());
					Date end=new SimpleDateFormat("yyyy-MM-dd").parse(endDate.get());
					optionalEM = employeeRepository.findByIdAndBirthdateBetween(Long.parseLong(id), start, end);
				} catch (ParseException e) {
					logger.warn("ocurrio un error al hacer el cast de la fecha");
					optionalEM = employeeRepository.findById(Long.parseLong(id));
				}
				logger.info("result " + optionalEM);
			}
			if(!optionalEM.isEmpty()) {
				employeeDto = this.mapEmployees(optionalEM.get());
			}
		    return employeeDto;
		});
		return future.get();
	}
	
	private Employees mapEmployees(EmployeeModel model){
		Employees employeeDto = Employees.builder()
				 .id(model.getId())
				 .name(model.getName())
				 .lastName(model.getLastName())
				 .birthdate(model.getBirthdate())
				 .jobId(model.getJobId())
				 .genderId(model.getGenderId())
				 .build();
		 return employeeDto;
	}
}
