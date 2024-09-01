package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;
import com.Model.User;
import com.presentation.guard.menu.GuardController;

public class GuardMenu {
	private Scanner scanner;
	private GuardController guardController;
    public GuardMenu() {
    	this.guardController=new GuardController();
        this.scanner = new Scanner(System.in);
    }
    public void displayMenu(User user) throws SQLException, ClassNotFoundException {
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
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                {
                    guardController.userManagementObj.displayMenu(user);
                    break;
                }
                case 2:
                {
                	guardController.attendanceMenuObj.displayMenu(user);
                	break;
                }
                case 3:
                {
                	guardController.alertMenuObj.displayMenu(user);
                	break;
                }
                case 4:
                {
                	guardController.noticesMenuObj.displayMenu(user);
                	break;
                }
                case 5:
                {
                	guardController.visitorMenuObj.displayMenu(user);
                	break;
                }
                case 6:
                {
                	guardController.complaintMenuObj.displayMenu(user);
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
