package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.Services;
import com.Model.User;
import com.controller.MasterController;

public class ServicesMenu {
	 private final MasterController masterController;
	 private final Services service = new Services();
	    private static Scanner scanner;
	    public ServicesMenu()
	    {
	    	this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
	    }
	    
	    public void displayMenu(User user) throws SQLException {
	        while (true) {
	           
	            System.out.println("1) Add Services");
	            System.out.println("2) View Services");
	            System.out.println("3) Update Services");
	            System.out.println("4) Delete Services");
	            System.out.println("5) Exit");
	          
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                {
	                   masterController.servicesController.createService(service);
	                    break;
	                }
	                case 2:
	                {
	                	//masterController.servicesController.viewService(choice);
	                	break;
	                }
	                case 3:
	                {
	                	//masterController.servicesController.updateService(choice);
	                	break;
	                }
	                case 4:
	                {
	                	//masterController.servicesController.deleteService(choice);
	                	break;
	                	
	                }
	                case 5:
	                {
	                	return;
	                }
	               
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }

}
