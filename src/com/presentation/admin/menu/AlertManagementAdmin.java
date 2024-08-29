package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class AlertManagementAdmin {
	private  Scanner scanner;
	private MasterController masterController;
	public AlertManagementAdmin()
	{
		this.masterController = new MasterController();
    	this.scanner = new Scanner(System.in);
	}
	public void displayMenu(User user) throws SQLException {
		// TODO Auto-generated method stub
		
		while(true)
		{
			String str=
					"""
					1) Add Alert
					2) Delete Alert
					3) View Alerts
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
