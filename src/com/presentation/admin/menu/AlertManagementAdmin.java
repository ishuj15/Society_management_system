package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.AlertController;

import com.util.StringConstants;

public class AlertManagementAdmin {
	private  Scanner scanner;
	private AlertController alertController;
	public AlertManagementAdmin()
	{
		this.alertController = new AlertController();
    	this.scanner = new Scanner(System.in);
	}
	public boolean displayMenu() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		while(true)
		{
			
			System.out.println(StringConstants.adminAlert);
			System.out.println("5) "+StringConstants.previousmenu);
	        System.out.println("6) "+StringConstants.logout);
	        System.out.println("7) Exit");
			System.out.println(StringConstants.enterChoice);
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
                return true;
            case 6:
            {
            	break;
            }
            case 7:
            {
            	scanner.close();
            	System.exit(0);
            	return false;
            }
				default:
					System.out.println("Invalid Choice , Please try again");
			}
		}
		
	
		
		
	}

}
