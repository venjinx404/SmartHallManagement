package com.smarthall.service;

import com.smarthall.model.Complaint;
import com.smarthall.repository.ComplaintRepository;

public class ComplaintService {

    private ComplaintRepository complaintRepository;

    public ComplaintService() {
        complaintRepository = new ComplaintRepository();
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