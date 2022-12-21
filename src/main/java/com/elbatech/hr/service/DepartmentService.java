package com.elbatech.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.elbatech.hr.model.Department;
import com.elbatech.hr.repository.DepartmentRepository;

public class DepartmentService {
	@Autowired
	DepartmentRepository depRepository;

//	public void save(MultipartFile file) {
//		try {
//			List<Department> departments = ExcelHelper.excelToEmployees(file.getInputStream());
//			for (Employee e : employees) {
//				System.out.println("Lista 2:" + e);
//			}
//			depRepository.saveAll(employees);
//		} catch (IOException e) {
//			throw new RuntimeException("fail to store excel data: " + e.getMessage());
//		}
//	}

	// CREATE
	public Department createDepartment(Department dep) {
		return depRepository.save(dep);
	}

	// READ
	public List<Department> getDepartments() {
		return depRepository.findAll();
	}

	// DELETE
	public void deleteDepartment(String departmentName) {
		depRepository.deleteByDepartmentName(departmentName);
	}

	// UPDATE
	public Department updateEmployee(String departmentName, Department departmentDetails) {
		Department dep = depRepository.findByDepartmentName(departmentName);
		dep.setDepartmentLeader(departmentDetails.getDepartmentLeader());
		dep.setDepartmentPhone(departmentDetails.getDepartmentPhone());

		return depRepository.save(dep);
	}
}
