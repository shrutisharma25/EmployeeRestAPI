package com.javaprojects.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaprojects.entity.Employee;
import com.javaprojects.services.EmployeeServices;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeServices employeeService;

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getEmployee() {
		List<Employee> list = this.employeeService.getAllEmployees();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();   
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee emp=null;
		try {
			emp=this.employeeService.addEmployee(employee);
			return ResponseEntity.of(Optional.of(emp));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		Employee employee = employeeService.getEmployeeById(id);
		if(employee==null) { 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(employee));
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
		try {
		    this.employeeService.deleteEmployee(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/employee/{employeeId}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") int employeeId) {
		this.employeeService.updateEmployee(employee,employeeId);
		return employee;		
	}

}
