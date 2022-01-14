package com.cfh.rest.utils;

import org.springframework.stereotype.Component;

import com.cfh.rest.dto.Employee;

import java.util.Comparator;

@Component
public class EmployeesSorter implements Comparator<Employee> {
	 @Override
	    public int compare(Employee o1, Employee o2) {
	        return o2.getLastName().compareToIgnoreCase(o1.getLastName());
	    }
}
