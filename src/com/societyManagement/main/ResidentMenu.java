package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;
import com.Model.User;
import com.presentarion.resident.menu.*;

public class ResidentMenu {
private static Scanner scanner =new Scanner(System.in);

private ResidentController residentController;
   public ResidentMenu() {
       this.residentController= new ResidentController();
        this.scanner = new Scanner(System.in);
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
        			 7) Logout
    				 """;
           
            System.out.println(str);
            System.out.println("Enter your choice");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                {
                    residentController.userManagementObj.displayMenu(user);
                    break;
                }
                case 2:
                {
                	residentController.servicesMenuObj.displayMenu(user);
                	break;
                }
                case 3:
                {
                	residentController.visitorMenuObj.displayMenu(user);
                	break;
                }
                case 4:
                {
                	residentController.noticesMenuObj.displayMenu(user);
                	break;
                }
                case 5:
                {
                	residentController.alertMenuObj.displayMenu(user);
                	break;
                }
                case 6:
                {
                	residentController.complaintMenuObj.displayMenu(user) ;
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