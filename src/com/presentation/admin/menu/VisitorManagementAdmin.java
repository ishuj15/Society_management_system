package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class VisitorManagementAdmin {
	private  Scanner scanner;
	private MasterController masterController;
	public VisitorManagementAdmin()
	{
		this.masterController = new MasterController();
    	this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		while(true)
		{
			String str=
					"""
					1) View Visitor
					2) Delete Visitor
					3) Exit
					""";
			System.out.println(str);
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				masterController.visitorController.listVisitors() ;
				break;
			}
			case 2:
			{
				//masterController.visitorController.deleteVisitor(choice) ;
				break;
			}
			
			case 3:
				return;
				default:
					System.out.println("Invalid Choice , Please try again");
			}
		}
		
	
		
	}

}
