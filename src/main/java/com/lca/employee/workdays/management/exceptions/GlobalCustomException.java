package com.lca.employee.workdays.management.exceptions;

public class GlobalCustomException extends Exception {
    private String message;
    private String details;

    protected GlobalCustomException() {
    }

    public GlobalCustomException(
            String message, String details) {
        this.message = message;
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

