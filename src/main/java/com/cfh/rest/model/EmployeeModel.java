package com.cfh.rest.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="EMPLOYEES")
public class EmployeeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;
	//@ManyToOne
    @JoinColumn(name="gender_id")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private GenderModel gender;
	
	@JoinColumn(name = "job_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private JobModel job;
	
    @NotBlank(message = "El nombre es requerido.")
	private String name;
    
	@Column(name = "last_name")
    @NotBlank(message = "El apellido es requerido.")
    private String lastName;
    @NotNull(message = "La fecha de nacimiento es requerida.")
	private Date birthdate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getGenderId() {
		return gender.getId();
	}
	public void setGenderId(int gender) {
		this.gender.setId(gender);;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public long getJobId() {
		return job.getId();
	}
	public void setJobId(int jobId) {
		this.job.setId(jobId);
	}
	
	public JobModel getJob() {
		return this.job;
	}
	public GenderModel getGender() {
		return this.gender;
	}
	
	public EmployeeModel() {
		this.job = new JobModel();
		this.gender = new GenderModel();
	}

}
