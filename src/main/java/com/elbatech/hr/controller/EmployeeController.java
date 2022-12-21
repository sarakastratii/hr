package com.elbatech.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elbatech.hr.helper.ExcelHelper;
import com.elbatech.hr.message.ResponseMessage;
import com.elbatech.hr.model.Employee;
import com.elbatech.hr.service.EmployeeService;
import com.elbatech.hr.service.EmployeeService.Result;

@RestController
@RequestMapping("/api")
public class EmployeeController {
        @Autowired
        EmployeeService empService;


        @RequestMapping(value="/employees/name", method=RequestMethod.GET)
        public List<Employee> readEmployeesByName(@RequestParam("param") String param) {
            return empService.getEmployeesByName(param);
        }

        @RequestMapping(value="/employees/statusEmployee", method=RequestMethod.GET)
        public List<Employee> readEmployeesByStatusEmployee(@RequestParam("param") String param) {
            return empService.getEmployeesByStatusEmployee(param);
        }

        @RequestMapping(value="/employees", method=RequestMethod.POST)
        public Employee createEmployee(@RequestBody Employee emp) {
            return empService.createEmployee(emp);
        }

        @RequestMapping(value="/employees", method=RequestMethod.GET)
        public List<Employee> readEmployees() {
            return empService.getEmployees();
        }

        @RequestMapping(value="/employees/asc", method=RequestMethod.GET)
        public List<Employee> readEmployeesASC() {
            return empService.getEmployeesASC();
        }

        @RequestMapping(value="/employees/myresult", method=RequestMethod.GET)
        public List<Result> myResult() {
        	return empService.getNoOfEmployeesByDepartment();
        }



        @RequestMapping(value="/employees/{username}", method=RequestMethod.PUT)
        public Employee readEmployees(@PathVariable(value = "username") String username, @RequestBody Employee empDetails) {
            return empService.updateEmployee(username, empDetails);
        }

        @RequestMapping(value="/employees/{username}", method=RequestMethod.DELETE)
        public void deleteEmployees(@PathVariable(value = "username") String username) {
            empService.deleteEmployee(username);
        }

        @PostMapping("/employees/upload")
        public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
          String message = "";

          if (ExcelHelper.hasExcelFormat(file)) {

            try {
				empService.save(file);

              message = "Uploaded the file successfully: " + file.getOriginalFilename();
              return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
              message = e.getMessage()+"\nCould not upload the file: " + file.getOriginalFilename() + "!";
              return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
          }

          message = "Please upload an excel file!";
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }

}