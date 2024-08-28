package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentarion.resident.menu.*;
public class ResidentMenu {
private static Scanner scanner =new Scanner(System.in);
//User user;
private UserManagementMenu userManagementObj;
private VisitorMenu visitorMenuObj;
private ServicesMenu servicesMenuObj;
private AlertMenu alertMenuObj;
private NoticeMenu noticesMenuObj;
private ComplaintMenu complaintMenuObj;
       

   public ResidentMenu() {
       this.userManagementObj = new UserManagementMenu();
       this.servicesMenuObj = new ServicesMenu();
       this.visitorMenuObj = new VisitorMenu();
       this.noticesMenuObj = new NoticeMenu();
       this.alertMenuObj = new AlertMenu();
       this.complaintMenuObj = new ComplaintMenu();
       
        this.scanner = new Scanner(System.in);
        //this.user= new User();
    }

    public   void displayMenu(User user) throws SQLException {
    	//this.user=user;
    	//User user=user;
        while (true) {
        	String str= """
        			Resident Options:
        			 1) User Management
        			 2) Services
        			 3) Visitor Management
        			 4) Notices
        			 5) Alert
        			 6) Complaints
        			 7) Exit
    				 """;
           
            System.out.println(str);
            System.out.println("Enter your choice");
            

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                {
                    userManagementObj.displayMenu(user);
                    break;
                }
                case 2:
                {
                	servicesMenuObj.displayMenu(user);
                	break;
                }
                case 3:
                {
                	visitorMenuObj.displayMenu(user);
                	break;
                }
                case 4:
                {
                	noticesMenuObj.displayMenu(user);
                	break;
                }
                case 5:
                {
                	alertMenuObj.displayMenu(user);
                	break;
                }
                case 6:
                {
                	complaintMenuObj.displayMenu(user) ;
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
