package com.smarthall;

import com.smarthall.service.NoticeService;
import com.smarthall.exception.StudentNotRegisteredException;
import com.smarthall.model.Complaint;
import com.smarthall.service.ComplaintService;
import com.smarthall.model.Payment;
import com.smarthall.service.PaymentService;
import com.smarthall.model.RoomApplication;
import com.smarthall.service.RoomApplicationService;
import com.smarthall.model.Student;
import com.smarthall.service.StudentService;

//Admin
import com.smarthall.repository.AdminRepository;
import com.smarthall.model.Notice;
import com.smarthall.exception.AdminLoginException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Admin_Objects
        AdminRepository adminRepository = new AdminRepository();

        // Student_Objects_
        StudentService studentService = new StudentService();
        RoomApplicationService roomApplicationService = new RoomApplicationService();
        PaymentService paymentService = new PaymentService();
        ComplaintService complaintService = new ComplaintService();
        NoticeService noticeService = new NoticeService();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("===== WELCOME TO BIJOY 24 HALL=====");
            System.out.println("1. Student");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    boolean studentRunning = true;

                    while (studentRunning) {

                        System.out.println();
                        System.out.println("===== Student Menu =====");
                        System.out.println("1. Register");
                        System.out.println("2. Apply for Room");
                        System.out.println("3. Make Payment");
                        System.out.println("4. Submit Complaint");
                        System.out.println("5. View Notice");
                        System.out.println("6. Application Details");
                        System.out.println("7. Exit");
                        System.out.print("Enter your choice: ");

                        int studentChoice = scanner.nextInt();

                        switch (studentChoice) {

                            case 1:

                                scanner.nextLine();

                                System.out.println();
                                System.out.println("===== Student Registration =====");

                                System.out.print("Name: ");
                                String name = scanner.nextLine();

                                System.out.print("Email: ");
                                String email = scanner.nextLine();

                                System.out.print("Phone: ");
                                String phone = scanner.nextLine();

                                System.out.print("Student ID: ");
                                String studentId = scanner.nextLine();

                                System.out.print("Department: ");
                                String department = scanner.nextLine();

                                System.out.print("Session: ");
                                String session = scanner.nextLine();

                                Student student = studentService.registerStudent(
                                        name,
                                        email,
                                        phone,
                                        studentId,
                                        department,
                                        session);

                                if (student != null) {

                                    System.out.println();
                                    System.out.println("Student registration successful!");
                                    System.out.println("Student ID: " + student.getStudentId());

                                } else {

                                    System.out.println();
                                    System.out.println("Student registration failed!");
                                }

                                break;

                            case 2:

                                scanner.nextLine();

                                System.out.println();
                                System.out.println("===== Room Application =====");

                                System.out.print("Student ID: ");
                                String applicationStudentId = scanner.nextLine();

                                System.out.print("Preferred Room Number: ");
                                String preferredRoomNumber = scanner.nextLine();

                                System.out.print("Application Date (YYYY-MM-DD): ");
                                String applicationDate = scanner.nextLine();

                                try {

                                    RoomApplication application = roomApplicationService.applyForRoom(
                                            applicationStudentId,
                                            preferredRoomNumber,
                                            applicationDate);

                                    if (application != null) {

                                        System.out.println();
                                        System.out.println(
                                                "Room application successful!");

                                    } else {

                                        System.out.println();
                                        System.out.println(
                                                "Room application failed!");
                                    }

                                } catch (StudentNotRegisteredException e) {

                                    System.out.println();
                                    System.out.println(
                                            "Room application failed!");

                                    System.out.println(
                                            "Reason: " + e.getMessage());
                                }
                                break;

                            case 3:

                                scanner.nextLine();

                                System.out.println();
                                System.out.println("===== Payment =====");

                                System.out.print("Student ID: ");
                                String paymentStudentId = scanner.nextLine();

                                System.out.print("Semester: ");
                                int semester = scanner.nextInt();

                                System.out.print("Amount: ");
                                double amount = scanner.nextDouble();

                                try {

                                    Payment payment = paymentService.createPayment(
                                            paymentStudentId,
                                            semester,
                                            amount);

                                    if (payment != null) {

                                        System.out.println();
                                        System.out.println("Payment created successfully!");
                                        System.out.println(
                                                "Student ID: " + payment.getStudentId());
                                        System.out.println(
                                                "Semester: " + payment.getSemester());
                                        System.out.println(
                                                "Amount: " + payment.getAmount() + " Taka");
                                        System.out.println(
                                                "Status: " + payment.getStatus());

                                        System.out.print(
                                                "Do you want to make payment now? (yes/no): ");

                                        scanner.nextLine();
                                        String paymentChoice = scanner.nextLine();

                                        if (paymentChoice.equalsIgnoreCase("yes")) {

                                            boolean paid = paymentService.makePayment(payment);

                                            if (paid) {

                                                System.out.println();
                                                System.out.println(
                                                        "Payment completed successfully!");

                                                System.out.println(
                                                        "Payment status: "
                                                                + payment.getStatus());

                                            } else {

                                                System.out.println();
                                                System.out.println(
                                                        "Payment failed!");
                                            }

                                        } else {

                                            System.out.println();
                                            System.out.println(
                                                    "Payment remains DUE.");
                                        }

                                    } else {

                                        System.out.println();
                                        System.out.println(
                                                "Payment creation failed!");
                                    }

                                } catch (StudentNotRegisteredException e) {

                                    System.out.println();
                                    System.out.println(
                                            "Invalid payment, unregistered student!");
                                }

                                break;

                            case 4:

                                scanner.nextLine();

                                System.out.println();
                                System.out.println("===== Submit Complaint =====");

                                System.out.print("Student ID: ");
                                String complaintStudentId = scanner.nextLine();

                                System.out.print("Room Number: ");
                                String complaintRoomNumber = scanner.nextLine();

                                System.out.print("Problem: ");
                                String problem = scanner.nextLine();

                                try {

                                    Complaint complaint = complaintService.submitComplaint(
                                            complaintStudentId,
                                            complaintRoomNumber,
                                            problem);

                                    if (complaint != null) {

                                        System.out.println();
                                        System.out.println(
                                                "Complaint submitted successfully!");

                                        System.out.println(
                                                "Student ID: "
                                                        + complaint.getStudentId());

                                        System.out.println(
                                                "Room Number: "
                                                        + complaint.getRoomNumber());

                                        System.out.println(
                                                "Problem: "
                                                        + complaint.getProblem());

                                    } else {

                                        System.out.println();
                                        System.out.println(
                                                "Complaint submission failed!");
                                    }

                                } catch (StudentNotRegisteredException e) {

                                    System.out.println();
                                    System.out.println(
                                            "Invalid complaint, unregistered student!");
                                }

                                break;

                            case 5:

                                System.out.println();

                                noticeService.viewAllNotices();

                                break;

                            case 6:
                                scanner.nextLine();

                                System.out.println();
                                System.out.println("===== Application Details=====");

                                System.out.print("Enter Student ID: ");
                                String searchStudentId = scanner.nextLine();

                                try {

                                    RoomApplication application = roomApplicationService.searchApplication(
                                            searchStudentId);

                                    if (application != null) {

                                        System.out.println();
                                        System.out.println(
                                                "===== Room Application Details =====");

                                        System.out.println(
                                                "Student ID: "
                                                        + application.getStudentId());

                                        System.out.println(
                                                "Preferred Room: "
                                                        + application.getPreferredRoomNumber());

                                        System.out.println(
                                                "Application Date: "
                                                        + application.getApplicationDate());

                                        System.out.println(
                                                "Status: "
                                                        + application.getStatus());

                                    } else {

                                        System.out.println();
                                        System.out.println(
                                                "No room application found!");
                                    }

                                } catch (StudentNotRegisteredException e) {

                                    System.out.println();
                                    System.out.println(
                                            "Student is not registered!");
                                }
                                break;
                            case 7:
                                studentRunning = false;
                                break;
                            default:
                                System.out.println();
                                System.out.println("Invalid choice!");
                        }
                    }

                    break;

                case 2:

                    scanner.nextLine();

                    System.out.println();
                    System.out.println("===== Admin Login =====");

                    System.out.print("Email: ");
                    String adminEmail = scanner.nextLine();

                    System.out.print("Password: ");
                    String adminPassword = scanner.nextLine();

                    try {

                        boolean adminLoggedIn = adminRepository.login(
                                adminEmail,
                                adminPassword);

                        if (adminLoggedIn) {

                            System.out.println();
                            System.out.println("Admin login successful!");

                            boolean adminRunning = true;

                            while (adminRunning) {

                                System.out.println();
                                System.out.println("===== Admin Dashboard =====");
                                System.out.println("1. Approve Room Application");
                                System.out.println("2. Post Notice");
                                System.out.println("3. Exit");
                                System.out.print("Enter your choice: ");

                                int adminChoice = scanner.nextInt();

                                switch (adminChoice) {

                                    case 1:

                                        scanner.nextLine();

                                        System.out.println();
                                        System.out.println("===== Pending Room Applications =====");

                                        if (!roomApplicationService.hasPendingApplications()) {

                                            System.out.println();
                                            System.out.println(
                                                    "No pending room applications found.");

                                            System.out.println(
                                                    "Returning to Admin Dashboard...");

                                            break;
                                        }

                                        roomApplicationService.viewPendingApplications();

                                        System.out.println();
                                        System.out.print("Student ID: ");
                                        String actionStudentId = scanner.nextLine();

                                        System.out.print("Preferred Room Number: ");
                                        String actionRoomNumber = scanner.nextLine();

                                        System.out.println();
                                        System.out.println("1. Approve");
                                        System.out.println("2. Reject");
                                        System.out.print("Enter your choice: ");

                                        int actionChoice = scanner.nextInt();

                                        RoomApplication application = new RoomApplication(
                                                actionStudentId,
                                                actionRoomNumber,
                                                "");

                                        if (actionChoice == 1) {

                                            boolean approved = roomApplicationService.approveApplication(
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

                                            boolean rejected = roomApplicationService.rejectApplication(
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
                                            System.out.println("Invalid choice!");
                                        }

                                        break;

                                    case 2:

                                        scanner.nextLine();

                                        System.out.println();
                                        System.out.println("===== Post Notice =====");

                                        System.out.print("Notice: ");
                                        String noticeText = scanner.nextLine();

                                        System.out.print("Date (YYYY-MM-DD): ");
                                        String noticeDate = scanner.nextLine();

                                        Notice notice = noticeService.createNotice(
                                                noticeText,
                                                noticeDate);

                                        if (notice != null) {

                                            System.out.println();
                                            System.out.println("Notice posted successfully!");
                                            System.out.println(
                                                    "Notice: " + notice.getNoticeText());
                                            System.out.println(
                                                    "Date: " + notice.getDate());

                                        } else {

                                            System.out.println();
                                            System.out.println("Notice posting failed!");
                                        }

                                        break;

                                    case 3:
                                        adminRunning = false;
                                        break;

                                    default:
                                        System.out.println();
                                        System.out.println("Invalid choice!");
                                }
                            }

                        }

                    } catch (AdminLoginException e) {

                        System.out.println();
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    System.out.println();
                    System.out.println("FAIRWELL FROM BIJOY 24 HALL!");
                    running = false;
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}