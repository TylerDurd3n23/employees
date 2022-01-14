package com.cfh.rest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="JOBS")
public class JobModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;
    @NotBlank(message = "El nombre es requerido.")
    private String name;
    @NotNull(message = "El salario es requerido.")
    private float salary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
    private List<EmployeeModel> employees;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public List<EmployeeModel> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeModel> employees) {
		this.employees = employees;
	}
	
}
