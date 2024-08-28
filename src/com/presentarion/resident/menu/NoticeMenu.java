package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class NoticeMenu {

	 private final MasterController masterController;
	    private static Scanner scanner;
	    
	    public NoticeMenu()
	    {
	    	this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
	    }
	    public void displayMenu(User user) throws SQLException {
	        while (true) {
	        	
	        	System.out.println("1) View Notices");
	            System.out.println("2) Exit");
	           
	          
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                {
	                	masterController.noticesController.listNotices() ; 
	                    break;
	                }
	                case 2:
	                {
	                	return;
	                }
	               
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }
}
