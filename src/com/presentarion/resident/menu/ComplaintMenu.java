package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Model.Complaint;
import com.Model.User;
import com.controller.ComplaintController;
import com.controller.MasterController;
import com.util.StringConstants;

public class ComplaintMenu {
		private final Scanner scanner;
		  private final ComplaintController complaintController;
		 
		 // static int complaintId= Complaint.getIdComplaint();
		public ComplaintMenu()
		{
			this.complaintController = new ComplaintController();
	    	this.scanner = new Scanner(System.in);
		}
		public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
	        while (true) {
	            System.out.println("Complaint Menu");
	            System.out.println("1) Add Complaint");
	            System.out.println("2) View Complaint");
	            System.out.println("3) "+StringConstants.previousmenu);
	            System.out.println("4) "+StringConstants.logout);
	            System.out.println("5) Exit");

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
	                	Complaint complaint= new Complaint();
	                	complaint.setUserId(user.getIdUser());
	                   complaintController.createComplaint(complaint);
	                    break;
	                }
	                case 2:
	                {
	                	complaintController.viewComplaint(user.getIdUser());
	                	break;
	                }
	                case 3:
	                    return true;
	                case 4:
	                	return false;
	                case 5:
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