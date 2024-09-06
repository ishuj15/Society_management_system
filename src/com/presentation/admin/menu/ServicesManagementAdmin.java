package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.ServicesController;
import com.util.Helper;
import com.util.str;

public class ServicesManagementAdmin {
	private Scanner scanner;
	private ServicesController servicesController;

	public ServicesManagementAdmin() {
		this.servicesController = new ServicesController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws ClassNotFoundException, SQLException {
		while (true) {
			Helper.printFunction(str.adminService);

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
				servicesController.listServices();
				break;
			}
			case 2: {
				servicesController.deleteServiceByAdmin();
				break;
			}
			case 3:
				return true;
			case 4: {
				break;
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
