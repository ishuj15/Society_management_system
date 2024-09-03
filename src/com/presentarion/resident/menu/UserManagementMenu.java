package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.StringConstants;

public class UserManagementMenu {
	private final MasterController masterController;
	private Scanner scanner;
	public UserManagementMenu() {

		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
		//boolean loggedIn;
		while (true ) {
			// System.out.println(user.getIdUser());

			System.out.println("1) Delete Profile");
			System.out.println("2) Update Profile");
			System.out.println("3) View Profile");
			System.out.println("4) "+StringConstants.previousmenu);
            System.out.println("5) "+StringConstants.logout);
            System.out.println("6) Exit");
            System.out.println(StringConstants.enterChoice);
           

			int choice;
			try {
				choice= scanner.nextInt();
				}catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a number.");
	                scanner.nextLine(); // clear the invalid input
	                continue; }

			switch (choice) {
			case 1: {
				masterController.userController.deleteUser(user);
				return false;
				
			}
			case 2: {
				masterController.userController.updateUser(user);
				break;
			}
			case 3: {
				masterController.userController.viewUser(user.getUserName());
				break;
			}
			case 4:
				return true;
			case 5:{
				
				return false;
			}
			case 6:{
				scanner.close();
				System.exit(0);
				return false;
			}
			default:
				System.out.println("Invalid choice, please try again.");

			}
		}

	}

}
