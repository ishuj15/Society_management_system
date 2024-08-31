package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class UserManagementMenu {
	private final MasterController masterController;
	private Scanner scanner;

	// static int userId=User.getIdUser();
	public UserManagementMenu() {
		// this.user= new User();
		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws SQLException, ClassNotFoundException {
		while (true) {
			// System.out.println(user.getIdUser());

			System.out.println("1) Delete Profile");
			System.out.println("2) Update Profile");
			System.out.println("3) View Profile");
			System.out.println("4) Exit");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				masterController.userController.deleteUser(user);
				break;
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
				return;
			default:
				System.out.println("Invalid choice, please try again.");

			}
		}

	}

}
