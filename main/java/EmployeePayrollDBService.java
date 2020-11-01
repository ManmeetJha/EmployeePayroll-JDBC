import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class EmployeePayrollDBService {
    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM employee_payroll";
        List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
        try(Connection connection = this.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                double netPay = resultSet.getDouble("netPay");
                LocalDate startDate = resultSet.getDate("START_DATE").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, netPay,startDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "Monu@12783";
        Connection connection;
        System.out.println("Connecting to database: " + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection successful: " + connection);
        return connection;
    }
}