package com.smarthall.ui;

import com.smarthall.exception.AdminLoginException;
import com.smarthall.model.Complaint;
import com.smarthall.model.Notice;
import com.smarthall.model.RoomApplication;
import com.smarthall.repository.AdminRepository;
import com.smarthall.service.ComplaintService;
import com.smarthall.service.NoticeService;
import com.smarthall.service.RoomApplicationService;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {

    private AdminRepository adminRepository;
    private RoomApplicationService roomApplicationService;
    private ComplaintService complaintService;
    private NoticeService noticeService;
    private Scanner scanner;

    public AdminMenu(Scanner scanner) {

        adminRepository = new AdminRepository();
        roomApplicationService = new RoomApplicationService();
        complaintService = new ComplaintService();
        noticeService = new NoticeService();

        this.scanner = scanner;
    }

    public void showMenu() {

        scanner.nextLine();

        boolean adminLoggedIn = false;

        while (!adminLoggedIn) {

            System.out.println();
            System.out.println("===== Admin Login =====");

            System.out.print("Email: ");
            String adminEmail = scanner.nextLine();

            System.out.print("Password: ");
            String adminPassword = scanner.nextLine();

            try {

                adminLoggedIn = adminRepository.login(
                        adminEmail,
                        adminPassword);

                if (adminLoggedIn) {

                    System.out.println();
                    System.out.println(
                            "Admin login successful!");

                            

                    boolean adminRunning = true;

                    while (adminRunning) {

                        System.out.println();
                        System.out.println(
                                "===== Admin Dashboard =====");

                        System.out.println(
                                "1. Approve Room Application");

                        System.out.println(
                                "2. Post Notice");

                        System.out.println(
                                "3. View Complaints");

                        System.out.println(
                                "4. Exit");

                        System.out.print(
                                "Enter your choice: ");

                        int adminChoice = scanner.nextInt();

                        switch (adminChoice) {

                            case 1:

                                scanner.nextLine();

                                System.out.println();
                                System.out.println(
                                        "===== Pending Room Applications =====");

                                if (!roomApplicationService
                                        .hasPendingApplications()) {

                                    System.out.println();
                                    System.out.println(
                                            "No pending room applications found.");

                                    System.out.println(
                                            "Returning to Admin Dashboard...");

                                    break;
                                }

                                roomApplicationService
                                        .viewPendingApplications();

                                System.out.println();

                                System.out.print(
                                        "Student ID: ");

                                String actionStudentId = scanner.nextLine();

                                System.out.print(
                                        "Preferred Room Number: ");

                                String actionRoomNumber = scanner.nextLine();

                                System.out.println();
                                System.out.println(
                                        "1. Approve");

                                System.out.println(
                                        "2. Reject");

                                System.out.print(
                                        "Enter your choice: ");

                                int actionChoice = scanner.nextInt();

                                RoomApplication application = new RoomApplication(
                                        actionStudentId,
                                        actionRoomNumber,
                                        "");

                                if (actionChoice == 1) {

                                    boolean approved = roomApplicationService
                                            .approveApplication(
                                                    application);

                                    if (approved) {

                                        System.out.println();
                                        System.out.println(
                                                "Room application approved successfully!");

                                    } else {

                                        System.out.println();
                                        System.out.println(
                                                "Room application approval failed!");
                                    }

                                } else if (actionChoice == 2) {

                                    boolean rejected = roomApplicationService
                                            .rejectApplication(
                                                    application);

                                    if (rejected) {

                                        System.out.println();
                                        System.out.println(
                                                "Room application rejected successfully!");

                                    } else {

                                        System.out.println();
                                        System.out.println(
                                                "Room application rejection failed!");
                                    }

                                } else {

                                    System.out.println();
                                    System.out.println(
                                            "Invalid choice!");
                                }

                                break;

                            case 2:

                                scanner.nextLine();

                                System.out.println();
                                System.out.println(
                                        "===== Post Notice =====");

                                System.out.print(
                                        "Notice: ");

                                String noticeText = scanner.nextLine();

                                System.out.print(
                                        "Date (YYYY-MM-DD): ");

                                String noticeDate = scanner.nextLine();

                                Notice notice = noticeService.createNotice(
                                        noticeText,
                                        noticeDate);

                                if (notice != null) {

                                    System.out.println();
                                    System.out.println(
                                            "Notice posted successfully!");

                                    System.out.println(
                                            "Notice: "
                                                    + notice
                                                            .getNoticeText());

                                    System.out.println(
                                            "Date: "
                                                    + notice.getDate());

                                } else {

                                    System.out.println();
                                    System.out.println(
                                            "Notice posting failed!");
                                }

                                break;

                            case 3:

                                scanner.nextLine();

                                System.out.println();
                                System.out.println(
                                        "===== Complaints =====");

                                ArrayList<Complaint> complaints = complaintService
                                        .getUnsolvedComplaints();

                                if (complaints.isEmpty()) {

                                    System.out.println(
                                            "No Unsolved problem");

                                } else {

                                    for (Complaint complaint : complaints) {

                                        System.out.println();

                                        System.out.println(
                                                "Student ID: "
                                                        + complaint
                                                                .getStudentId());

                                        System.out.println(
                                                "Room Number: "
                                                        + complaint
                                                                .getRoomNumber());

                                        System.out.println(
                                                "Problem: "
                                                        + complaint
                                                                .getProblem());

                                        System.out.println(
                                                "Status: "
                                                        + complaint
                                                                .getStatus());
                                    }

                                    System.out.println();

                                    System.out.print(
                                            "Enter Room Number to mark as solved "
                                                    + "(0 to return): ");

                                    String roomNumber = scanner.nextLine();

                                    if (!roomNumber.equals("0")) {

                                        boolean solved = complaintService
                                                .markComplaintAsSolved(
                                                        roomNumber);

                                        if (solved) {

                                            System.out.println();
                                            System.out.println(
                                                    "Complaint solved successfully!");

                                        } else {

                                            System.out.println();
                                            System.out.println(
                                                    "No unsolved complaint found "
                                                            + "for this room.");
                                        }
                                    }
                                }

                                break;

                            case 4:

                                System.out.println();
                                System.out.println(
                                        "Admin logged out.");

                                adminRunning = false;

                                break;

                            default:

                                System.out.println();
                                System.out.println(
                                        "Invalid choice!");
                        }
                    }
                }

            } catch (AdminLoginException e) {

                System.out.println();
                System.out.println(
                        e.getMessage());

                System.out.println();
                System.out.println(
                        "Please try logging in again.");
            }
        }
    }
}