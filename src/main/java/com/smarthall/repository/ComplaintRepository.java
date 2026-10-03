package com.smarthall.repository;

import com.smarthall.model.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplaintRepository {

    // Save a new complaint
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


    // Get all unsolved complaints
    public ArrayList<Complaint> getUnsolvedComplaints() {

        ArrayList<Complaint> complaints =
                new ArrayList<>();

        String sql =
                "SELECT student_id, room_number, problem, status " +
                "FROM complaints " +
                "WHERE status = 'UNSOLVED' " +
                "ORDER BY complaint_id";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Complaint complaint =
                        new Complaint(
                                resultSet.getString("student_id"),
                                resultSet.getString("room_number"),
                                resultSet.getString("problem"),
                                resultSet.getString("status")
                        );

                complaints.add(complaint);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to load complaints!"
            );

            e.printStackTrace();
        }

        return complaints;
    }


    // Mark complaint as solved using room number
    public boolean markComplaintAsSolved(String roomNumber) {

        String sql =
                "UPDATE complaints " +
                "SET status = 'SOLVED' " +
                "WHERE room_number = ? " +
                "AND status = 'UNSOLVED'";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    roomNumber
            );

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update complaint!"
            );

            e.printStackTrace();

            return false;
        }
    }
}