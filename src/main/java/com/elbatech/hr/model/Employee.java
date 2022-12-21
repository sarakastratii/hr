package com.elbatech.hr.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@Column(name = "manager")
	private String manager;

	@Column(name = "email")
	private String email;

	@Column(name = "department")
	private String department;

	@ManyToOne
    @JoinColumn(name="departmentName", referencedColumnName = "departmentName")
    private Department departmentName;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "statusEmployee")
	private String statusEmployee;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Department getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(Department departmentName) {
		this.departmentName = departmentName;
	}

	public String getStatusEmployee() {
		return statusEmployee;
	}

	public void setStatusEmployee(String statusEmployee) {
		this.statusEmployee = statusEmployee;
	}

	@Override
	public String toString() {
		return "Employee:" + " " + name + " " + manager + " " + username + " " + email + " " + departmentName + " "
				+ phoneNumber + " " + address

				+ " " + new SimpleDateFormat("yyyy-MM-dd").format(startDate) + " "
				+ new SimpleDateFormat("yyyy-MM-dd").format(endDate) + " " + statusEmployee + "";
	}



}