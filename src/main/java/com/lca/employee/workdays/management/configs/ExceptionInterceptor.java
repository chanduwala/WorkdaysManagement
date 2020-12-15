package com.lca.employee.workdays.management.configs;

import com.lca.employee.workdays.management.data.ErrorResponse;
import com.lca.employee.workdays.management.exceptions.EmployeeNotFoundException;
import com.lca.employee.workdays.management.exceptions.GlobalCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GlobalCustomException.class)
    public final ResponseEntity<Object> handleAllExceptions(GlobalCustomException ex) {
        ErrorResponse exceptionResponse =
                new ErrorResponse(
                        ex.getMessage(), ex.getDetails());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundExceptions(EmployeeNotFoundException ex) {
        ErrorResponse exceptionResponse =
                new ErrorResponse(
                        ex.getMessage(), ex.getDetails());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorResponse exceptionResponse =
                new ErrorResponse(
                        ex.getMessage(), ex.getLocalizedMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
