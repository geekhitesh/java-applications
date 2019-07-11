package com.amdocs.ensemble.employeeengagement.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.ensemble.employeeengagement.entities.Employee;
import com.amdocs.ensemble.employeeengagement.repositories.EmployeeRepository;

@RestController
public class EmployeeResource {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employee/list")
	@CrossOrigin
	public List<Employee> getEmployeeList() {
		
		List<Employee> empList= employeeRepository.findAll();
		
		return empList;
	}
	
	@PostMapping("employee/create")
	@CrossOrigin
	public void create(@RequestBody Employee employee) {
		
		employee.setCreatedAt(new Date());
		
		
		employee.setUpdatedAt(new Date());
		System.out.println(employee);
		employeeRepository.save(employee);
		
		
	}

	@PostMapping("employee/update")
	@CrossOrigin
	public void update(@RequestBody Employee employee) {
		
		
		employee.setUpdatedAt(new Date());
		
		System.out.println(employee);
		employeeRepository.save(employee);
		
	}	
}
