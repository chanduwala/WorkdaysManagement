package com.lca.employee.workdays.management.exceptions;

public class EmployeeNotFoundException extends GlobalCustomException {

    public EmployeeNotFoundException(
            String message, String details) {
        super(message, details);
    }
}
