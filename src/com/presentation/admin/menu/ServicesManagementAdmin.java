package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;

import com.controller.ServicesController;
import com.util.StringConstants;

public class ServicesManagementAdmin {
	private  Scanner scanner;
	private ServicesController servicesController;
	public ServicesManagementAdmin()
	{
		this.servicesController = new ServicesController();
    	this.scanner = new Scanner(System.in);
	}
	public void displayMenu(User user) throws ClassNotFoundException, SQLException {
		while(true)
		{
			
			System.out.println(StringConstants.adminService);
			System.out.println(StringConstants.enterChoice);
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				servicesController.listServices();
				break;
			}
			case 2:
			{
				servicesController.deleteServiceByAdmin() ;
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
