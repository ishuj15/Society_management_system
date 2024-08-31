package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;
import com.controller.UserController;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String str = """
				1) Create Account
				2) Login
				3) Logout
				4) Exit
				""";

		while (true) {
			System.out.println("Welcome to the Society Management Application");
			System.out.println(str);
			System.out.println("enter your choice");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				UserController.createUser();
				break;
			}
			case 2: {
				UserController.login();
				break;
			}
			case 3:
				break;
			case 4: {
				scanner.close();
				System.exit(0);
				return;
			}

			default:
				System.out.print("Wrong input");
			}

		}
	}

}
