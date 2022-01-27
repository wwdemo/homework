package com.catoj.homework.employee;

import java.util.List;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

interface EmployeeRepository extends Repository<Employee, Integer> {
	
	@Query("MATCH (employee:Employee) RETURN employee")
	List<Employee> findSearchResults();

}
