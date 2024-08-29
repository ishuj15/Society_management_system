package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class VisitorMenu {
	 private final MasterController masterController;
	    private static Scanner scanner;
	    
	    public VisitorMenu()
	    {
	    	this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
	    }
	    public void displayMenu(User user) throws SQLException {
	        while (true) {
	           
	            System.out.println("1) Add Visitor");
	            System.out.println("2) View Visitor");
	            System.out.println("3) Exit");
	          
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                {
	                   masterController.visitorController.createVisitor() ;
	                    break;
	                }
	                case 2:
	                {
	                	masterController.visitorController.listVisitors() ;
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
