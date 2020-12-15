package com.lca.employee.workdays.management.controllers;

import com.lca.employee.workdays.management.data.Employee;
import com.lca.employee.workdays.management.exceptions.EmployeeNotFoundException;
import com.lca.employee.workdays.management.exceptions.GlobalCustomException;
import com.lca.employee.workdays.management.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees() {
        LOGGER.info("Entered to fetch all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        LOGGER.info("Retrieved employees {}!", employees.size());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "empId") long empId) throws EmployeeNotFoundException {
        LOGGER.info("Entered to fetch employee with id {} ", empId);
        Optional<Employee> emp = employeeService.getEmployee(empId);
        if (emp.isPresent()) {
            LOGGER.info("Retrieved employee id {} !", empId);
            return new ResponseEntity(emp.get(), HttpStatus.OK);
        } else {
            LOGGER.info("No employee id {} Found!", empId);
            throw new EmployeeNotFoundException("Not a valid Employee ID", "Employee ID Does not exist!");
        }
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployeeDetails(@Valid @RequestBody Employee employee) throws GlobalCustomException {
        LOGGER.info("Entered to update employee with id {} ", employee.getEmpId());
        Optional<Employee> emp = employeeService.updateEmployee(employee);
        if (emp.isPresent()) {
            LOGGER.info("Updated employee with id {} ", employee.getEmpId());
            return new ResponseEntity(emp.get(), HttpStatus.OK);
        } else {
            //return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            throw new EmployeeNotFoundException("Not a valid Employee ID", "Employee ID Does not exist!");
        }
    }
}
