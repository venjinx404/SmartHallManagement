package com.smarthall.service;

import com.smarthall.model.RoomApplication;
import com.smarthall.repository.RoomApplicationRepository;
import com.smarthall.repository.StudentRepository;

public class RoomApplicationService {

    private RoomApplicationRepository roomApplicationRepository;
    private StudentRepository studentRepository;

    public RoomApplicationService() {
        roomApplicationRepository = new RoomApplicationRepository();
        studentRepository = new StudentRepository();
    }

    // Create and save a new room application
    public RoomApplication applyForRoom(String studentId,
            String preferredRoomNumber,
            String applicationDate) {

        if (studentId == null ||
                preferredRoomNumber == null ||
                applicationDate == null) {

            return null;
        }

        RoomApplication application = new RoomApplication(
                studentId,
                preferredRoomNumber,
                applicationDate);

        boolean saved = roomApplicationRepository.saveApplication(application);

        if (!saved) {
            return null;
        }

        return application;
    }

    // Approve a room application
    public boolean approveApplication(RoomApplication application) {

    if (application == null ||
        !application.isPending()) {

        return false;
    }

    application.approve();

    boolean statusUpdated =
            roomApplicationRepository.updateStatus(
                    application
            );

    if (!statusUpdated) {
        return false;
    }

    boolean roomAssigned =
            studentRepository.assignRoom(
                    application.getStudentId(),
                    application.getPreferredRoomNumber()
            );

    if (!roomAssigned) {
        return false;
    }

    return true;
}

    // Reject a room application
    public boolean rejectApplication(RoomApplication application) {

        if (application == null || !application.isPending()) {
            return false;
        }

        application.reject();

        return roomApplicationRepository.updateStatus(application);
    }

    public void viewPendingApplications() {

        roomApplicationRepository.displayPendingApplications();
    }

    public boolean hasPendingApplications() {

        return roomApplicationRepository.hasPendingApplications();
    }
}