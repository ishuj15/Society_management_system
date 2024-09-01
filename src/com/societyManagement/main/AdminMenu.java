package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;
import com.Model.User;
import com.presentation.admin.menu.AdminController;

public class AdminMenu {
    private final AdminController adminController;
    private final Scanner scanner;
    
    public AdminMenu() {
        this.scanner = new Scanner(System.in);
        this.adminController= new AdminController();   
    }

    public void displayMenu(User user) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("Admin Options:");
            System.out.println("1) Account 	  Management");
            System.out.println("2) Notice  	  Management");
            System.out.println("3) Alert   	  Management");
            System.out.println("4) Visitor 	  Management");
            System.out.println("5) Services   Management");
            System.out.println("6) Complaint  Management");
            System.out.println("7) Attendance Management");
            System.out.println("8)Exit");
            
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                   
                	adminController.accountManage.displayMenu(user);
                    break;
                case 2:{
                	adminController.noticeManage.displayMenu(user);
                	break;
                }
                case 3:
                {
                	adminController.alertManage.displayMenu();
                	break;
                }
                case 4:
                {
                	adminController.visitorManage.displayMenu(user);
                	break;
                }
                case 5:
                {
                	adminController.servicesManage.displayMenu(user);
                	break;
                }
                case 6:
                {
                	adminController.complaintManage.displayMenu(user);
                	break;
                }
                case 7:
                {
                	adminController.attendanceManage.displayMenu(user);
                	break;
                }
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
