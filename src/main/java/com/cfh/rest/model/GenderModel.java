package com.cfh.rest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GENDERS")
public class GenderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;
	
    //@OneToMany(mappedBy = "gender")
	//private List<EmployeeModel> employe;
    
    private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*public List<EmployeeModel> getEmploye() {
		return employe;
	}

	public void setEmploye(List<EmployeeModel> employe) {
		this.employe = employe;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
