package com.presentation.admin.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;
import com.controller.UserController;

public class AccontManagementAdmin {
	private  Scanner scanner;
	private MasterController masterController;
	public  AccontManagementAdmin()
	{
		this.masterController = new MasterController();
    	this.scanner = new Scanner(System.in);
	}
	public void displayMenu(User user) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
			String str=
					"""
					1) Add User
					2) Delete User
					3) View list of users
					4) View list by userName
					5) Update User
					6) Exit
					""";
			while(true)
			{
			System.out.println(str);
			System.out.println("Enter your choice");
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
	        	  User user2= masterController.userController.getUserByadmin();
	        	  masterController.userController.deleteUser(user2);
	        	  break;
	          }
	          case 3:
	          {
	        	  masterController.userController.listUsers();
	        	  break;
	          }
	          case 4:
	          {
	        	  System.out.println("Enter user name");
	        	  String userName= scanner.nextLine();
	        	  masterController.userController.viewUser(userName);
	        	 break; 
	          }
	          case 5:
	          {
	        	 User user2= masterController.userController.getUserByadmin();
	        	 masterController.userController.deleteUser(user2);
	        	  break;
	          }
	          case 6:
	        	  return;
	          
	          }
			
		}
		
	}

}
