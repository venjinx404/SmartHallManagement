package com.smarthall.service;

import com.smarthall.model.Admin;
import com.smarthall.model.Student;
import com.smarthall.model.User;

public class AuthService {

    // Admin login verification
    public boolean authenticateAdmin(Admin admin,
                                     String name,
                                     String email,
                                     String phone,
                                     String password) {

        if (admin == null ||
            name == null ||
            email == null ||
            phone == null ||
            password == null) {

            return false;
        }

        return admin.getName().equals(name)
                && admin.getEmail().equals(email)
                && admin.getPhone().equals(phone)
                && admin.getPassword().equals(password);
    }


    // Student login verification
    public boolean authenticateStudent(Student student,
                                       String name,
                                       String studentId,
                                       String phone) {

        if (student == null ||
            name == null ||
            studentId == null ||
            phone == null) {

            return false;
        }

        return student.getName().equals(name)
                && student.getStudentId().equals(studentId)
                && student.getPhone().equals(phone);
    }


    // Check whether the user is an Admin
    public boolean isAdmin(User user) {
        return user instanceof Admin;
    }


    // Check whether the user is a Student
    public boolean isStudent(User user) {
        return user instanceof Student;
    }
}