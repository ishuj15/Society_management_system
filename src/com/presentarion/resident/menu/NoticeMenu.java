package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.NoticesController;
import com.util.Helper;
import com.util.str;

public class NoticeMenu {

	private final NoticesController noticesController;
	private static Scanner scanner;

	public NoticeMenu() {
		this.noticesController = new NoticesController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
		while (true) {

			Helper.printFunction(str.userNotice);

			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(4, choice))
					 break;
				 Helper.printFunction(str.invalidInput);
			}

			switch (choice) {
			case 1: {
				noticesController.listNotices();
				break;
			}
			case 2: {
				return true;
			}
			case 3:
				return false;
			case 4: {
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
