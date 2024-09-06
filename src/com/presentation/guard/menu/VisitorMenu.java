package com.presentation.guard.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.Helper;
import com.util.str;

public class VisitorMenu {
	private final MasterController masterController;
	private Scanner scanner;

	public VisitorMenu() {

		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws ClassNotFoundException, SQLException {
		while (true) {
			Helper.printFunction(str.guardVisitorMenu);
			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(5, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
			}

			switch (choice) {
			case 1: {
				User user2 = masterController.userController.getUsernameList();
				
				masterController.visitorController.createVisitor(user2, "Pending");
				Helper.printFunction(str.requestSent);
			
				break;
			}
			case 2: {
				User user2 = masterController.userController.getUsernameList();
				masterController.visitorController.verifyVisitor(user2);
				break;
			}
			case 3:
				return true;
			case 4:
				return false;
			case 5: {
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
