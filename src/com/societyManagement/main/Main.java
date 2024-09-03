package com.societyManagement.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.controller.UserController;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String str = """
				1) Register new  Account
				2) Login
				3)  Exit
				""";
		 try (Scanner scanner = new Scanner(System.in)) {
		while (true) {
			System.out.println("Welcome to the Society Management Application");
			System.out.println(str);
			System.out.println("enter your choice");
			
			int choice;
			try {
			choice= scanner.nextInt();
			}catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue; }

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
				System.out.print("Wrong input");
						}	
				
		 	}
	}catch(Exception e) {
		 System.out.println("Incorrect input");

	 }
		 
		 
		 
	}
}
