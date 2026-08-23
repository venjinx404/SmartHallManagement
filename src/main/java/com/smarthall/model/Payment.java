package com.smarthall.model;

public class Payment {

    private String studentId;
    private int semester;
    private double amount;
    private String status;

    public Payment(String studentId, int semester, double amount) {

        this.studentId = studentId;
        this.semester = semester;
        this.amount = amount;
        this.status = "DUE";
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void makePayment() {
        status = "PAID";
    }

    public boolean isDue() {  
        return status.equals("DUE");
    }

    public void displayPaymentInfo() {

        System.out.println("Student ID: " + studentId);
        System.out.println("Semester: " + semester);
        System.out.println("Amount: " + amount + " Taka");
        System.out.println("Status: " + status);
    }
}