package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.util.Helper;
import com.util.str;
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
			Helper.printFunction(str.residentVisitorMenu);
			int choice=0;
			while(true)
			{
				Helper.printFunction(str.enterChoice);
				choice= Helper.choiceInput();
				 if(Helper.checkLimit(8, choice))
					 break;	
				 Helper.printFunction(str.invalidInput);
			}
			switch (choice) {
			case 1: {
				masterController.visitorController.createVisitor(user, "Aprroved");
				Helper.printFunction(str.visitorRegistered);
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
				break;
			}
			case 6:
				return true;
			case 7:
				return false;
			case 8:
			{
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
