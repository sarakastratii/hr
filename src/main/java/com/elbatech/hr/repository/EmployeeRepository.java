package com.elbatech.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elbatech.hr.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	void deleteByUsername(String username);
	Employee findByUsername(String username);
	List<Employee> findByName(String name);
	List<Employee> findByStatusEmployee(String statusEmployee);


}