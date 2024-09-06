package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.ComplaintController;
import com.util.Helper;
import com.util.str;

public class ComplaintManagementAdmin {
	private Scanner scanner;
	private ComplaintController complaintController;

	public ComplaintManagementAdmin() {
		this.complaintController = new ComplaintController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws ClassNotFoundException, SQLException {
		while (true) {
			Helper.printFunction(str.adminComplaint);
			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(6, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
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
				 Helper.printFunction(str.invalidInput);
			}
		}

	}

}
