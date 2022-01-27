package com.catoj.homework.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;
import static org.neo4j.driver.Values.parameters;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	private final Neo4jClient neo4jClient;
	
	private final Driver driver;
	
	private final DatabaseSelectionProvider databaseSelectionProvider;
	
	EmployeeService(EmployeeRepository employeeRepository,
			Neo4jClient neo4jClient,
			Driver driver,
			DatabaseSelectionProvider databaseSelectionProvider) {
		this.employeeRepository = employeeRepository;
		this.neo4jClient = neo4jClient;
		this.driver = driver;
		this.databaseSelectionProvider = databaseSelectionProvider;
	}

	public List<EmployeeDto> searchEmployeesById(int emp_id) {
		return this.employeeRepository.findSearchResults()
				.stream()
				.map(EmployeeDto::new)
				.collect(Collectors.toList());
		}

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		try (Session session = driver.session()) {
			var records = session.readTransaction(tx -> tx.run(
                    "MATCH (e:Employee) " +
			        "RETURN e.emp_id AS emp_id, e.name AS name"
					).list());

			    records.forEach(record -> {
				    var employee = new Employee(record.get("emp_id").asInt(), record.get("name").asString());
	                employees.add(employee);
		    	}
			);
			session.close();
			return employees;
		}
	}

	public List<Employee> createEmployee(int emp_id, String name) {
		List<Employee> employees = new ArrayList<Employee>();
		try (Session session = driver.session()) {
            var record = session.writeTransaction(tx -> {
                return tx.run("CREATE (e:Employee {emp_id:$emp_id, name:$name}) " +
                              "RETURN e.emp_id AS emp_id, e.name AS name",
                        parameters("emp_id",emp_id,"name",name)).single();
            });
			session.close();
            employees.add(new Employee(record.get("emp_id").asInt(), record.get("name").asString()));
        } catch (Exception e) {
            e.printStackTrace();
        } 
		return employees;
	}

}
