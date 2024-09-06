package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.Helper;
import com.util.str;

public class NoticeManagementAdmin {
	private Scanner scanner;
	private MasterController masterController;

	public NoticeManagementAdmin() {
		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {

		while (true) {

			Helper.printFunction(str.adminNotice);
			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(7, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
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
				 Helper.printFunction(str.invalidInput);
			}
		}

	}

}