package com.elbatech.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elbatech.hr.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
	void deleteByDepartmentName(String departmentName);
	Department findByDepartmentName(String departmentName);
}