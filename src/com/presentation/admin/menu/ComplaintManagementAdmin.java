package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.ComplaintController;

import com.util.StringConstants;

public class ComplaintManagementAdmin {
	private  Scanner scanner;
	private ComplaintController complaintController;
	public ComplaintManagementAdmin()
	{
		this.complaintController = new ComplaintController();
    	this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		while(true)
		{
			
			System.out.println(StringConstants.adminComplaint);
			System.out.println(StringConstants.enterChoice);
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				complaintController.updateComplaint();
				break;
			}
			case 2:
			{
				complaintController.deleteComplaintAdmin();
				break;
			}
			case 3:
			{
				complaintController.listComplaints();
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
