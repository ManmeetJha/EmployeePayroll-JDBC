import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EmployeePayrollService {


    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    private List<EmployeePayrollData> employeePayrollList;
    private EmployeePayrollDBService employeePayrollDBService;

    public EmployeePayrollService() {
        employeePayrollDBService = EmployeePayrollDBService.getInstance();
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this();
        this.employeePayrollList = employeePayrollList;

    }

    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayRollService = new EmployeePayrollService(employeePayrollList);
        Scanner sc = new Scanner(System.in);
        employeePayRollService.readData(sc);
        // employeePayRollService.writeData();
    }

    public void readData(Scanner sc) {
        System.out.println("Enter ID:");
        int id = sc.nextInt();
        System.out.println("Enter Name:");
        String name = sc.next();
        System.out.println("Enter Salary:");
        double salary = sc.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public void writeData(IOService ioService) {
        if (ioService.equals(EmployeePayrollService.IOService.CONSOLE_IO))
            System.out.println(employeePayrollList);
       // else if (ioService.equals(EmployeePayrollService.IOService.FILE_IO))
          //  new EmployeePayrollFileIOService().writeData2(employeePayrollList);
    }

    public void printData(IOService ioService) {
        if (ioService.equals(EmployeePayrollService.IOService.FILE_IO))
            new EmployeePayrollFileIOService().printData();
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(EmployeePayrollService.IOService.FILE_IO))
            return new EmployeePayrollFileIOService().countEntries();
        return 0;

    }

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            this.employeePayrollList = new EmployeePayrollDBService().readData();
        return this.employeePayrollList;
    }

    public void updateEmployeeSalary(String name, double salary) {
        int result = employeePayrollDBService.updateSalaryUsingSQL(name, salary);
        EmployeePayrollData employeePayrollData = getEmployeePayrollData(name);
        if (result != 0 && employeePayrollData != null)
            employeePayrollData.netpay = salary;
    }

    public boolean isEmpPayrollSyncedWithDB(String name) {
        try {
            return employeePayrollDBService.getEmployeePayrollDatas(name).get(0).equals(getEmployeePayrollData(name));
        } catch (IndexOutOfBoundsException e) {
        }
        return false;
    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        return employeePayrollList.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    public List<EmployeePayrollData> readPayrollData(IOService ioService) {
       /* if (ioService.equals(IOService.FILE_IO))
            this.employeePayrollList = new EmployeePayrollFileIOService().readData();*/
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollList = new EmployeePayrollDBService().readData();
        return employeePayrollList;
    }

}