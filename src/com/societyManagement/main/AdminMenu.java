package com.societyManagement.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.presentation.admin.menu.AdminController;
import com.util.Helper;
import com.util.str;

public class AdminMenu {
	private final AdminController adminController;
	private final Scanner scanner;

	public AdminMenu() {
		this.scanner = new Scanner(System.in);
		this.adminController = new AdminController();
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException, InterruptedException {
		boolean loggedIn = true;
		while (true) {
			Helper.printFunction(str.adminMenu);
			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(9, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
			}
			switch (choice) {
			case 1:

				loggedIn = adminController.accountManage.displayMenu(user);
				break;
			case 2: {
				loggedIn = adminController.noticeManage.displayMenu(user);
				break;
			}
			case 3: {
				loggedIn = adminController.alertManage.displayMenu();
				break;
			}
			case 4: {
				loggedIn = adminController.visitorManage.displayMenu(user);
				break;
			}
			case 5: {
				loggedIn=adminController.servicesManage.displayMenu(user);
				break;
			}
			case 6: {
				loggedIn=adminController.complaintManage.displayMenu(user);
				break;
			}
//                case 7:
//                {
//                	adminController.attendanceManage.displayMenu(user);
//                	break;
//                }
			case 7:
				return true;
			case 8: {
				System.out.println(str.loggingout);
				Thread.sleep(1000);
				return false;
			}
			case 9: {
				scanner.close();
				System.exit(0);
				return false;
			}
			default:
				 Helper.printFunction(str.invalidInput);
			}
			if (loggedIn == false)
			{
				System.out.println(str.loggingout);
				Thread.sleep(1000);

				return false;
				
			}
		}
	}
}
