package com.smarthall;

import com.smarthall.ui.AdminMenu;
import com.smarthall.ui.StudentMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentMenu studentMenu = new StudentMenu(scanner);

        AdminMenu adminMenu = new AdminMenu(scanner);

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println(
                    "===== WELCOME TO BIJOY 24 HALL=====");

            System.out.println("1. Student");
            System.out.println("2. Admin");
            System.out.println("3. Exit");

            System.out.print(
                    "Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    studentMenu.showMenu();

                    break;

                case 2:

                    adminMenu.showMenu();

                    break;

                case 3:

                    System.out.println();
                    System.out.println(
                            "FAIRWELL FROM BIJOY 24 HALL!");

                    running = false;

                    break;

                default:

                    System.out.println();
                    System.out.println(
                            "Invalid choice!");
            }
        }

        scanner.close();
    }
}