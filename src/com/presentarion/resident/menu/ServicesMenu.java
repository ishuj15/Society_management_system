package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.Model.User;
import com.controller.ServicesController;
import com.util.StringConstants;

public class ServicesMenu {
	 private final ServicesController servicesController;
	 //private final Services service = new Services();
	    private  Scanner scanner;
	    public ServicesMenu()
	    {
	    	this.servicesController = new ServicesController();
	    	this.scanner = new Scanner(System.in);
	    }
	    
	    public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
	        while (true) {
	           
	            System.out.println("1) Add Services");
	            System.out.println("2) View Your Services");
	            System.out.println("3) Update Services");
	            System.out.println("4) Delete Services");
	            System.out.println("5) List of services");
	            System.out.println("6) "+StringConstants.previousmenu);
	            System.out.println("7) "+StringConstants.logout);
	            System.out.println(StringConstants.enterChoice);
	          
	            int choice ;
	            try {
	    			choice= scanner.nextInt();
	    			}catch (InputMismatchException e) {
	                    System.out.println("Invalid input. Please enter a number.");
	                    scanner.nextLine(); // clear the invalid input
	                    continue; }
	            switch (choice) {
	                case 1:
	                {
	                   servicesController.createService(user);
	                    break;
	                }
	                case 2:
	                {
	                	servicesController.viewService(user.getIdUser());
	                	break;
	                }
	                case 3:
	                {
	                	servicesController.updateService(user.getIdUser());
	                	break;
	                }
	                case 4:
	                {
	                	servicesController.deleteService(user.getIdUser());
	                	break;
	                	
	                }
	                case 5:
	                {
	                	servicesController.listServices();
	                	break;
	                }
	                case 6:
	                	return true ;
	                case 7:
	                	return false; 
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }

}
