package com.smarthall.service;

import com.smarthall.exception.StudentNotRegisteredException;
import com.smarthall.model.Complaint;
import com.smarthall.repository.ComplaintRepository;
import com.smarthall.repository.StudentRepository;

public class ComplaintService {

    private ComplaintRepository complaintRepository;
    private StudentRepository studentRepository;

    public ComplaintService() {
        complaintRepository = new ComplaintRepository();
        studentRepository=new StudentRepository();
    }

    // Create and save a new complaint
    public Complaint submitComplaint(String studentId,
                                     String roomNumber,
                                     String problem) {

        if (studentId == null ||
            roomNumber == null ||
            problem == null) {

            return null;
        }
         if (!studentRepository.existsByStudentId(studentId)) {

            throw new StudentNotRegisteredException(
                    "Invalid complaint, unregistered student!"
            );
        }

        Complaint complaint = new Complaint(
                studentId,
                roomNumber,
                problem
        );

        boolean saved =
                complaintRepository.saveComplaint(complaint);

        if (!saved) {
            return null;
        }

        return complaint;
    }
}