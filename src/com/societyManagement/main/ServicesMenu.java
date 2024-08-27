package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.MasterController;

public class ServicesMenu {
	 private final MasterController masterController;
	    private final Scanner scanner;
	    public ServicesMenu()
	    {
	    	this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
	    }
	    
	    public void displayMenu() throws SQLException {
	        while (true) {
	           
	            System.out.println("1) Add Services");
	            System.out.println("2) List Services ");
	            System.out.println("3) Delete Services");
	            System.out.println("4) Update Services");
	            System.out.println("5) Give feedback");
	            System.out.println("6) Exit");
	          
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                {
	                    masterController.servicesController.addService();
	                    break;
	                }
	                case 2:
	                {
	                	masterController.userController.updateUser();
	                	break;	
	                }
	                case 3:
	                {
	                	masterController.servicesController.addService();
	                	break;
	                }
	                    return;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }

}
