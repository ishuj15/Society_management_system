
package com.presentation.guard.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.AttendanceController;

public class AttendanceMenu {
	private  Scanner scanner;
	private AttendanceController attendanceController;
	public  AttendanceMenu()
	{
		this.attendanceController = new AttendanceController();
    	this.scanner = new Scanner(System.in);
	}
	public void displayMenu(User user) throws ClassNotFoundException, SQLException {
		
		while(true)
		{
		String str= """
				1) Mark Attendance
				2) View Attendance
				3) Exit
				""";
		System.out.println(str);
		System.out.println("Enter your choice");
		int choice =scanner.nextInt();
		scanner.nextLine();
		switch(choice)
		{
		case 1:
		{
			attendanceController.createAttendance(user.getIdUser());
			break;
		}
		case 2:
			{
				attendanceController.listAttendances();
			break;
			}
		case 3:
			return;
			default:
				System.out.println("Invalid input, Please try again ");
			}
		}
		
	}

}
