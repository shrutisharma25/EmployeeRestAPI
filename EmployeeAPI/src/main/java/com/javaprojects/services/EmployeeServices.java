package com.javaprojects.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaprojects.dao.EmployeeRepository;
import com.javaprojects.entity.Employee;

@Component
public class EmployeeServices {
	
	@Autowired
	EmployeeRepository employeeRepository;

	//GET ALL EMPLOYEE
	public List<Employee> getAllEmployees(){
		List<Employee> list = (List<Employee>)this.employeeRepository.findAll();
		return list;
	}

	//ADD THE EMPLOYEE DETAIL
	public Employee addEmployee(Employee e) {
		Employee employee = employeeRepository.save(e);
		return employee;
	}

	//GET EMPLOYEE DETAILS OF PARTICULAR ID
	public Employee getEmployeeById(int id){
		Employee employee =null;
		try {
			this.employeeRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	//FOR DELEING EMPLOYEE DETAILS
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	//FOR UPDATING THE EMPLOYEE DETAIL
	public void updateEmployee(Employee employee, int employeeId) {
		employee.setId(employeeId);
		employeeRepository.save(employee);
	}

}
