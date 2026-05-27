package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/laundarydb";
    private static final String USER = "postgres";
    private static final String PASS = "Abhay15"; // change to your password

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // fatal at startup
            throw new RuntimeException("Postgres JDBC Driver not found. Add the JDBC jar to classpath.", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            // Bubble up with a descriptive runtime exception so failures are visible
            throw new RuntimeException("Unable to connect to DB at " + URL + " user=" + USER, e);
        }
    }
}
