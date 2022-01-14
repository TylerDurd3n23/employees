package com.cfh.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Employees {
	@JsonProperty(value="id")
	private long id;
	@JsonProperty(value="name")
	private String name;
	@JsonProperty(value="last_name")
	private String lastName;
	@JsonProperty(value="birthdate")
	private Date birthdate;
	@JsonProperty(value="job_id")
	private long jobId;
	@JsonProperty(value="gender_id")
	private long genderId;
}
