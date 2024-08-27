package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

public class GuardMenu {

    private final GuardController guardController;
    private final Scanner scanner;

    public GuardMenu() {
        this.guardController = new GuardController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() throws SQLException {
        while (true) {
            System.out.println("Guard Options:");
            System.out.println("1) Profile Management");
            System.out.println("2) Attendance");
            System.out.println("3) Alerts");
            System.out.println("4) Notices");
            System.out.println("5) Visitor");
            System.out.println("6) Complaints");
            System.out.println("7) Exit");
            

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    guardController.viewAttendance();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
