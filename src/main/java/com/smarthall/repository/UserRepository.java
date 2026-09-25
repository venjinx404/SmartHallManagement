package com.smarthall.repository;

import com.smarthall.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public int saveUser(User user, String role) {

        String sql =
                "INSERT INTO users (name, email, phone, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             sql,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, role);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {

                int userId = resultSet.getInt(1);

                System.out.println("User saved successfully!");
                System.out.println("Generated User ID: " + userId);

                return userId;
            }

        } catch (SQLException e) {

            System.out.println("Failed to save user!");
            e.printStackTrace();
        }

        return -1;
    }
}