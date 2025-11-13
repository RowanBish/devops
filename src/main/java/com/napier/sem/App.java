package com.napier.sem;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        Connection con = connectToDatabase();
        if (con != null) {
            System.out.println("SUCCESS: Connected to database!");
            // Do your database operations here
            try {
                // Test with a real query
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM employees LIMIT 5");
                System.out.println("Sample employees:");
                while (rs.next()) {
                    System.out.println(" - " + rs.getString("first_name") + " " + rs.getString("last_name"));
                }
                rs.close();
                stmt.close();

                con.close();
            } catch (SQLException e) {
                System.out.println("Error during database operations: " + e.getMessage());
            }
        } else {
            System.out.println("FAILED: Could not connect to database");
            System.exit(1);
        }
    }

    private static Connection connectToDatabase() {
        // CHANGE THIS LINE: Use 'localhost' instead of 'db'
        String url = "jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "example";

        int maxRetries = 10; // Reduced retries
        int retryDelay = 3000; // 3 seconds

        for (int i = 1; i <= maxRetries; i++) {
            try {
                System.out.println("Connection attempt " + i + " to: " + url);
                Connection connection = DriverManager.getConnection(url, user, password);

                // Test the connection
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1");
                if (rs.next()) {
                    System.out.println("Database connection test successful!");
                }
                rs.close();
                stmt.close();

                return connection;

            } catch (SQLException e) {
                System.out.println("Attempt " + i + " failed: " + e.getMessage());
                if (i < maxRetries) {
                    System.out.println("Retrying in " + (retryDelay/1000) + " seconds...");
                    try {
                        Thread.sleep(retryDelay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return null;
                    }
                }
            }
        }
        return null;
    }
}