package com.smarthall.repository;

import com.smarthall.model.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComplaintRepository {

    public boolean saveComplaint(Complaint complaint) {

        String sql =
                "INSERT INTO complaints " +
                "(student_id, room_number, problem) " +
                "VALUES (?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    complaint.getStudentId()
            );

            statement.setString(
                    2,
                    complaint.getRoomNumber()
            );

            statement.setString(
                    3,
                    complaint.getProblem()
            );

            statement.executeUpdate();

            System.out.println(
                    "Complaint saved successfully!"
            );

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to save complaint!"
            );

            e.printStackTrace();

            return false;
        }
    }
}