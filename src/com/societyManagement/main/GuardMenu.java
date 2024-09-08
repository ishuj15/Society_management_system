package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentation.guard.menu.GuardController;
import com.util.Helper;
import com.util.str;

public class GuardMenu {
	private Scanner scanner;
	private GuardController guardController;

	public GuardMenu() {
		this.guardController = new GuardController();
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws SQLException, ClassNotFoundException, InterruptedException {
		boolean loggedIn = true;
		while (true) {
			
			Helper.printFunction(str.guarrdMenu);

			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(7, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
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
			{
				System.out.println(str.loggingout);
				Thread.sleep(1000);
				return;
			}
			case 7: {
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
