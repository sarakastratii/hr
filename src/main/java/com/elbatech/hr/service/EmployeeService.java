package com.elbatech.hr.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elbatech.hr.helper.ExcelHelper;
import com.elbatech.hr.model.Department;
import com.elbatech.hr.model.Employee;
import com.elbatech.hr.repository.DepartmentRepository;
import com.elbatech.hr.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepository;
	@Autowired
	DepartmentRepository depRepository;

	public void save(MultipartFile file) {
		try {
			List[] result = ExcelHelper.excelToEmployees(file.getInputStream());
			List<Employee> employees = result[0];
			List<Department> departments = result[1];

			depRepository.saveAll(departments);

			for (Employee employee : employees) {
				for (Department department : departments) {
					if (employee.getDepartment().equals(department.getDepartmentName())) {
						employee.setDepartmentName(department);
					}
				}
			}
			empRepository.saveAll(employees);

		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	// CREATE
	public Employee createEmployee(Employee emp) {
		return empRepository.save(emp);
	}

	// READ
	public List<Employee> getEmployees() {
		return empRepository.findAll();
	}


	public List<Employee> getEmployeesASC() {
		System.out.println("getEmployees");
		return empRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	public List<Employee> getEmployeesByName(String name) {
		return empRepository.findByName(name);
	}

	public List<Employee> getEmployeesByStatusEmployee(String statusEmployee) {
		return empRepository.findByStatusEmployee(statusEmployee);
	}

	public List<Result> getNoOfEmployeesByDepartment(){
		List<Employee> emps= empRepository.findAll();
    	ArrayList<Result> list=new ArrayList<>();

    	List<String> deps= new ArrayList<>();
    	for(Employee e:emps) {
    		if(!deps.contains(e.getDepartment())) {
    			deps.add(e.getDepartment());
    		}
    	}

    	int countEmps=0;
    	ArrayList<String> lastNames=new ArrayList<>();
    	for(String d: deps) {
    		for(Employee e:emps) {
    			if(d.equals(e.getDepartment())) {
    				countEmps++;
    				if(!lastNames.contains(getLastName(e.getName()))) {
    					lastNames.add(getLastName(e.getName()));
    				}
    			}
    		}
    		list.add(new Result(d, countEmps, lastNames));
    		countEmps=0;
    		lastNames=new ArrayList<>();
    	}

    	return list;
	}

	private String getLastName(String name) {
		return name.split(" ",2)[1];
	}

	 public class Result{
		 private String dep;
		 private int noEmp;
		 private ArrayList<String> lastNames;
			public Result(String dep, int noEmp, ArrayList<String> lastNames) {
				super();
				this.dep = dep;
				this.noEmp = noEmp;
				this.lastNames=lastNames;

			}
			public String getDep() {
				return dep;
			}
			public void setDep(String dep) {
				this.dep = dep;
			}
			public int getNoEmp() {
				return noEmp;
			}
			public void setNoEmp(int noEmp) {
				this.noEmp = noEmp;
			}
			public ArrayList<String> getLastNames() {
				return lastNames;
			}
			public void setLastNames(ArrayList<String> lastNames) {
				this.lastNames = lastNames;
			}


     }



	// DELETE
	public void deleteEmployee(String username) {
		empRepository.deleteByUsername(username);
	}

	// UPDATE
	public Employee updateEmployee(String username, Employee employeeDetails) {
		Employee emp = empRepository.findByUsername(username);
		emp.setStatusEmployee(employeeDetails.getStatusEmployee());
		emp.setAddress(employeeDetails.getAddress());
		emp.setEmail(employeeDetails.getEmail());
		emp.setEndDate(employeeDetails.getEndDate());
		emp.setManager(employeeDetails.getManager());
		emp.setName(employeeDetails.getName());
		emp.setPhoneNumber(employeeDetails.getPhoneNumber());
		emp.setStartDate(employeeDetails.getStartDate());

		return empRepository.save(emp);
	}
}