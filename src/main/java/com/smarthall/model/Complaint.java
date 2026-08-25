package com.smarthall.model;

public class Complaint {

    private String studentId;
    private String roomNumber;
    private String problem;

    public Complaint(String studentId, String roomNumber, String problem) {
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.problem = problem;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}