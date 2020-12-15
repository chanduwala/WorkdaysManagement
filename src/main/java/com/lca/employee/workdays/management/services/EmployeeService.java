package com.lca.employee.workdays.management.services;

import com.lca.employee.workdays.management.WorkdaysManagementApplication;
import com.lca.employee.workdays.management.data.Employee;
import com.lca.employee.workdays.management.data.EmployeeType;
import com.lca.employee.workdays.management.exceptions.GlobalCustomException;
import com.lca.employee.workdays.management.factory.EmployeeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private List<Employee> employees;

    EmployeeService() {
        employees = Arrays.asList(
                EmployeeFactory.createEmployee(EmployeeType.HOURLY, 1000l, "Sekhar"),
                EmployeeFactory.createEmployee(EmployeeType.HOURLY, 1001l, "John"),
                EmployeeFactory.createEmployee(EmployeeType.HOURLY, 1002l, "Steve"),
                EmployeeFactory.createEmployee(EmployeeType.SALARIED, 1003l, "Neeraj"),
                EmployeeFactory.createEmployee(EmployeeType.SALARIED, 1004l, "Nancy"),
                EmployeeFactory.createEmployee(EmployeeType.SALARIED, 1005l, "Jonathon"),
                EmployeeFactory.createEmployee(EmployeeType.SALARIED, 1006l, "Rick"),
                EmployeeFactory.createEmployee(EmployeeType.SALARIED, 1007l, "Carie"),
                EmployeeFactory.createEmployee(EmployeeType.MANAGER, 1008l, "Kathy"),
                EmployeeFactory.createEmployee(EmployeeType.MANAGER, 1009l, "Josh"));
        LOGGER.info("Initialized default employees!");
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Optional<Employee> getEmployee(long empId) {
        LOGGER.info("Trying to find employee {} ", empId);
        Optional<Employee> emp = employees.stream().filter(e -> e.getEmpId() == empId).findFirst();
        if (emp.isPresent()) {
            LOGGER.info("Employee found {} ", emp.get().getName());
            return emp;
        }
        LOGGER.info("Unable to find employee {} ", empId);
        return Optional.empty();
    }

    public Optional<Employee> updateEmployee(Employee employee) throws GlobalCustomException {
        LOGGER.info("Trying to update employee {} ", employee.getEmpId());
        Optional<Employee> emp = getEmployee(employee.getEmpId());
        if (emp.isPresent()) {
            Employee empToUpdate = emp.get();
            if (employee.getWorkDays() != 0) {
                empToUpdate.work(employee.getWorkDays());
            }
            empToUpdate.takeVacation(employee.getVacationDays());

            return emp;
        }
        return Optional.empty();
    }
}