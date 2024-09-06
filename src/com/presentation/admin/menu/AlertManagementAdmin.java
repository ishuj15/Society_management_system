package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.AlertController;
import com.util.Helper;
import com.util.str;

public class AlertManagementAdmin {
	private Scanner scanner;
	private AlertController alertController;

	public AlertManagementAdmin() {
		this.alertController = new AlertController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		while (true) {
			Helper.printFunction(str.adminAlert);
			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(7, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
				 System.out.println("Invalid User, Please try again");
			}

			switch (choice) {
			case 1: {
				alertController.createAlert();
				break;
			}
			case 2: {
				alertController.deleteAlert();
				break;
			}
			case 3: {
				alertController.listAlerts();
				break;
			}
			case 4: {
				alertController.updateAlert();
				break;
			}
			case 5:
				return true;
			case 6: {
				return false;
			}
			case 7: {
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
