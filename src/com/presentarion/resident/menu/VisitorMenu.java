package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.Helper;
import com.util.StringConstants;

public class VisitorMenu {
	private final MasterController masterController;
	private static Scanner scanner;

	@SuppressWarnings("static-access")
	public VisitorMenu() {
		this.masterController = new MasterController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
		while (true) {

			System.out.println("1) Add Visitor");
			System.out.println("2) View Visitor");
			System.out.println("3) Pending Requests");
			System.out.println("4) Update Visitor");
			System.out.println("5) Delete Visitor");
			System.out.println("6) " + StringConstants.previousmenu);
			System.out.println("7) " + StringConstants.logout);
			

			int choice=0;
			while(true)
			{
				System.out.println(StringConstants.enterChoice);

				choice= Helper.choiceInput();
				 if(Helper.checkLimit(7, choice))
					 break;	
				 System.out.println("Invalid input, Please try again");

			}


			switch (choice) {
			case 1: {
				masterController.visitorController.createVisitor(user, "Aprroved");
				break;
			}
			case 2: {
				masterController.visitorController.viewVisitor(user.getIdUser());
				break;
			}
			case 3: {
				masterController.visitorController.getAllPendingReq(user.getIdUser());
				break;
			}
			case 4: {
				masterController.visitorController.updateVisitor(user.getIdUser());
				break;
			}
			case 5: {
				masterController.visitorController.deleteVisitor(user.getIdUser());
			}
			case 6:
				return true;
			case 7:
				return false;
			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}
}
