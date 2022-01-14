package com.cfh.rest.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfh.rest.exceptions.BadRequest;
import com.cfh.rest.model.EmployeeModel;
import com.cfh.rest.repository.EmployeeRepository;
import com.cfh.rest.repository.GenderRepository;
import com.cfh.rest.repository.JobRepository;

import ch.qos.logback.classic.Logger;

@Component
public class EmployeeValidator {
	private static Logger logger  = (Logger) LoggerFactory.getLogger(EmployeeValidator.class);
	public static int LEGAL_AGE = 18;
	@Autowired
	JobRepository jobRepository;
	@Autowired
	GenderRepository genderRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	public boolean validate(EmployeeModel employee) throws BadRequest  {
		boolean isValid = false;
		if(!this.validateJob(employee.getJobId()) || !this.validateGender(employee.getGenderId())) {
			this.message("El id de trabajo o genero son incorrectos.");
		}
		if(!this.validateNames(employee.getName(), employee.getLastName())) {
			logger.warn("aki se muere...");
			this.message("El empleado ya se encuentran registrados. Verificar el nombre y apellido.");
		}
		if(!this.isAdult(employee.getBirthdate())) {
			this.message("El empleado debe de ser mayor de edad.");
		}
		return isValid;
	}
	
	private void message(String message) throws BadRequest {
		throw new BadRequest(message);
	}
	
	private boolean validateJob(long job) {
		//logger.info("existe empleo ? "+this.jobRepository.findById(job).isEmpty());
		return !this.jobRepository.findById(job).isEmpty();
	}
	
	private boolean validateGender(long gender) {
		//logger.info("existe genero ? "+this.genderRepository.findById(gender).isEmpty());
		return !this.genderRepository.findById(gender).isEmpty();
	}
	
	private boolean isAdult(Date birthdate) {
		int years = Period.between(this.convertToLocalDateViaInstant(birthdate), LocalDate.now()).getYears();
		logger.info("Edad : "+years);
		return (years >= this.LEGAL_AGE);
	}
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	private boolean validateNames(String name, String lastName) {
		logger.info("name: "+name+" - last_name: "+lastName);
		return (this.employeeRepository.findByNameAndLastName(name, lastName).isEmpty());
	}
}
