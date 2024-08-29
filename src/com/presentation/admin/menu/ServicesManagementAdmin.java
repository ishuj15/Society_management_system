package com.presentation.admin.menu;

import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class ServicesManagementAdmin {
	private  Scanner scanner;
	private MasterController masterController;
	public ServicesManagementAdmin()
	{
		this.masterController = new MasterController();
    	this.scanner = new Scanner(System.in);
	}
	public void displayMenu(User user) {
		// TODO Auto-generated method stub

		while(true)
		{
			String str=
					"""
					1) View Services
					2) Delete Services
					3) s
					4) Exit
					""";
			System.out.println(str);
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				masterController.alertController.createAlert();
				break;
			}
			case 2:
			{
				//masterController.noticesController.deleteNotice(choice);
				break;
			}
			case 3:
			{
				masterController.alertController.listAlerts() ;
				break;
			}
			case 4:
				return;
				default:
					System.out.println("Invalid Choice , Please try again");
			}
		}
		
		
	}

}
