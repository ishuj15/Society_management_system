package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class NoticeManagementAdmin {
	private  Scanner scanner;
	private MasterController masterController;
	public NoticeManagementAdmin()
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
					1) Add Notice
					2) Delete Notice
					3) View list Notices
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
				masterController.noticesController.createNotice();
				break;
			}
			case 2:
			{
				//masterController.noticesController.deleteNotice(choice);
				break;
			}
			case 3:
			{
				masterController.noticesController.listNotices();
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
