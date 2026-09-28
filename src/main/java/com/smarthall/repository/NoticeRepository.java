package com.smarthall.repository;

import com.smarthall.model.Notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoticeRepository {

    public boolean saveNotice(Notice notice) {

        String sql =
                "INSERT INTO notices " +
                "(notice_text, date) " +
                "VALUES (?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    notice.getNoticeText()
            );

            statement.setString(
                    2,
                    notice.getDate()
            );

            statement.executeUpdate();

            System.out.println(
                    "Notice saved successfully!"
            );

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to save notice!"
            );

            e.printStackTrace();

            return false;
        }
    }
}