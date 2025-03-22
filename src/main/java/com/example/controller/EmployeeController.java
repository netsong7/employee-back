package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Employee;
import com.example.service.IEmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin()
class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEmployeeService employeeService;

    //get all employees
    @GetMapping("/employees")
    @Operation(summary = "Get employee list", description = "Retrieve a list of all employees")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employee list")
    public List<Employee> getAll() {
        logger.debug("employee_list");

        return employeeService.getAll();
    }

    //add employee
    @PostMapping("/employees")
    @Operation(summary = "Add a new employee", description = "Create a new employee record")
    @ApiResponse(responseCode = "200", description = "Employee created successfully")
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.debug("employee_add");

        return employeeService.add(employee);
    }

    //get employee by id
    @GetMapping("/employees/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieve employee details by ID")
    @Parameter(name = "id", description = "ID of the employee", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "Employee found")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {

        logger.debug("employee_getid");

        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    //update employee
    @PutMapping("/employees")
    @Operation(summary = "Update an employee", description = "Update existing employee details")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {

        logger.debug("employee_update");

        Employee employeeUpdated = employeeService.update(employee);
        return ResponseEntity.ok(employeeUpdated);
    }

    //delete employee
    @PostMapping("/employees/delete")
    @Operation(summary = "Delete an employee", description = "Delete an employee record")
    @ApiResponse(responseCode = "200", description = "Employee deleted successfully")
    public ResponseEntity<String> delete(@RequestBody Employee employee) {
        employeeService.delete(employee);
        return ResponseEntity.ok("Employee deleted.");
    }

    // deleteById
    @DeleteMapping("employees/{id}")
    @Operation(summary = "Delete employee by ID", description = "Delete an employee by their ID")
    @Parameter(name = "id", description = "ID of the employee to delete", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "Employee deleted successfully")
    public ResponseEntity<Map<String,Boolean>> deleteById(@PathVariable("id") Long id){
        logger.debug("employee_delete_byid");

        employeeService.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/healthz")
    @Operation(summary = "Health check", description = "Check if the service is up")
    @ApiResponse(responseCode = "200", description = "Service is up")
    public String healthz() {
        logger.debug("########################healthz");
        return "UP";
    }
}
