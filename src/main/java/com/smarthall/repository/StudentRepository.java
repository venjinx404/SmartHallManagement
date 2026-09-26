package com.smarthall.repository;

import com.smarthall.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository {

    public boolean saveStudent(Student student, int userId) {

        String sql =
                "INSERT INTO students " +
                "(user_id, student_id, department, session, room_assigned) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

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
}