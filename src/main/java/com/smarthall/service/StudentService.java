package com.smarthall.service;

import com.smarthall.model.Student;
import com.smarthall.repository.StudentRepository;
import com.smarthall.repository.UserRepository;

public class StudentService {

    private UserRepository userRepository;
    private StudentRepository studentRepository;

    public StudentService() {
        userRepository = new UserRepository();
        studentRepository = new StudentRepository();
    }

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

        // student object
        Student student = new Student(
                name,
                email,
                phone,
                studentId,
                department,
                session);

        int userId = userRepository.saveUser(student, "STUDENT");

        if (userId == -1) {
            return null;
        }

        // Save student information
        boolean saved = studentRepository.saveStudent(student, userId);

        if (!saved) {
            return null;
        }

        return student;
    }
}