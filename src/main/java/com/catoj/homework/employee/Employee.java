package com.catoj.homework.employee;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * 
 * @author catojohannessen
 *
 */

@Node
public class Employee {

	@Id
	private final Integer emp_id;

	private final String name;
	
	public Employee(Integer emp_id, String name) {
		this.emp_id = emp_id;
		this.name = name;
	}

	public Integer getEmp_id() {
		return this.emp_id;
	}

	public String getName() {
		return this.name;
	}

}
