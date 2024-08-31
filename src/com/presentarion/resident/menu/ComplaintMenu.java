package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.Complaint;
import com.Model.User;
import com.controller.MasterController;

public class ComplaintMenu {
		private final Scanner scanner;
		  private final MasterController masterController;
		 Complaint complaint= new Complaint();
		 // static int complaintId= Complaint.getIdComplaint();
		public ComplaintMenu()
		{
			this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
		}
		public void displayMenu(User user) throws SQLException, ClassNotFoundException {
	        while (true) {
	        	complaint.setUserId(user.getIdUser());
	        	System.out.println(complaint.getUserId());
	            System.out.println("Complaint Menu");
	            System.out.println("1) Add Complaint");
	            System.out.println("2) View Complaint");
	            System.out.println("3) Delete Complaint");
	            System.out.println("4) Exit");
	            Complaint complaint = new Complaint();

	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                {
	                   masterController.complaintController.createComplaint(user.getIdUser(),complaint);
	                    break;
	                }
	                case 2:
	                {
	                	masterController.complaintController.viewComplaint(user.getIdUser());
	                	break;
	                }
	                case 3:
	                {
	                	masterController.complaintController.deleteComplaint(complaint.getUserId());
	                	break;
	                }
	                case 4:
	                    return;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    }
		
	

}
