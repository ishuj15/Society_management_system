package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
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
	public void displayMenu(User user) throws SQLException, ClassNotFoundException {
			
			while(true)
			{
			System.out.println(StringConstants.adminDisplayUser);
			System.out.println(StringConstants.enterChoice);
			 int choice = scanner.nextInt();
			 scanner.nextLine();
	            
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
	        	  return;
	          
	          }
			
		}
		
	}

}
