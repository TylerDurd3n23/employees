package com.cfh.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Job {
	@JsonProperty(value="id")
	private long id;
	@JsonProperty(value="name")
	private String name;
	@JsonProperty(value="salary")
	private float salary;
}
