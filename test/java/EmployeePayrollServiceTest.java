
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.assertTrue;

public class EmployeePayrollServiceTest {

    @Test
    public void retrieveEmployeePayrollTest() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readPayrollData(EmployeePayrollService.IOService.DB_IO);
        Assert.assertEquals(5, employeePayrollData.size());
    }

    @Test
    public void givenNewSalary_WhenUpdated_ShouldSyncWithDB() {
        EmployeePayrollService empPayRollService = new EmployeePayrollService();
        List<EmployeePayrollData> empPayrollList = empPayRollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        empPayRollService.updateEmployeeSalary("Navneet", 3000000.0);
        boolean isSynced = empPayRollService.isEmpPayrollSyncedWithDB("Navneet");
        assertTrue(isSynced);
    }

}