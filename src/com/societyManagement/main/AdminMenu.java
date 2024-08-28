package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentation.admin.NoticeManagementAdmin;
import com.presentation.admin.menu.AccontManagementAdmin;
import com.presentation.admin.menu.AlertManagementAdmin;
import com.presentation.admin.menu.AttendanceManagementAdmin;
import com.presentation.admin.menu.ComplaintManagementAdmin;
import com.presentation.admin.menu.ServicesManagementAdmin;
import com.presentation.admin.menu.VisitorManagementAdmin;

public class AdminMenu {

   // private final AdminController adminController;
    private final Scanner scanner;
    
    private AccontManagementAdmin accountManage;
    private NoticeManagementAdmin noticeManage;
    private AlertManagementAdmin alertManage;
    private VisitorManagementAdmin visitorManage;
    private ServicesManagementAdmin servicesManage;
    private ComplaintManagementAdmin complaintManage;
    private AttendanceManagementAdmin attendanceManage;
    
    

    public AdminMenu() {
       // this.adminController = new AdminController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu(User user) throws SQLException {
        while (true) {
            System.out.println("Admin Options:");
            System.out.println("1) Account Management");
            System.out.println("2) Notice Management");
            System.out.println("3) Alert Management");
            System.out.println("4) Visitor Management");
            System.out.println("5) Services Management");
            System.out.println("6) Complaint Management");
            System.out.println("7) Attendance Management");
            System.out.println("8)Exit");
            
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                   
                	accountManage.displayMenu(user);
                    break;
                case 2:{
                	noticeManage.displayMenu(user);
                	break;
                }
                case 3:
                {
                	alertManage.displayMenu(user);
                	break;
                }
                case 4:
                {
                	visitorManage.displayMenu(user);
                	break;
                }
                case 5:
                {
                	servicesManage.displayMenu(user);
                	break;
                }
                case 6:
                {
                	complaintManage.displayMenu(user);
                	break;
                }
                case 7:
                {
                	attendanceManage.displayMenu(user);
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
