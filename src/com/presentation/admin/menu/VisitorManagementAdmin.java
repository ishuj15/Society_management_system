package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.controller.VisitorController;
import com.util.StringConstants;

public class VisitorManagementAdmin {
	private  Scanner scanner;
	private VisitorController visitorController;
	public VisitorManagementAdmin()
	{
		this.visitorController = new VisitorController();
    	this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
		
		while(true)
		{
			
			System.out.println(StringConstants.adminVisitor);
			System.out.println("3) "+StringConstants.previousmenu);
	        System.out.println("4) "+StringConstants.logout);
	        System.out.println("5) Exit");
			System.out.println(StringConstants.enterChoice);
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				visitorController.listVisitors() ;
				break;
			}
			case 2:
			{
				
				visitorController.deleteVisitor(visitorController.DeleteUserByadmin());
			}
			case 3:
                return true;
            case 4:
            {
            	break;
            }
            case 5:
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
