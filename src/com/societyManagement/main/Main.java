package com.societyManagement.main;
import java.sql.SQLException;
import java.util.Scanner;
import com.controller.*;
import com.controller.UserController;
 
public class Main {
	
	private static final UserController userController=  new UserController();
		public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		String str= """
				1) Create Account
				2) Login
				3) Exit
				""";
		System.out.println("Welcome to the Society Management Application");
		System.out.println(str);
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch(choice)
		{
		case 1:
		{
			userController.createUser();
			break;
		}
		case 2:
		{
			userController.login(scanner);
			//login();
			break;
		}
				
		case 3:
		{
			scanner.close();
			return;
		}
			
		default:
			System.out.print("Wrong input");		
		}
		scanner.close();
		
	}
}

