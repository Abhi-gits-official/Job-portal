package com.jobportal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/job_portal?useSSL=false&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    static {
        try {
            // Load MySQL JDBC driver once when class is initialized.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("MySQL JDBC driver not found.", ex);
        }
    }

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static boolean insertApplication(
            String name,
            String email,
            String jobRole,
            String skills
    ) throws SQLException {
        String sql = "INSERT INTO applications "
                + "(name, email, job_role, skills) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, jobRole);
            statement.setString(4, skills);

            return statement.executeUpdate() > 0;
        }
    }

    public static List<Map<String, String>> searchApplications(String jobRole) throws SQLException {
        List<Map<String, String>> records = new ArrayList<>();
        String sql = "SELECT id, name, email, job_role, skills "
                + "FROM applications WHERE job_role = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, jobRole);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Keep result shape simple so JSP can render it directly.
                    Map<String, String> row = new HashMap<>();
                    row.put("id", String.valueOf(resultSet.getInt("id")));
                    row.put("name", resultSet.getString("name"));
                    row.put("email", resultSet.getString("email"));
                    row.put("job_role", resultSet.getString("job_role"));
                    row.put("skills", resultSet.getString("skills"));
                    records.add(row);
                }
            }
        }

        return records;
    }
}
