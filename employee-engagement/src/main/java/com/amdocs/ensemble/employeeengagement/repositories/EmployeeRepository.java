package com.amdocs.ensemble.employeeengagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.ensemble.employeeengagement.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
