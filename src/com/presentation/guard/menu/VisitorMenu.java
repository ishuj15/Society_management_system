package com.presentation.guard.menu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.StringConstants;

public class VisitorMenu {
	private final MasterController masterController;
	private Scanner scanner;
	public VisitorMenu() {

		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws ClassNotFoundException, SQLException {
				while(true)
		{
			System.out.println("1) Add Visitor");
			System.out.println("2) Verify Visitor");
			System.out.println("3) "+StringConstants.previousmenu);
			System.out.println("4) "+StringConstants.logout);
			System.out.println("5) Exit");
			System.out.println(StringConstants.enterChoice);

			int choice;
			try {
				choice= scanner.nextInt();
				}catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a number.");
	                scanner.nextLine(); 
	                continue; }
			switch(choice)
			{
			case 1:
			{
				User user2=masterController.userController.getUsernameList();
			//	System.out.println(user2.getIdUser());
				masterController.visitorController.createVisitor(user2,"Pending");
				System.out.println("Request sent to resident");
				break;
			}
			case 2:
			{
				User user2=masterController.userController.getUsernameList();
				masterController.visitorController.verifyVisitor(user2);
				break;
			}
			case 3:
				return true ;
			case 4:
				return false;
			case 5:
			{
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
