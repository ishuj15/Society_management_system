package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.AttendanceController;
import com.util.StringConstants;

public class AttendanceManagementAdmin {
	private Scanner scanner;
	private AttendanceController attendanceController;

	public AttendanceManagementAdmin() {
		this.attendanceController = new AttendanceController();
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu(User user) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println(StringConstants.adminAttendance);
			System.out.println(StringConstants.enterChoice);
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1: {
				attendanceController.listAttendances();
				break;
			}
			case 2: {

				// attendanceController
				break;
			}
			case 3: {
				break;
			}
			case 4:
				break;
			default:
				System.out.println("Invalid input, Please try again");
			}
		}
	}
}
