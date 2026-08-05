package com.smarthall.model;

import java.util.ArrayList;

public class Room {

    private String roomNumber;
    private int floorNumber;
    private final int capacity;
    private ArrayList<Student> students;

    public Room(String roomNumber, int floorNumber) {

        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.capacity = 4;
        this.students = new ArrayList<>();
    }

    // ==========================
    // Getters
    // ==========================

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    // ==========================
    // Setters
    // ==========================

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    // ==========================
    // Business Methods
    // ==========================

    public boolean addStudent(Student student) {

        if (!isFull()) {

            students.add(student);
            student.setRoomAssigned(true);

            return true;
        }

        return false;
    }

    public boolean removeStudent(Student student) {

        if (students.remove(student)) {

            student.setRoomAssigned(false);

            return true;
        }

        return false;
    }

    public boolean isFull() {

        return students.size() >= capacity;
    }

    public int getAvailableSeats() {

        return capacity - students.size();
    }

    public int getOccupiedSeats() {

        return students.size();
    }
}