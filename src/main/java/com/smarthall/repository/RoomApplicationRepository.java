package com.smarthall.repository;

import com.smarthall.model.RoomApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomApplicationRepository {

    public boolean saveApplication(RoomApplication application) {

        String sql =
                "INSERT INTO room_applications " +
                "(student_id, preferred_room_number, application_date, status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    application.getStudentId()
            );

            statement.setString(
                    2,
                    application.getPreferredRoomNumber()
            );

            statement.setString(
                    3,
                    application.getApplicationDate()
            );

            statement.setString(
                    4,
                    application.getStatus()
            );

            statement.executeUpdate();

            System.out.println(
                    "Room application saved successfully!"
            );

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to save room application!"
            );

            e.printStackTrace();

            return false;
        }
    }
}