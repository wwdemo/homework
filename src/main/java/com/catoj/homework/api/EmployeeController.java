package com.catoj.homework.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.catoj.homework.employee.Employee;
import com.catoj.homework.employee.EmployeeDto;
import com.catoj.homework.employee.EmployeeService;

/**
 * 
 * @author catojohannessen
 *
 */
@RestController
public class EmployeeController {
	
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employee")
	public List<Employee> getAll() {
		return employeeService.getAll();
	}
	
	@PostMapping("/employee/{id}/{name}")
	public List<Employee>  createEmployeeURI(@PathVariable("id") int id, @PathVariable("name") String name) {
		return employeeService.createEmployee(id, name);
	}
	
	@PostMapping("/employee")
	public List<Employee>  createEmployeeJSON(@RequestBody Employee newEmployee) {
		return employeeService.createEmployee(newEmployee.getEmp_id(), newEmployee.getName());
	}
	
}
