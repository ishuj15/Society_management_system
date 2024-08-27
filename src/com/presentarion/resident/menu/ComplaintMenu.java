package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.Complaint;
import com.Model.User;
import com.controller.MasterController;

public class ComplaintMenu {
	  private final Scanner scanner;
	  private final MasterController masterController;
	  static int userId=User.getIdUser();
	  static int complaintId= Complaint.getIdComplaint();
	public ComplaintMenu()
	{
		this.masterController = new MasterController();
    	this.scanner = new Scanner(System.in);
	}
	public void displayMenu() throws SQLException {
        while (true) {
            System.out.println("Complaint Menu");
            System.out.println("1) Add Complaint");
            System.out.println("2) View Complaint");
            System.out.println("3) Delete Complaint");
            System.out.println("4) Exit");
            

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                {
                   masterController.complaintController.createComplaint();
                    break;
                }
                case 2:
                {
                	masterController.complaintController.viewComplaint(complaintId);
                	break;
                }
                case 3:
                {
                	//masterController.complaintController.deleteComplaint();
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
