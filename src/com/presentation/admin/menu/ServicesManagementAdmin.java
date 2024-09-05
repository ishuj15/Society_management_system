package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.ServicesController;
import com.util.Helper;
import com.util.StringConstants;

public class ServicesManagementAdmin {
	private Scanner scanner;
	private ServicesController servicesController;

	public ServicesManagementAdmin() {
		this.servicesController = new ServicesController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws ClassNotFoundException, SQLException {
		while (true) {

			System.out.println(StringConstants.adminService);
			System.out.println("3) " + StringConstants.previousmenu);
			System.out.println("4) " + StringConstants.logout);
			System.out.println("5) Exit");
			System.out.println(StringConstants.enterChoice);
			int choice=0;
			while(true)
			{
				System.out.println(StringConstants.enterChoice);

				choice= Helper.choiceInput();
				 if(Helper.checkLimit(5, choice))
					 break;	
				 System.out.println("Invalid User, Please try again");

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
				System.out.println("Invalid Choice , Please try again");
			}
		}
	}

}
