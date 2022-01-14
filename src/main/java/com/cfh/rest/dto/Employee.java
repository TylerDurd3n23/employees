package com.cfh.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Employee{
	@JsonProperty(value="id")
	private long id;
	@JsonProperty(value="name")
	private String name;
	@JsonProperty(value="last_name")
	private String lastName;
	@JsonProperty(value="birthdate")
	private Date birthdate;
	@JsonProperty(value="job")
	private Job job;
	@JsonProperty(value="gender")
	private Gender gender;
	
}
