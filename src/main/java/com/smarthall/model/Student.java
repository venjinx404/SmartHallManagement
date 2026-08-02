package com.smarthall.model;

public class Student extends User {
    private String id;
    private String faculty;
    private String session;

    Student(String name, String email, String phonenumber,String id, String faculty, String session)
    {
        super(name, email, phonenumber);
        this.id=id;
        this.faculty=faculty;
        this.session=session;
    }
    public String getid()
    {
        return id;
    }
    public String getfaculty()
    {
        return faculty;
    }

    public String getsession()
    {
        return session;
    }
    public void setid(String id)
    {
        this.id=id;
    }
    public void setfaculty(String faculty)
    {
         this.faculty=faculty;
    }
    public void setsession(String session)
    {
        this.session=session;
    }
}
