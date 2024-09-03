package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Model.User;

import com.controller.NoticesController;
import com.util.StringConstants;

public class NoticeMenu {

	 private final NoticesController noticesController;
	   private static Scanner scanner;
	    
	    public NoticeMenu()
	    {
	    	this.noticesController = new NoticesController();
	    	this.scanner = new Scanner(System.in);
	    }
	    public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
	        while (true) {
	        	
	        	System.out.println("1) View Notices");
	            System.out.println("2) "+ StringConstants.previousmenu);
	            System.out.println("3) "+StringConstants.logout);
	            System.out.println("4) Exit");
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
	                {
	                	noticesController.listNotices() ; 
	                    break;
	                }
	                case 2:
	                {
	                	return true;
	                }
	                case 3:
	                	return false;
	                case 4:
	                {
	                	scanner.close();
	    				System.exit(0);
	    				return false ;
	                }
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }
}
