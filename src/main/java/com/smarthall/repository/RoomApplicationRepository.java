package com.smarthall.repository;

import com.smarthall.model.RoomApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomApplicationRepository {

        public boolean saveApplication(RoomApplication application) {

                String sql = "INSERT INTO room_applications " +
                                "(student_id, preferred_room_number, application_date, status) " +
                                "VALUES (?, ?, ?, ?)";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(
                                        1,
                                        application.getStudentId());

                        statement.setString(
                                        2,
                                        application.getPreferredRoomNumber());

                        statement.setString(
                                        3,
                                        application.getApplicationDate());

                        statement.setString(
                                        4,
                                        application.getStatus());

                        statement.executeUpdate();

                        System.out.println(
                                        "Room application saved successfully!");

                        return true;

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to save room application!");

                        e.printStackTrace();

                        return false;
                }
        }

        public boolean updateStatus(RoomApplication application) {

                String sql = "UPDATE room_applications " +
                                "SET status = ? " +
                                "WHERE student_id = ? " +
                                "AND preferred_room_number = ? " +
                                "AND status = 'PENDING'";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(
                                        1,
                                        application.getStatus());

                        statement.setString(
                                        2,
                                        application.getStudentId());

                        statement.setString(
                                        3,
                                        application.getPreferredRoomNumber());

                        int rowsUpdated = statement.executeUpdate();

                        if (rowsUpdated > 0) {
                                System.out.println(
                                                "Room application status updated successfully!");
                                return true;
                        }

                        return false;

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to update room application status!");

                        e.printStackTrace();

                        return false;
                }
        }

        public boolean hasPendingApplications() {

                String sql = "SELECT COUNT(*) " +
                                "FROM room_applications " +
                                "WHERE status = 'PENDING'";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                                return resultSet.getInt(1) > 0;
                        }

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to check pending applications!");

                        e.printStackTrace();
                }

                return false;
        }

        public void displayPendingApplications() {

                String sql = "SELECT student_id, preferred_room_number, " +
                                "application_date, status " +
                                "FROM room_applications " +
                                "WHERE status = 'PENDING' " +
                                "ORDER BY application_id";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        ResultSet resultSet = statement.executeQuery();

                        System.out.println();
                        System.out.println("===== Pending Room Applications =====");

                        boolean found = false;

                        while (resultSet.next()) {

                                found = true;

                                System.out.println();
                                System.out.println(
                                                "Student ID: "
                                                                + resultSet.getString("student_id"));

                                System.out.println(
                                                "Preferred Room: "
                                                                + resultSet.getString("preferred_room_number"));

                                System.out.println(
                                                "Application Date: "
                                                                + resultSet.getString("application_date"));

                                System.out.println(
                                                "Status: "
                                                                + resultSet.getString("status"));
                        }

                        if (!found) {
                                System.out.println();
                                System.out.println("No pending applications found.");
                        }

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to load pending applications!");

                        e.printStackTrace();
                }
        }

        public RoomApplication findByStudentId(String studentId) {

                String sql = "SELECT student_id, preferred_room_number, " +
                                "application_date, status " +
                                "FROM room_applications " +
                                "WHERE student_id = ? " +
                                "ORDER BY application_id DESC " +
                                "LIMIT 1";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(
                                        1,
                                        studentId);

                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {

                                RoomApplication application = new RoomApplication(
                                                resultSet.getString("student_id"),
                                                resultSet.getString("preferred_room_number"),
                                                resultSet.getString("application_date"));

                                String status = resultSet.getString("status");

                                if ("APPROVED".equals(status)) {

                                        application.approve();

                                } else if ("REJECTED".equals(status)) {

                                        application.reject();
                                }

                                return application;
                        }

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to search room application!");

                        e.printStackTrace();
                }

                return null;
        }
}