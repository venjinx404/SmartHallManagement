package com.smarthall.service;

import com.smarthall.exception.StudentNotRegisteredException;
import com.smarthall.model.Complaint;
import com.smarthall.repository.ComplaintRepository;
import com.smarthall.repository.StudentRepository;

import java.util.ArrayList;

public class ComplaintService {

    private ComplaintRepository complaintRepository;
    private StudentRepository studentRepository;

    public ComplaintService() {

        complaintRepository = new ComplaintRepository();
        studentRepository = new StudentRepository();
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
                    "Invalid complaint, unregistered student!");
        }

        Complaint complaint = new Complaint(
                studentId,
                roomNumber,
                problem);

        boolean saved = complaintRepository.saveComplaint(complaint);

        if (!saved) {
            return null;
        }

        return complaint;
    }

    public ArrayList<Complaint> getUnsolvedComplaints() {

        return complaintRepository.getUnsolvedComplaints();
    }

    public boolean markComplaintAsSolved(String roomNumber) {

        if (roomNumber == null ||
                roomNumber.trim().isEmpty()) {

            return false;
        }

        return complaintRepository.markComplaintAsSolved(
                roomNumber);
    }
}