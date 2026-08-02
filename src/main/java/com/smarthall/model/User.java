package com.smarthall.model;

public class User {
    private String name;
    private String email;
    private String phonenumber;

    User(String name, String email , String phonenumber)
    {
    
        this.name=name;
        this.email=email;
        this.phonenumber=phonenumber;
    }

    //getters
    public String getname()
    {
        return name;
    }

       public String getemail()
    {
        return email;
    }
       public String getphonenumber()
    {
        return phonenumber;
    }

    //setters
    public void setname(String name)
    {
        this.name=name;
    }

    public void setemail(String email)
    {
        this.email=email;
    }
    public void setphonenumber(String phonenumber)
    {
        this.phonenumber=phonenumber;
    }

}

