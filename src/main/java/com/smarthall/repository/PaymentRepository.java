package com.smarthall.repository;

import com.smarthall.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentRepository {

    public boolean savePayment(Payment payment) {

        String sql =
                "INSERT INTO payments " +
                "(student_id, semester, amount, status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    payment.getStudentId()
            );

            statement.setInt(
                    2,
                    payment.getSemester()
            );

            statement.setDouble(
                    3,
                    payment.getAmount()
            );

            statement.setString(
                    4,
                    payment.getStatus()
            );

            statement.executeUpdate();

            System.out.println(
                    "Payment saved successfully!"
            );

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to save payment!"
            );

            e.printStackTrace();

            return false;
        }
    }
}