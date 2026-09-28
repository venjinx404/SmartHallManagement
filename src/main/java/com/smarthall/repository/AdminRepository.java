package com.smarthall.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {

    public boolean login(String email, String password) {

        String sql =
                "SELECT users.user_id " +
                "FROM users " +
                "JOIN admins ON users.user_id = admins.user_id " +
                "WHERE users.email = ? " +
                "AND admins.password = ? " +
                "AND users.role = 'ADMIN'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {

            System.out.println("Admin login check failed!");
            e.printStackTrace();

            return false;
        }
    }
}