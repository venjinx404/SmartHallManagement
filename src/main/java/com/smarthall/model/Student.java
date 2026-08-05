package com.smarthall.model;

public class Student extends User {

    private String studentId;
    private String department;
    private String session;
    private boolean roomAssigned;

    public Student(String name, String email, String phone,
                   String studentId, String department, String session) {

        super(name, email, phone);

        this.studentId = studentId;
        this.department = department;
        this.session = session;
        this.roomAssigned = false;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public boolean isRoomAssigned() {
        return roomAssigned;
    }

    public void setRoomAssigned(boolean roomAssigned) {
        this.roomAssigned = roomAssigned;
    }

    @Override
    public void showDashboard() {
        System.out.println("\n===== Student Dashboard =====");
        System.out.println("Welcome, " + getName());
    }
}