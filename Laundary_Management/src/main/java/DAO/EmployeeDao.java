package DAO;

import java.sql.*;
import model.Employee;

public class EmployeeDao {
    private Connection con;

    public EmployeeDao() {
        con = DBConnection.getConnection();
    }

    public Employee validateEmployee(String email, String password) {
        Employee emp = null;
        String sql = "SELECT * FROM employees WHERE email=? AND password=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    emp.setName(rs.getString("name"));
                    emp.setEmail(rs.getString("email"));
                    emp.setRole(rs.getString("role"));
                    emp.setPhone(rs.getString("phone"));
                    emp.setActive(rs.getBoolean("is_active"));
                }
            }
        } catch (SQLException e) {
            // log or rethrow depending on your policy; don't swallow silently
            e.printStackTrace();
        }
        return emp;
    }
}
