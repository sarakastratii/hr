package com.elbatech.hr.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Department")

public class Department {

	@Id
	@Column(name = "departmentName")
	private String departmentName;

	@Column(name = "departmentLeader")
	private String departmentLeader;

	@Column(name = "departmentPhone")
	private String departmentPhone;

	@OneToMany(mappedBy = "departmentName")
	private List<Employee> employees = new ArrayList<>();

	public Department() {

	}

	public Department(String name, String departmentLeader, String departmentPhone) {
		this.departmentName = name;
		this.departmentLeader = departmentLeader;
		this.departmentPhone = departmentPhone;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentLeader() {
		return departmentLeader;
	}

	public void setDepartmentLeader(String departmentLeader) {
		this.departmentLeader = departmentLeader;
	}

	public String getDepartmentPhone() {
		return departmentPhone;
	}

	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
	}

	@Override
	public String toString() {
		return "Department:" + departmentName + " " + departmentLeader + " " + departmentPhone + "";
	}

}
