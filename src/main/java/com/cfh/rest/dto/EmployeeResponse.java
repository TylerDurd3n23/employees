package com.cfh.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeResponse {
	@JsonProperty(value="id")
	private String id;
	@JsonProperty(value="success")
	private boolean success;
}
