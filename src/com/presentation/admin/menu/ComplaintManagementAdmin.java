package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.ComplaintController;
import com.util.Helper;
import com.util.StringConstants;

public class ComplaintManagementAdmin {
	private Scanner scanner;
	private ComplaintController complaintController;

	public ComplaintManagementAdmin() {
		this.complaintController = new ComplaintController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println(StringConstants.adminComplaint);
			System.out.println("4) " + StringConstants.previousmenu);
			System.out.println("5) " + StringConstants.logout);
			System.out.println("6) Exit");
			System.out.println(StringConstants.enterChoice);
			int choice=0;
			while(true)
			{
				System.out.println(StringConstants.enterChoice);

				choice= Helper.choiceInput();
				 if(Helper.checkLimit(6, choice))
					 break;	
				 System.out.println("Invalid User, Please try again");

			}

			switch (choice) {
			case 1: {
				complaintController.updateComplaint();
				break;
			}
			case 2: {
				complaintController.deleteComplaintAdmin();
				break;
			}
			case 3: {
				complaintController.listComplaints();
				break;
			}
			case 4:
				return true;
			case 5: {
				return false;

			}
			case 6: {
				scanner.close();
				System.exit(0);
				return false;
			}
			default:
				System.out.println("Invalid Choice , Please try again");
			}
		}

	}

}
