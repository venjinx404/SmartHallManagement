package com.smarthall.repository;

import com.smarthall.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomRepository {

    public boolean saveRoom(Room room) {

        String sql =
                "INSERT INTO rooms " +
                "(room_number, floor_number, capacity) " +
                "VALUES (?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, room.getRoomNumber());
            statement.setInt(2, room.getFloorNumber());
            statement.setInt(3, room.getCapacity());

            statement.executeUpdate();

            System.out.println("Room saved successfully!");

            return true;

        } catch (SQLException e) {

            System.out.println("Failed to save room!");
            e.printStackTrace();

            return false;
        }
    }

    public void initializeRooms() {

        String sql =
                "INSERT INTO rooms " +
                "(room_number, floor_number, capacity) " +
                "VALUES (?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            for (int floor = 1; floor <= 4; floor++) {

                for (int room = 1; room <= 30; room++) {

                    String roomNumber =
                            String.valueOf(floor * 100 + room);

                    statement.setString(1, roomNumber);
                    statement.setInt(2, floor);
                    statement.setInt(3, 4);

                    statement.addBatch();
                }
            }

            statement.executeBatch();

            System.out.println(
                    "120 rooms initialized successfully!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Failed to initialize rooms!"
            );

            e.printStackTrace();
        }
    }
}