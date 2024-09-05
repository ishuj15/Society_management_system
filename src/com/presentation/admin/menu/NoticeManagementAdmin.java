package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.Helper;
import com.util.StringConstants;

public class NoticeManagementAdmin {
	private Scanner scanner;
	private MasterController masterController;

	public NoticeManagementAdmin() {
		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {

		while (true) {

			System.out.println(StringConstants.adminNotice);
			System.out.println("5) " + StringConstants.previousmenu);
			System.out.println("6) " + StringConstants.logout);
			System.out.println("7) Exit");
			System.out.println(StringConstants.enterChoice);
			int choice=0;
			while(true)
			{
				System.out.println(StringConstants.enterChoice);

				choice= Helper.choiceInput();
				 if(Helper.checkLimit(7, choice))
					 break;	
				 System.out.println("Invalid User, Please try again");

			}

			switch (choice) {
			case 1: {
				masterController.noticesController.createNotice();
				break;
			}
			case 2: {
				masterController.noticesController.deleteNotice();
				break;
			}
			case 3: {
				masterController.noticesController.listNotices();
				break;
			}
			case 4: {
				masterController.noticesController.updateNotice();
				break;
			}
			case 5:
				return true;
			case 6: {
				break;
			}
			case 7: {
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
