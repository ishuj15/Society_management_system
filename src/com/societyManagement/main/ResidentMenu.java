package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentarion.resident.menu.ResidentController;
import com.util.Helper;
import com.util.StringConstants;

public class ResidentMenu {
	private Scanner scanner = new Scanner(System.in);

	private ResidentController residentController;

	public ResidentMenu() {
		this.residentController = new ResidentController();
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws SQLException, ClassNotFoundException {
		boolean loggedIn = true;
		// this.user=user;
		// User user=user;
		while (true) {

			System.out.println("1) " + StringConstants.account);
			System.out.println("2) " + StringConstants.notice);
			System.out.println("3) " + StringConstants.alert);
			System.out.println("4) " + StringConstants.visitor);
			System.out.println("5) " + StringConstants.service);
			System.out.println("6) " + StringConstants.complaint);
			System.out.println("7) " + StringConstants.logout);
					System.out.println("8) " + StringConstants.exit);

			int choice=0;
			while(true)
			{
				System.out.println(StringConstants.enterChoice);

				choice= Helper.choiceInput();
				 if(Helper.checkLimit(9, choice))
					 break;	
				 System.out.println("Invalid User, Please try again");

			}

			switch (choice) {
			case 1: {
				loggedIn = residentController.userManagementObj.displayMenu(user);
				break;
			}
			case 2: {
				loggedIn = residentController.noticesMenuObj.displayMenu(user);

				break;
			}
			case 3: {
				loggedIn = residentController.alertMenuObj.displayMenu(user);

				break;
			}
			case 4: {
				loggedIn = residentController.visitorMenuObj.displayMenu(user);
				break;
			}
			case 5: {
				loggedIn = residentController.servicesMenuObj.displayMenu(user);

				break;
			}
			case 6: {
				loggedIn = residentController.complaintMenuObj.displayMenu(user);
				break;
			}
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