package com.smarthall.model;

import java.util.ArrayList;

public class Room {

    private String roomNumber;
    private int floorNumber;
    private int capacity;
    private ArrayList<Student> students;

    public Room(String roomNumber, int floorNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.capacity = 4;
        this.students = new ArrayList<>();
    }

    // Getters
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

    // Setters
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Add Student
    public boolean addStudent(Student student) {

        if (students.size() < capacity) {
            students.add(student);
            student.setRoomAssigned(true);
            return true;
        }

        return false;
    }

    // Remove Student
    public boolean removeStudent(Student student) {

        if (students.remove(student)) {
            student.setRoomAssigned(false);
            return true;
        }

        return false;
    }

    // Check Room Full
    public boolean isFull() {
        return students.size() >= capacity;
    }

    // Vacant Seat
    public int getAvailableSeat() {
        return capacity - students.size();
    }

    // Total Occupied
    public int getOccupiedSeat() {
        return students.size();
    }
}