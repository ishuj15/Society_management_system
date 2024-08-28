package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentarion.resident.menu.AlertMenu;
import com.presentarion.resident.menu.ComplaintMenu;
import com.presentarion.resident.menu.NoticeMenu;
import com.presentarion.resident.menu.UserManagementMenu;
import com.presentation.guard.menu.AttendanceMenu;
import com.presentation.guard.menu.VisitorMenu;


public class GuardMenu {
	private VisitorMenu visitorMenuObj;
    private final Scanner scanner;
    private NoticeMenu noticesMenuObj;
    private UserManagementMenu userManagementObj;
    private ComplaintMenu complaintMenuObj;
    private AlertMenu alertMenuObj;
    private AttendanceMenu attendanceMenuObj;

    public GuardMenu() {
    	 this.userManagementObj = new UserManagementMenu();
    	 this.visitorMenuObj = new VisitorMenu();
         this.noticesMenuObj = new NoticeMenu();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu(User user) throws SQLException {
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
                    userManagementObj.displayMenu(user);
                    break;
                }
                case 2:
                {
                	attendanceMenuObj.displayMenu(user);
                	break;
                }
                case 3:
                {
                	alertMenuObj.displayMenu(user);
                	break;
                }
                case 4:
                {
                	noticesMenuObj.displayMenu(user);
                	break;
                }
                case 5:
                {
                	visitorMenuObj.displayMenu(user);
                	break;
                }
                case 6:
                {
                	complaintMenuObj.displayMenu(user);
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
