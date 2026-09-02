package com.smarthall.service;

import com.smarthall.model.Room;
import com.smarthall.model.Student;

public class RoomService {

    // Check whether a room has vacancy
    public boolean checkVacancy(Room room) {

        if (room == null) {
            return false;
        }

        return !room.isFull();
    }


    // Assign a student to a room
    public boolean assignRoom(Student student, Room room) {

        if (student == null || room == null) {
            return false;
        }

        // Student already has a room
        if (student.isRoomAssigned()) {
            return false;
        }

        // Room is full
        if (room.isFull()) {
            return false;
        }

        // Add student to the room
        return room.addStudent(student);
    }


    // Change student's room
    public boolean changeRoom(Student student,
                              Room currentRoom,
                              Room newRoom) {

        if (student == null ||
            currentRoom == null ||
            newRoom == null) {

            return false;
        }

        // Student must already have a room
        if (!student.isRoomAssigned()) {
            return false;
        }

        // Cannot change to the same room
        if (currentRoom == newRoom) {
            return false;
        }

        // New room must have vacancy
        if (newRoom.isFull()) {
            return false;
        }

        // Remove from current room
        if (!currentRoom.removeStudent(student)) {
            return false;
        }

        // Add to new room
        return newRoom.addStudent(student);
    }


    // Leave the current room
    public boolean leaveRoom(Student student, Room room) {

        if (student == null || room == null) {
            return false;
        }

        // Student must have a room
        if (!student.isRoomAssigned()) {
            return false;
        }

        return room.removeStudent(student);
    }
}