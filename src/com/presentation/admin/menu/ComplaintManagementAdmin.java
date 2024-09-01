package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class ComplaintManagementAdmin {
	private  Scanner scanner;
	private MasterController masterController;
	public ComplaintManagementAdmin()
	{
		this.masterController = new MasterController();
    	this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		while(true)
		{
			String str=
					"""
					1) Update Complaint
					2) Delete Complaint
					3) View list of Complaint
					4) Exit
					""";
			System.out.println(str);
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				masterController.complaintController.updateComplaint();
				break;
			}
			case 2:
			{
				masterController.complaintController.deleteComplaintAdmin();
				break;
			}
			case 3:
			{
				masterController.complaintController.listComplaints();
				break;
			}
			
			case 4:
				return;
				default:
					System.out.println("Invalid Choice , Please try again");
			}
		}
		
	}

}
