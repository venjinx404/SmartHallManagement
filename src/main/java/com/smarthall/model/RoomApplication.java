package com.smarthall.model;

public class RoomApplication {

    private String studentId;
    private String preferredRoomNumber;
    private String applicationDate;
    private String status;

    public RoomApplication(String studentId, String preferredRoomNumber, String applicationDate) {
        this.studentId = studentId;
        this.preferredRoomNumber = preferredRoomNumber;
        this.applicationDate = applicationDate;
        this.status = "PENDING";
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPreferredRoomNumber() {
        return preferredRoomNumber;
    }

    public void setPreferredRoomNumber(String preferredRoomNumber) {
        this.preferredRoomNumber = preferredRoomNumber;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void approve() {
        status = "APPROVED";
    }

    public void reject() {
        status = "REJECTED";
    }

    public boolean isPending() {
        return status.equals("PENDING");
    }
}
