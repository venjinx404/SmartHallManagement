package com.smarthall.model;

public class Admin extends User {

    private String adminId;
    private String password;
    private String role;

    public Admin(String name, String email, String phone,
                 String adminId, String password, String role) {

        super(name, email, phone);

        this.adminId = adminId;
        this.password = password;
        this.role = role;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void showDashboard() {
        System.out.println("\n===== Admin Dashboard =====");
        System.out.println("Welcome, " + getName());
    }
}