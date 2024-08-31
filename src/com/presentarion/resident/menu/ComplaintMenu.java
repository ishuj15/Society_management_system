package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.Complaint;
import com.Model.User;
import com.controller.MasterController;

public class ComplaintMenu {
		private final Scanner scanner;
		  private final MasterController masterController;
		 
		 // static int complaintId= Complaint.getIdComplaint();
		public ComplaintMenu()
		{
			this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
		}
		public void displayMenu(User user) throws SQLException, ClassNotFoundException {
	        while (true) {
	        	
	        	//System.out.println(complaint.getUserId());
	            System.out.println("Complaint Menu");
	            System.out.println("1) Add Complaint");
	            System.out.println("2) View Complaint");
	            System.out.println("3) Delete Complaint");
	            System.out.println("4) Exit");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 
	            switch (choice) {
	                case 1:
	                {
	                	Complaint complaint= new Complaint();
	                	complaint.setUserId(user.getIdUser());
	                   masterController.complaintController.createComplaint(complaint);
	                    break;
	                }
	                case 2:
	                {
	                	masterController.complaintController.viewComplaint(user.getIdUser());
	                	break;
	                }
	                case 3:
	                {
	                	masterController.complaintController.deleteComplaint(user.getIdUser());
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