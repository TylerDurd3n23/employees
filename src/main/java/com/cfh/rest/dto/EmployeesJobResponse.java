package com.cfh.rest.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeesJobResponse {
	@JsonProperty(value="employees")
	private ArrayList<Employee> employees;
	@JsonProperty(value="success")
	private boolean success;
}
