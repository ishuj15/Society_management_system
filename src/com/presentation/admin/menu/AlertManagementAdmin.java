package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.AlertController;
import com.controller.MasterController;

public class AlertManagementAdmin {
	private  Scanner scanner;
	private AlertController alertController;
	public AlertManagementAdmin()
	{
		this.alertController = new AlertController();
    	this.scanner = new Scanner(System.in);
	}
	public void displayMenu() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		while(true)
		{
			String str=
					"""
					1) Add Alert
					2) Delete Alert
					3) View Alerts
					4) Update Alert
					5) Exit
					""";
			System.out.println(str);
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				alertController.createAlert();
				break;
			}
			case 2:
			{
				alertController.deleteAlert();
				break;
			}
			case 3:
			{
				alertController.listAlerts() ;
				break;
			}
			case 4:
			{
				alertController.updateAlert();
				break;
			}
			case 5:
				return;
				default:
					System.out.println("Invalid Choice , Please try again");
			}
		}
		
	
		
		
	}

}
