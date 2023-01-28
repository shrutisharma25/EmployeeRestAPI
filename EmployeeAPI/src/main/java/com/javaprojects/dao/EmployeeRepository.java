package com.javaprojects.dao;

import org.springframework.data.repository.CrudRepository;

import com.javaprojects.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	public Employee findById(int id); 

}
