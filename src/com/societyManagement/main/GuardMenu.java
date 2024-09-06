package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentation.guard.menu.GuardController;
import com.util.Helper;
import com.util.StringConstants;

public class GuardMenu {
	private Scanner scanner;
	private GuardController guardController;

	public GuardMenu() {
		this.guardController = new GuardController();
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws SQLException, ClassNotFoundException {
		boolean loggedIn = true;
		while (true) {
			// System.out.println("Guard Options:");
			System.out.println("1) " + StringConstants.account);
			System.out.println("2) " + StringConstants.alert);
			System.out.println("3) " + StringConstants.notice);
			System.out.println("4) " + StringConstants.visitor);
			System.out.println("5) " + StringConstants.complaint);
			System.out.println("6) " + StringConstants.previousmenu);
			System.out.println("7) " + StringConstants.logout);
			System.out.println("8) Exit");
			

			int choice=0;
			while(true)
			{
				System.out.println(StringConstants.enterChoice);

				choice= Helper.choiceInput();
				 if(Helper.checkLimit(8, choice))
					 break;	
				 System.out.println("Invalid User, Please try again");

			}


			switch (choice) {
			case 1: {
				loggedIn = guardController.userManagementObj.displayMenu(user);
				break;
			}
			case 2: {
				loggedIn = guardController.alertMenuObj.displayMenu(user);
				break;
			}
			case 3: {
				loggedIn = guardController.noticesMenuObj.displayMenu(user);
				break;
			}
			case 4: {
				loggedIn = guardController.visitorMenuObj.displayMenu(user);
				break;
			}
			case 5: {
				loggedIn = guardController.complaintMenuObj.displayMenu(user);
				break;
			}
			case 6:
				return;
			case 7:
				return;
			case 8: {
				scanner.close();
				System.exit(0);
				return;
			}
			default:
				System.out.println("Invalid choice, please try again.");
			}
			if (loggedIn == false)
				return;
		}
	}
}
