package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class VisitorMenu {
	 private final MasterController masterController;
	    private static Scanner scanner;
	    
	    @SuppressWarnings("static-access")
		public VisitorMenu()
	    {
	    	this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
	    }
	    public void displayMenu(User user) throws SQLException, ClassNotFoundException {
	        while (true) {
	           
	            System.out.println("1) Add Visitor");
	            System.out.println("2) View Visitor");
	            System.out.println("3) Pending Requests");
	            System.out.println("4) Update Visitor");
	            System.out.println("5) Delete Visitor");
	            System.out.println("6) Exit");
	          
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                {                	
	                   masterController.visitorController.createVisitor() ;
	                    break;
	                }
	                case 2:
	                {
	                	masterController.visitorController.viewVisitor(user.getIdUser()) ;
	                	break;	
	                }
	                case 3:
	                {
	                	masterController.visitorController.pendingRequests(user.getIdUser());
	                	break;
	                }
	                case 4:
	                {
	                	masterController.visitorController.updateVisitor(user.getIdUser());
	                	break;
	                }
	                case 5:
	                {
	                	masterController.visitorController.deleteVisitor(user.getIdUser());
	                }
	                case 6:
	                    return;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }
}
