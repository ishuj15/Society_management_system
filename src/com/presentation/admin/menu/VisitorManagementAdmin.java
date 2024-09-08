package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.VisitorController;
import com.util.Helper;
import com.util.str;

public class VisitorManagementAdmin {
	private Scanner scanner;
	private VisitorController visitorController;

	public VisitorManagementAdmin() {
		this.visitorController = new VisitorController();
		this.scanner = new Scanner(System.in);
	}
	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {

		while (true) {

			Helper.printFunction(str.adminVisitor);
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
				visitorController.listVisitors();
				break;
			}
			case 2: {

				visitorController.deleteVisitorByAdmin();
				break;
			}
			case 3:
				return true;
			case 4: {
				return false;
			}
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
