package com.controller;

import com.Model.User;
import com.service.UserService;
import com.societyManagement.main.GuardMenu;
import com.societyManagement.main.ResidentMenu;
import com.util.Helper;

import java.io.Console;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private static UserService userService = new UserService();

    public static void createUser() throws SQLException {
    	String password,userRole,phoneNo;
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine().trim();
        
       
        while(true)
        {
        	 System.out.print("Enter user role: ");
        	 userRole = scanner.nextLine().trim();
        	if(Helper.isUserRoleValid(userRole.toLowerCase()))
        		break;
        	else
        		System.out.println("Wrong role");
        	
        	
        }
        
        while(true) {
        System.out.print("Enter password: ");
         password = scanner.nextLine().trim();
        if (Helper.isPasswordValid(password)) {
            break;
        } else {
            System.out.println("Password does not meet the policy requirements. Please try again.");
        }
        }
       
       
        while(true)
        {
        	 System.out.print("Enter phone number: ");
        	phoneNo = scanner.nextLine().trim();
        	if(Helper.isPhoneNumberValid(phoneNo))
        		break;
        	else
        		System.out.println("Wrong phone number");
        	
        }
         
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();
        
        String hashedPassword=Helper.hashPassword(password);
        User user = new User();
        user.setUserName(userName);
        user.setUserRole(userRole);
        user.setPassword(hashedPassword);
        user.setPhoneNo(phoneNo);
        user.setEmail(email);
        user.setAddress(address);

        userService.addUser(user);
        System.out.println("User created successfully!");
    }

    public void viewUser(int idUser) throws SQLException {
        User user = userService.getUserById(idUser);
        if (user != null) {
            System.out.println("User ID: " + user.getIdUser());
            System.out.println("User Name: " + user.getUserName());
            System.out.println("User Role: " + user.getUserRole());
            System.out.println("Phone No: " + user.getPhoneNo());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Address: " + user.getAddress());
        } else {
            System.out.println("User not found!");
        }
    }

    public void listUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println("User ID: " + user.getIdUser() + ", User Name: " + user.getUserName());
        }
    }

    public void updateUser(int idUser) throws SQLException {
    	Scanner scanner = new Scanner(System.in);
       
			User user = userService.getUserById(idUser);
      if (user != null) {
      System.out.print("Enter new user name: ");
      String userName = scanner.nextLine();
      System.out.print("Enter new user role: ");
      String userRole = scanner.nextLine();
      System.out.print("Enter new password: ");
      String password = scanner.nextLine();
      System.out.print("Enter new phone number: ");
      String phoneNo = scanner.nextLine();
      System.out.print("Enter new email: ");
      String email = scanner.nextLine();
      System.out.print("Enter new address: ");
      String address = scanner.nextLine();
      user.setUserName(userName);
      user.setUserRole(userRole);
      user.setPassword(password);
      user.setPhoneNo(phoneNo);
      user.setEmail(email);
      user.setAddress(address);

      userService.updateUser(user);
      System.out.println("User updated successfully!");
   } else {
      System.out.println("User not found!");
   }
		
}

public void deleteUser(int idUser) throws SQLException {

    userService.deleteUser(idUser);
    System.out.println("User deleted successfully!");
}
public static  void login() throws SQLException{
	
	
	try {
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		 System.out.println("Enter your login details:");
	        System.out.print("Username: ");
	        String userName = scanner.nextLine().trim();
	        System.out.print("Password: ");	 
	        String password = scanner.nextLine().trim();
	        User user = UserService.login(userName, password);

	        if (user == null ) {
	        	
	        	 System.out.println("Invalid username or password. Please try again.");
		            logger.warning("Failed login attempt for username: " + userName);
	            
	           
	        } else {
	        	System.out.println("Login successful! Welcome, " + user.getUserName() + ".");
	            logger.info("User logged in: " + user.getUserName());
	           
	            
	            if(user.getUserRole().toLowerCase().equals("resident"))
	            {
	            	ResidentMenu obj= new ResidentMenu();
	            	
	            	obj.displayMenu(user);	
	            }
	            else if(user.getUserRole().toLowerCase().equals("guard")) {
	            	//GuardMenu.displayMenu();
	            	System.out.println("Guard");
	            	GuardMenu obj= new GuardMenu();
	            	obj.displayMenu(user);
	            	
	            }
	            else
	            {
	            	System.out.println("admin");
	            	//AdminMenu.display(); 	
	            }
	           
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Login failed due to a database error", e);
	    }
	
}
}
//Console console = System.console();
//if (console == null) {
//    System.out.println("No console available");
//   // return null;
//}
//String userName = console.readLine("Enter your username: ");
//char[] inputFromConsole = console.readPassword("Enter your");
//String password = new String(inputFromConsole);
// return result;

//Swing
//JPasswordField passwordField = new JPasswordField(20);
//JOptionPane.showConfirmDialog(null, passwordField, "Enter your password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//char[] passwordChars = passwordField.getPassword();
//String password = new String(passwordChars);
