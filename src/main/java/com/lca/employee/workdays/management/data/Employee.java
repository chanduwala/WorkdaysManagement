package com.lca.employee.workdays.management.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lca.employee.workdays.management.exceptions.GlobalCustomException;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ApiModel
public class Employee {

    private int workDays = 0;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EmployeeType employeeType;
    private double vacationDays = 0;
    private String name;
    @NotNull
    private long empId;

    public Employee(String name, long empId, EmployeeType employeeType) {
        this.name = name;
        this.empId = empId;
        this.employeeType = employeeType;
    }

    public long getEmpId() {
        return empId;
    }

    public int getWorkDays() {
        return workDays;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public double getVacationDays() {
        return vacationDays;
    }

    public String getName() {
        return name;
    }

    // Updating vacation days based on days worked.
    public void work(int days) throws GlobalCustomException {
        if (days < 0 || days > 260) {
            throw new GlobalCustomException("Invalid Work Days", "Working days should not be negative (OR) greater than 260");
        }
        //this.vacationDays = 260 - days == 0 ? this.employeeType.getMaxVacationDays() : 0;
        this.workDays = days;
        this.vacationDays = days / (260 / this.employeeType.getMaxVacationDays());
    }

    public void takeVacation(double vacationDays) throws GlobalCustomException {
        if (vacationDays > this.vacationDays || vacationDays < 0) {
            throw new GlobalCustomException("Invalid Vacation Days", "Vacation days should not be negative (OR) should not greater than " + this.vacationDays);
        }
        BigDecimal bd = new BigDecimal(this.vacationDays).setScale(2, RoundingMode.FLOOR)
                .subtract(new BigDecimal(vacationDays).setScale(2, RoundingMode.FLOOR));
        this.vacationDays = bd.doubleValue();
    }
}
