package com.smarthall.repository;

import com.smarthall.exception.AdminLoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {

    public boolean login(String email, String password) {

        // check if email is In Admin
        String emailSql =
                "SELECT users.user_id " +
                "FROM users " +
                "JOIN admins ON users.user_id = admins.user_id " +
                "WHERE users.email = ? " +
                "AND users.role = 'ADMIN'";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement emailStatement =
                     connection.prepareStatement(emailSql)) {

            emailStatement.setString(1, email);

            ResultSet emailResult =
                    emailStatement.executeQuery();

            // Email does not exist
            if (!emailResult.next()) {

                throw new AdminLoginException(
                        "Wrong email! Re-enter email."
                );
            }

            // Email is correct, now check password
            String passwordSql =
                    "SELECT users.user_id " +
                    "FROM users " +
                    "JOIN admins ON users.user_id = admins.user_id " +
                    "WHERE users.email = ? " +
                    "AND admins.password = ? " +
                    "AND users.role = 'ADMIN'";

            try (PreparedStatement passwordStatement =
                         connection.prepareStatement(passwordSql)) {

                passwordStatement.setString(1, email);
                passwordStatement.setString(2, password);

                ResultSet passwordResult =
                        passwordStatement.executeQuery();

                if (!passwordResult.next()) {

                    throw new AdminLoginException(
                            "Wrong password! Re-enter password."
                    );
                }

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Admin login check failed!"
            );

            e.printStackTrace();

            return false;
        }
    }
}