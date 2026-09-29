package com.smarthall.repository;

import com.smarthall.model.Notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public void displayAllNotices() {

    String sql =
            "SELECT notice_text, date " +
            "FROM notices " +
            "ORDER BY notice_id DESC";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        ResultSet resultSet =
                statement.executeQuery();

        System.out.println();
        System.out.println("===== Notices =====");

        while (resultSet.next()) {

            String noticeText =
                    resultSet.getString("notice_text");

            String date =
                    resultSet.getString("date");

            System.out.println();
            System.out.println("Notice: " + noticeText);
            System.out.println("Date: " + date);
        }

    } catch (SQLException e) {

        System.out.println("Failed to load notices!");
        e.printStackTrace();
    }
}
}