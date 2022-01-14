package com.cfh.rest.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cfh.rest.model.JobModel;

@Repository
public interface JobRepository extends CrudRepository<JobModel, Long>{
	public abstract ArrayList<JobModel> findByName(String name);

}
