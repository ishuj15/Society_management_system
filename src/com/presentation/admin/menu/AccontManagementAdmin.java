package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Model.User;
import com.controller.UserController;
import com.util.StringConstants;

public class AccontManagementAdmin {
	private  Scanner scanner;
	private UserController userController;
	public  AccontManagementAdmin()
	{
		this.userController = new UserController();
    	this.scanner = new Scanner(System.in);
	}
	public boolean displayMenu(User user) throws SQLException, ClassNotFoundException {
			
			while(true)
			{
				System.out.println("Manage user\n");

			System.out.println("1) "+StringConstants.addUser);
			System.out.println("2) "+StringConstants.deleteUser);
			System.out.println("3) "+StringConstants.viewListOfUser);
			System.out.println("4) "+StringConstants.viewListByUserName);
			System.out.println("5) "+StringConstants.updateUser);
			System.out.println("6) "+StringConstants.previousmenu);
			System.out.println("7) "+StringConstants.logout);
			System.out.println("8) Exit");

			System.out.println(StringConstants.enterChoice);
			 int choice=-1;
			 
			 try {
					choice= scanner.nextInt();
					}catch (InputMismatchException e) {
		                System.out.println("Invalid input. Please enter a number.");
		                scanner.nextLine();
		                continue; }
	          switch(choice)
	          {
	          case 1:
	          {
	        	  UserController.createUser();
	        	  break;
	          }
	          case 2:
	          {
	        	  User user2= userController.getUserByadmin();
	        	  userController.deleteUser(user2);
	        	  break;
	          }
	          case 3:
	          {
	        	  userController.listUsers();
	        	  break;
	          }
	          case 4:
	          {
	        	  System.out.println("Enter user name");
	        	  String userName= scanner.nextLine();
	        	  userController.viewUser(userName);
	        	 break; 
	          }
	          case 5:
	          {
	        	 User user2= userController.getUserByadmin();
	        	 userController.updateUser(user2);
	        	  break;
	          }
	          case 6:
	        	  return true;
	          case 7:
	        	  return false;
	          case 8:{
	        	  scanner.close();
	        	  System.exit(0);
	        	  return false;
	          }
	        	  default:
	        		  System.out.println("Invalid input");

	        		  
	          
	          }
			
		}
		
	}

}
