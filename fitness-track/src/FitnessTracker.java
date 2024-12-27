package src;

import java.sql.*;
import java.util.Scanner;

public class FitnessTracker {
    public static void main(String[] args) {
        final String DB_URL = "jdbc:mysql://localhost:3306/fitness_tracker";
        final String DB_USER = "root";
        final String DB_PASSWORD = "yourpassword";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to database!");

            // Example database interaction
            System.out.print("Enter an activity name: ");
            String activity = scanner.nextLine();
            System.out.print("Enter duration (minutes): ");
            int duration = scanner.nextInt();

            String insertQuery = "INSERT INTO activity_log (activity_name, duration) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, activity);
                statement.setInt(2, duration);
                statement.executeUpdate();
                System.out.println("Activity logged successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}
