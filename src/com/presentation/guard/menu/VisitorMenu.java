package com.presentation.guard.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class VisitorMenu {
	private final MasterController masterController;
	private Scanner scanner;
	public VisitorMenu() {

		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		while(true)
		{
			String str= """
					1) Add Visitor
					2) Verify Visitor
					3) Exit
					""";
			System.out.println(str);
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice)
			{
			case 1:
			{
				masterController.visitorController.createVisitor();
				break;
			}
			case 2:
			{
				
				masterController.visitorController.verifyVisitor();
				break;
			}
			case 3:
				return ;
			default:
				System.out.println("Invalid choice, please try again.");
			}
			
		}
		
		
	}

}
