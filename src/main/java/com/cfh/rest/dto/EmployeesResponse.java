package com.cfh.rest.dto;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeesResponse {
	@JsonProperty(value="employees")
	private ArrayList<Employees> employees;
	@JsonProperty(value="succes")
	private boolean success;
}
