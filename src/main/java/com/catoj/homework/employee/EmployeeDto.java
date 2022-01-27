package com.catoj.homework.employee;

public class EmployeeDto {
	private final Employee employee;

	public EmployeeDto(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}
}
