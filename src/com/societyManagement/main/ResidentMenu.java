package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentarion.resident.menu.*;
public class ResidentMenu {
	private final UserManagementMenu userManagementObj;
	private final ServicesMenu servicesObj;
	private final VisitorMenu visitorObj;
	private final NoticeMenu noticesObj;
	private final AlertMenu alertObj;
	private final ComplaintMenu complaintObj;
	//private final ServicesMenu servicesObj;
       private final Scanner scanner;
       

    public ResidentMenu() {
        this.userManagementObj = new UserManagementMenu();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() throws SQLException {
        while (true) {
            System.out.println("Resident Options:");
            System.out.println("1) User Management");
            System.out.println("2) Services");
            System.out.println("3) Visitor Management");
            System.out.println("4) Notices");
            System.out.println("5) Alert");
            System.out.println("6) Complaints");
            System.out.println("7) Exit");
            

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                {
                    userManagementObj.displayMenu();
                    break;
                }
                case 2:
                {
                	servicesObj.displayMenu();
                	break;
                }
                case 3:
                {
                	visitorObj.displayMenu();
                	break;
                }
                case 4:
                {
                	noticesObj.displayMenu();
                	break;
                }
                case 5:
                {
                	alertObj.displayMenu();
                	break;
                }
                case 6:
                {
                	complaintObj.displayMenu() ;
                	break;
                }
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
