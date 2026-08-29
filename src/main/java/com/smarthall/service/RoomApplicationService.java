package com.smarthall.service;

import com.smarthall.model.RoomApplication;

public class RoomApplicationService {

    // Create a new room application
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
                applicationDate
        );

        return application;
    }


    // Approve a room application
    public boolean approveApplication(RoomApplication application) {

        if (application == null || !application.isPending()) {
            return false;
        }

        application.approve();
        return true;
    }


    // Reject a room application
    public boolean rejectApplication(RoomApplication application) {

        if (application == null || !application.isPending()) {
            return false;
        }

        application.reject();
        return true;
    }
}