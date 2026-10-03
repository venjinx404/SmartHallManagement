package com.smarthall.model;

public class Complaint {

    private String studentId;
    private String roomNumber;
    private String problem;
    private String status;

    public Complaint(String studentId, String roomNumber, String problem) {
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.problem = problem;
        this.status = "Unsolved";
    }

    public Complaint(String studentId,
            String roomNumber,
            String problem,
            String status) {

        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.problem = problem;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}