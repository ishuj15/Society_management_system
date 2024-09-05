package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.UserController;
import com.util.Helper;
import com.util.StringConstants;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String str = """
				1) Register new  Account
				2) Login
				3)  Exit""";
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println("Welcome to the Society Management Application");
				System.out.println(str);
				int choice = 0;
				while (true) {
					System.out.println(StringConstants.enterChoice);

					choice = Helper.choiceInput();
					if (Helper.checkLimit(3, choice))
						break;
					System.out.println("Invalid User, Please try again");
				}
				switch (choice) {
				case 1: {
					UserController.createUser();
					break;
				}
				case 2: {
					UserController.login();
					break;
				}
				case 3: {
					scanner.close();
					System.exit(0);
					return;
				}
				default:
					System.out.println("Wrong input");
				}
			}
		} catch (Exception e) {
			System.out.println("Incorrect input");

		}

	}
}
