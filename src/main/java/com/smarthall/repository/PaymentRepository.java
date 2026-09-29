package com.smarthall.repository;

import com.smarthall.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentRepository {

        public boolean savePayment(Payment payment) {

                String sql = "INSERT INTO payments " +
                                "(student_id, semester, amount, status) " +
                                "VALUES (?, ?, ?, ?)";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(
                                        1,
                                        payment.getStudentId());

                        statement.setInt(
                                        2,
                                        payment.getSemester());

                        statement.setDouble(
                                        3,
                                        payment.getAmount());

                        statement.setString(
                                        4,
                                        payment.getStatus());

                        statement.executeUpdate();

                        System.out.println(
                                        "Payment saved successfully!");

                        return true;

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to save payment!");

                        e.printStackTrace();

                        return false;
                }
        }
        public boolean updateStatus(Payment payment) {

    String sql =
            "UPDATE payments " +
            "SET status = ? " +
            "WHERE student_id = ? " +
            "AND semester = ? " +
            "AND status = 'DUE'";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setString(
                1,
                payment.getStatus()
        );

        statement.setString(
                2,
                payment.getStudentId()
        );

        statement.setInt(
                3,
                payment.getSemester()
        );

        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {

            System.out.println(
                    "Payment status updated successfully!"
            );

            return true;
        }

        return false;

    } catch (SQLException e) {

        System.out.println(
                "Failed to update payment status!"
        );

        e.printStackTrace();

        return false;
    }
}
}