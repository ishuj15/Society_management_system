package com.societyManagement.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.Model.User;
import com.presentation.admin.menu.AdminController;
import com.util.StringConstants;

public class AdminMenu {
    private final AdminController adminController;
    private final Scanner scanner;
    
    public AdminMenu() {
        this.scanner = new Scanner(System.in);
        this.adminController= new AdminController();   
    }

    public void displayMenu(User user) throws SQLException, ClassNotFoundException {
    	boolean loggedIn=true;
        while (true) {
        	
            System.out.println("1) "+ StringConstants.user);
            System.out.println("2) "+StringConstants.notice);
            System.out.println("3) "+StringConstants.alert);
            System.out.println("4) "+StringConstants.visitor);
            System.out.println("5) "+StringConstants.service);
            System.out.println("6) "+StringConstants.complaint);
           // System.out.println("7) "+StringConstants.attendance);
            System.out.println("7) "+StringConstants.previousmenu);
            System.out.println("8) "+StringConstants.logout);
            System.out.println("9) Exit");

            System.out.println(StringConstants.enterChoice);
            int choice;
            try {
    			choice= scanner.nextInt();
    			}catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // clear the invalid input
                    continue; } 

            switch (choice) {
                case 1:
                   
                	loggedIn=adminController.accountManage.displayMenu(user);
                    break;
                case 2:{
                	loggedIn=adminController.noticeManage.displayMenu(user);
                	break;
                }
                case 3:
                {
                	loggedIn= adminController.alertManage.displayMenu();
                	break;
                }
                case 4:
                {
                	loggedIn= adminController.visitorManage.displayMenu(user);
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
//                case 7:
//                {
//                	adminController.attendanceManage.displayMenu(user);
//                	break;
//                }
                case 7:
                    return;
                case 8:
                {
                	break;
                }
                case 9:
                {
                	scanner.close();
                	System.exit(0);
                	return false;
                }
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            if(loggedIn==false)
            	return;
        }
    }
}
