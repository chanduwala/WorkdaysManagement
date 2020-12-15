package com.lca.employee.workdays.management.factory;

import com.lca.employee.workdays.management.data.Employee;
import com.lca.employee.workdays.management.data.EmployeeType;

public class EmployeeFactory {
    public static Employee createEmployee(EmployeeType employeeType, long empId, String name) {
        return new Employee(name, empId, employeeType);
    }
}
