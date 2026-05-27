package DAO;

import model.Student;
import model.Hostel;
import java.sql.*;

public class StudentDao {

    // Get student by email & password (for login)
    public static Student getStudentByEmailPassword(String email, String password) throws SQLException {
        Student student = null;
        String sql = "SELECT s.student_id, s.name, s.email, s.password, "
                   + "h.hostel_name, h.hostel_block, h.laundry_block "
                   + "FROM students s "
                   + "LEFT JOIN hostels h ON s.hostel_id = h.hostel_id "
                   + "WHERE s.email = ? AND s.password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password); // for production: store hashed passwords

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));
                    student.setPassword(rs.getString("password"));

                    Hostel hostel = new Hostel();
                    hostel.setName(rs.getString("hostel_name"));
                    hostel.setBlock(rs.getString("hostel_block"));
                    hostel.setLaundryBlock(rs.getString("laundry_block"));

                    student.setHostel(hostel);
                }
            }
        }
        return student;
    }

    public static Student getStudentByEmail(String email) throws SQLException {
        Student student = null;
        String sql = "SELECT s.student_id, s.name, s.email, s.password, "
                   + "h.hostel_name, h.hostel_block, h.laundry_block "
                   + "FROM students s "
                   + "LEFT JOIN hostels h ON s.hostel_id = h.hostel_id "
                   + "WHERE s.email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));
                    student.setPassword(rs.getString("password"));

                    Hostel hostel = new Hostel();
                    hostel.setName(rs.getString("hostel_name"));
                    hostel.setBlock(rs.getString("hostel_block"));
                    hostel.setLaundryBlock(rs.getString("laundry_block"));

                    student.setHostel(hostel);
                }
            }
        }
        return student;
    }
}
