package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentarion.resident.menu.ResidentController;
import com.util.Helper;
import com.util.str;

public class ResidentMenu {
	private Scanner scanner = new Scanner(System.in);

	private ResidentController residentController;

	public ResidentMenu() {
		this.residentController = new ResidentController();
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws SQLException, ClassNotFoundException, InterruptedException {
		boolean loggedIn = true;
		
		while (true) {
			Helper.printFunction(str.residentMenu);
			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(8, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
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
			{
				System.out.println(str.loggingout);
				Thread.sleep(1000);
				return;
			}
			case 8: {
				scanner.close();
				System.exit(0);
				return;
			}
			default:
				Helper.printFunction(str.invalidInput);
			}
			if (loggedIn == false)
			{
				System.out.println(str.loggingout);
				Thread.sleep(1000);
				return;

			}
		}
	}
}