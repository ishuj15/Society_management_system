package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.MasterController;

public class UserManagementMenu {
	 private final MasterController masterController;
	    private final Scanner scanner;
	    public UserManagementMenu()
	    {
	    	this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
	    }
	    
	    public void displayMenu() throws SQLException {
	        while (true) {
	           
	            System.out.println("1) Delete account");
	            System.out.println("2) Update Account");
	            System.out.println("3) Exit");
	          
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                {
	                    masterController.userController.deleteUser();
	                    break;
	                }
	                case 2:
	                {
	                	masterController.userController.updateUser();
	                	break;	
	                }
	                case 3:
	                    return;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }

}
