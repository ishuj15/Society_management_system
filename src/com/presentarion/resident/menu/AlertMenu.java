package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Model.User;
import com.controller.AlertController;

import com.util.StringConstants;

public class AlertMenu {
	 private final AlertController alertController;
	    private  Scanner scanner;
	   
	    public AlertMenu()
	    {
	    	this.alertController = new AlertController();
	    	this.scanner = new Scanner(System.in);
	    }
	    public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
	        while (true) {
	           
	            System.out.println("1) View Alerts");
	            System.out.println("2) "+StringConstants.previousmenu);
	            System.out.println("3) "+StringConstants.logout);
	            System.out.println("4) "+StringConstants.enterChoice);
	            System.out.println("5) Exit");

	          
	            int choice;
	            try {
	    			choice= scanner.nextInt();
	    			}catch (InputMismatchException e) {
	                    System.out.println("Invalid input. Please enter a number.");
	                    scanner.nextLine(); 
	                    continue; }
	            switch (choice) {
	                case 1:
	                {
	                    alertController.viewAlertByRole(user.getUserRole()) ;
	                    
	                    break ;
	                }
	                
	                case 2:
	                    return true;
	                case 3:
	                	return false;
	                case 4:
	                {
	                	scanner.close();
	    				System.exit(0);
	    				return false;
	                }
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }

}
