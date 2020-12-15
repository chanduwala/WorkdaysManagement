package com.lca.employee.workdays.management;

import com.lca.employee.workdays.management.data.Employee;
import com.lca.employee.workdays.management.data.EmployeeType;
import com.lca.employee.workdays.management.exceptions.GlobalCustomException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WorkdaysManagementApplicationTests {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    void contextLoads() {
    }

    @Test
    void whenHourlyEmplyeesUpdateWork_calculateVacation() throws GlobalCustomException {
        Employee user = new Employee("Sekhar", 1000, EmployeeType.HOURLY);
        user.work(260);
        assertThat(user.getVacationDays()).isEqualTo(10);
        user.takeVacation(5.0);
        assertThat(user.getVacationDays()).isEqualTo(5);
        user.takeVacation(7.0);

    }

    @Test
    void whenManagerEmplyeesUpdateWork_calculateVacation() throws GlobalCustomException {
        Employee user = new Employee("Sekhar", 1000, EmployeeType.MANAGER);
        user.work(260);
        assertThat(user.getVacationDays()).isEqualTo(30);
        user.takeVacation(5.0);
        assertThat(user.getVacationDays()).isEqualTo(25);
    }

    @Test
    void whenSalariedEmplyeesUpdateWork_calculateVacation() throws GlobalCustomException {
        Employee user = new Employee("Sekhar", 1000, EmployeeType.SALARIED);
        user.work(260);
        assertThat(user.getVacationDays()).isEqualTo(15);
        user.takeVacation(5.0);
        assertThat(user.getVacationDays()).isEqualTo(10);
    }

    @Test()
    public void whenTakingMoreVacationThanAccumulated_thenExpectationSatisfied() throws GlobalCustomException {
        Throwable e = null;

        Employee user = new Employee("Sekhar", 1000, EmployeeType.HOURLY);
        user.work(260);
        try {
            user.takeVacation(15.0);
        } catch (GlobalCustomException ex) {
            e = ex;
        }
        assertThat(e instanceof GlobalCustomException);
        assertThat(e.getMessage().equals("Invalid Vacation Days"));
    }

    @Test()
    public void whenUpdatingNegativeWorkDays_thenExpectationSatisfied() throws GlobalCustomException {
        Throwable e = null;

        Employee user = new Employee("Sekhar", 1000, EmployeeType.HOURLY);

        try {
            user.work(-1);
        } catch (GlobalCustomException ex) {
            e = ex;
        }
        assertThat(e instanceof GlobalCustomException);
        assertThat(e.getMessage().equals("Invalid Work Days"));
    }

    @Test()
    public void whenUpdatingWorkDaysMorethanMax_thenExpectationSatisfied() throws GlobalCustomException {
        Throwable e = null;

        Employee user = new Employee("Sekhar", 1000, EmployeeType.HOURLY);

        try {
            user.work(261);
        } catch (GlobalCustomException ex) {
            e = ex;
        }
        assertThat(e instanceof GlobalCustomException);
        assertThat(e.getMessage().equals("Invalid Work Days"));
    }
}
