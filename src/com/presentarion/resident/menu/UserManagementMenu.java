package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class UserManagementMenu {
	 private final MasterController masterController;
	    private final Scanner scanner;
	    static int userId=User.getIdUser();
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
	                    masterController.userController.deleteUser(userId);
	                    break;
	                }
	                case 2:
	                {
	                	masterController.userController.updateUser(userId);
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
