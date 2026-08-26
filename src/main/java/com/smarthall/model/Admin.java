package com.smarthall.model;

public class Admin extends User {

   
    private String password;
    

    public Admin(String name, String email, String phone,
                  String password) {

        super(name, email, phone);

        
        this.password = password;
        
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    @Override
    public void showDashboard() {
        System.out.println("\n===== Admin Dashboard =====");
        System.out.println("Welcome, " + getName());
    }
}