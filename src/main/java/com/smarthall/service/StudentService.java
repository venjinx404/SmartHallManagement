package com.smarthall.service;

import com.smarthall.model.Student;

public class StudentService {

    // Register a new student
    public Student registerStudent(String name,
                                   String email,
                                   String phone,
                                   String studentId,
                                   String department,
                                   String session) {

        if (name == null || email == null ||
            phone == null || studentId == null ||
            department == null || session == null) {

            return null;
        }

        Student student = new Student(
                name,
                email,
                phone,
                studentId,
                department,
                session
        );

        return student;
    }
}