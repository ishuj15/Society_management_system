package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.ServicesController;
import com.util.Helper;
import com.util.str;
public class ServicesMenu {
	private final ServicesController servicesController;
	private Scanner scanner;

	public ServicesMenu() {
		this.servicesController = new ServicesController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
		while (true) {

			Helper.printFunction(str.residentServices);
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
				servicesController.createService(user);
				break;
			}
			case 2: {
				servicesController.viewService(user.getIdUser());
				break;
			}
			case 3: {
				servicesController.updateService(user.getIdUser());
				break;
			}
			case 4: {
				servicesController.deleteService(user.getIdUser());
				break;

			}
			case 5: {
				servicesController.listServices();
				break;
			}
			case 6:
				return true;
			case 7:
				return false;
			case 8:
			{
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
