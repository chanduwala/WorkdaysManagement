package com.lca.employee.workdays.management.data;

public enum EmployeeType {

    HOURLY(10), SALARIED(15), MANAGER(30);

    private int maxWorkdays = 260;
    private double maxVacationDays;

    EmployeeType(double vacationDays) {
        this.maxVacationDays = vacationDays;
    }

    public int getMaxWorkdays() {
        return maxWorkdays;
    }

    public double getMaxVacationDays() {
        return maxVacationDays;
    }
}
