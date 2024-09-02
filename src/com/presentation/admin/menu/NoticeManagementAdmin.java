package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.StringConstants;

public class NoticeManagementAdmin {
	private  Scanner scanner;
	private MasterController masterController;
	public NoticeManagementAdmin()
	{
		this.masterController = new MasterController();
    	this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		while(true)
		{
			
			System.out.println(StringConstants.adminNotice);
			System.out.println(StringConstants.enterChoice);
			int choice = scanner.nextInt();
            scanner.nextLine(); 

			switch(choice)
			{
			case 1:
			{
				masterController.noticesController.createNotice();
				break;
			}
			case 2:
			{
				masterController.noticesController.deleteNotice();
				break;
			}
			case 3:
			{
				masterController.noticesController.listNotices();
				break;
			}
			case 4:
			{
				masterController.noticesController.updateNotice();
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
