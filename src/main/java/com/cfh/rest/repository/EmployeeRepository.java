package com.cfh.rest.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cfh.rest.model.EmployeeModel;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeModel, Long>{
	public abstract ArrayList<EmployeeModel> findByNameAndLastName(String name, String lastName);
	public abstract Optional<EmployeeModel> findByIdAndBirthdateBetween(long id, Date startDate, Date endDate);

}
