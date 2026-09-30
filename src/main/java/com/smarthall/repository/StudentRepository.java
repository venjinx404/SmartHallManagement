package com.smarthall.repository;

import com.smarthall.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {

        public boolean saveStudent(Student student, int userId) {

                String sql = "INSERT INTO students " +
                                "(user_id, student_id, department, session, room_assigned) " +
                                "VALUES (?, ?, ?, ?, ?)";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setInt(1, userId);
                        statement.setString(2, student.getStudentId());
                        statement.setString(3, student.getDepartment());
                        statement.setString(4, student.getSession());
                        statement.setBoolean(5, student.isRoomAssigned());

                        statement.executeUpdate();

                        System.out.println("Student saved successfully!");

                        return true;

                } catch (SQLException e) {

                        System.out.println("Failed to save student!");
                        e.printStackTrace();

                        return false;
                }
        }

        public boolean assignRoom(String studentId,
                        String roomNumber) {

                String sql = "UPDATE students " +
                                "SET room_assigned = TRUE, " +
                                "room_number = ? " +
                                "WHERE student_id = ? " +
                                "AND room_assigned = FALSE";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(1, roomNumber);
                        statement.setString(2, studentId);

                        int rowsUpdated = statement.executeUpdate();

                        if (rowsUpdated > 0) {

                                System.out.println(
                                                "Room assigned to student successfully!");

                                return true;
                        }

                        System.out.println(
                                        "Student already has a room or student was not found!");

                        return false;

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to assign room to student!");

                        e.printStackTrace();

                        return false;
                }
        }

        public boolean existsByStudentId(String studentId) {

                String sql = "SELECT student_id " +
                                "FROM students " +
                                "WHERE student_id = ?";

                try (Connection connection = DatabaseConnection.getConnection();
                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(1, studentId);

                        ResultSet resultSet = statement.executeQuery();

                        return resultSet.next();

                } catch (SQLException e) {

                        System.out.println(
                                        "Failed to check student!");

                        e.printStackTrace();

                        return false;
                }
        }
}