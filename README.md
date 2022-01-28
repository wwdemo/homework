# Neo4j Homework Project

The task is to develop a simple API that would process input data, communicate with Neo4J and return results to the client.

#### REST API that will support 2 operations
**Create an Employee node**

This operation will take 2 parameters; a String and an int. The String value will populate the name property of the new Employee node, the int value will populate emp_id value.


**Return all Employee nodes to the client**

For your API - Neo4J communication use a Bolt driver (https://neo4j.com/docs/developer-manual/current/drivers/).

## Demo

A demo solution is running in AWS and can be accessed [here](http://44.200.245.161). The API connects to a Neo4j Sandbox instance using Bolt. 

(Go directly to [Setup](https://github.com/wwdemo/homework#setup))

## Overview

The development project was created using the [spring initializr](https://start.spring.io), with the added dependency “Spring Data Neo4j”. The generated project contains the Spring Boot application and Maven dependencies for Neo4j integration. This was imported into Eclipse, with the Spring Tools Suite installed.

Neo4j’s example [project](https://github.com/neo4j-examples/movies-java-spring-data-neo4j) was used as a template for how to build the REST controller for the API, the service, the repository, the data transfer object and the entity.

When creating new Employee nodes, a “CREATE” Cypher statement is used. This creates a new Employee node for each invocation, so there can be duplicate nodes. To optionally prevent duplicates, you can set a constraint on the emp_id for Employee to be unique by running this Cypher statement in your Neo4j instance:

    CREATE CONSTRAINT ON (e:Employee) ASSERT e.emp_id IS UNIQUE


## API End Points:

These two end points retrieve all the employees and create new ones:
 
GET /employee
- retrieves emp_id and name for all Employee nodes in the database

POST /employee {JSON} 
- creates new Employee node with the emp_id and name from the JSON payload


Alternate POST end point:

POST /employee/{id}/{name} 
- creates new Employee node with the emp_id and name from the URI

The OpenAPI documentation for the REST service can be accessed at [http://localhost/v3/api-docs/](http://localhost/v3/api-docs/)

## Setup

The following steps are needed to run the API on your local machine.

To connect the API to your Neo4j database instance, go to **src/main/resources/application.properties** and add the values for the following properties:

spring.neo4j.uri=  
spring.neo4j.authentication.username=  
spring.neo4j.authentication.password=

The application is listening to port **80**. If this port is in use on your machine, change the **server.port** value in the application.properties file, for instance to 8080.

## Build and Run the Application
To build and run the application from the command line, you must have Maven and Java installed. Go to the project’s root directory and run the following commands:

mvn package  
cd target  
java -jar homework-1.0.0.jar

## Accessing the API

The API is accessible from a browser at [http://localhost](http://localhost)

You can also access the API from the command line using curl. **test/employee.curl.txt** contains some sample curl commands.


