package com.presentarion.resident.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.Model.User;
import com.controller.MasterController;

public class UserManagementMenu {
	private final MasterController masterController;
	    private static Scanner scanner;
	   
	    //static int userId=User.getIdUser();
	    public UserManagementMenu()
	    {
	    	//this.user= new User();
	    	this.masterController = new MasterController();
	    	this.scanner = new Scanner(System.in);
	    }
	    public   void displayMenu(User user) throws SQLException {
	        while (true) {
	        	//System.out.println(user.getIdUser());
	           
	            System.out.println("1) Delete account");
	            System.out.println("2) Update Account");
	            System.out.println("3) Exit");
	            System.out.println("Enter your choice");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                {
//							if(User.getUserRole().equals("admin"))
//							{
//								System.out.println("Admin account cannot be deleted");
//								break;	
//							}
	                   masterController.userController.deleteUser(user.getIdUser());
	                    break;
	                }
	                case 2:
	                {
	                	
	                	masterController.userController.updateUser(user.getIdUser());
	                	break;	
	                }
	                case 3:
	                    return;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            
	        				}
	        }
	    
	    }

	    }
	    
