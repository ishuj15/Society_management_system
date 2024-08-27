package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminMenu {

    private final AdminController adminController;
    private final Scanner scanner;

    public AdminMenu() {
        this.adminController = new AdminController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() throws SQLException {
        while (true) {
            System.out.println("Admin Options:");
            System.out.println("1) Manage Notices\n2) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminController.manageNotices();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
