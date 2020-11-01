import java.time.LocalDate;

public class EmployeePayrollData {
    public int id;
    public String name;
    public double netpay;
    public LocalDate startDate;

    public EmployeePayrollData(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.netpay = salary;
    }

    public EmployeePayrollData(int id, String name, double netpay, LocalDate startDate) {
        this(id, name, netpay);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "id= " + id + ", name= " + name + ", netpay= " + netpay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return id == that.id && Double.compare(that.netpay,netpay) == 0 && name.equals(that.name);
    }
}