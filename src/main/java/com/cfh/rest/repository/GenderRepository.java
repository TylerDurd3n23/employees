package com.cfh.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cfh.rest.model.GenderModel;

@Repository
public interface GenderRepository extends CrudRepository<GenderModel, Long>{

}
