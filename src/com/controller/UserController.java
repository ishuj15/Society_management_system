package com.controller;

import com.Model.User;
import com.service.UserService;
import com.societyManagement.main.AdminMenu;
import com.societyManagement.main.GuardMenu;
import com.societyManagement.main.ResidentMenu;
import com.util.Helper;

//import java.io.Console;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.swing.JOptionPane;
//import javax.swing.JPasswordField;

public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private static UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);

    public static void createUser() throws SQLException, ClassNotFoundException {
    	String password,userRole,phoneNo,email,userName;
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        while(true)
        {
        System.out.print("Enter user name: ");
         userName = scanner.nextLine().trim();
        if(Helper.isUsernameValid(userName) /*&& !userService.userDAO.isUsernameTaken(userName)*/ )
        {
        	break;
        }
        	
        else
        {
        	System.out.println("username already taken");
        	System.out.println("Please try again");
        }
        	
        }
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
         while(true)
         {
        	 System.out.print("Enter email: ");
              email = scanner.nextLine().trim();
              if(Helper.isEmailValid(email))
            	  break;
              else
          		System.out.println("Wrong email format");
              
         }
       
        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();
       // byte[] salt = Helper.generateSalt();
        String hashedPassword=Helper.hashPassword(password);
       
       // String hashedPassword = Helper.hashPassword(password);
        String userId= Helper.generateUniqueId();
        User user = new User();
        user.setIdUser(userId);
        user.setUserName(userName);
        user.setUserRole(userRole);
        user.setPassword(hashedPassword);
        user.setPhoneNo(phoneNo);
        user.setEmail(email);
        user.setAddress(address);
        //user.setSalt(salt.toString());

        userService.addUser(user);
        System.out.println("User created successfully!");
    }

    public void viewUser(String idUser) throws SQLException, ClassNotFoundException {
        User user = userService.getUserByUserName(idUser);
        if (user != null) {
            System.out.println("-------------------------------------------------------------");
            System.out.printf("| %-15s | %-30s |\n", "Field", "Value");
            System.out.println("-------------------------------------------------------------");

            System.out.printf("| %-15s | %-30s |\n", "User Name", user.getUserName());
            System.out.printf("| %-15s | %-30s |\n", "User Role", user.getUserRole());
            System.out.printf("| %-15s | %-30s |\n", "Phone No", user.getPhoneNo());
            System.out.printf("| %-15s | %-30s |\n", "Email", user.getEmail());
            System.out.printf("| %-15s | %-30s |\n", "Address", user.getAddress());

            System.out.println("-------------------------------------------------------------");
        } else {
            System.out.println("User not found!");
        }
    }

    public void listUsers() throws SQLException, ClassNotFoundException {
    	List<User> users = userService.getAllUsers();
        
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        // Print table headers
        System.out.printf("+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");
        System.out.printf("| SN | User Name       | User Role                    | Phone No          | Email                        | Address                      |%n");
        System.out.printf("+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");

        // Print each user's details with a serial number
        int serialNumber = 1;
        for (User user : users) {
            System.out.printf("| %-2d | %-15s | %-30s | %-17s | %-28s | %-28s |%n",
                    serialNumber++, user.getUserName(), user.getUserRole(), user.getPhoneNo(), user.getEmail(), user.getAddress());
        }
        
        // Print table footer
        System.out.printf("+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");
    }
    public void updateUser(User user) throws SQLException, ClassNotFoundException {
    	
			//User user = userService.getUserById(idUser);
			  if (user != null) {
				  String str="""
				  		1) UserName
				  		2) Password
				  		3) Phone Number
				  		4) Email
				  		5) Address
				  		6) Exit
				  		""";
				  System.out.println(str);
				 // System.out.println("Select which one to update");
				  String columnToUpdate = null,newValue=null;
				  System.out.println("Enter your choice to update ");
				  int choice= scanner.nextInt();
				  scanner.nextLine(); 
				 
				  switch(choice)
			  	{
			  	case 1:
			  	{
			  		while(true)
			  		{
			  		columnToUpdate="userName";
			  		 System.out.print("Enter new user name: ");
			  	       newValue = scanner.nextLine();
			  	       if(Helper.isUsernameValid(newValue))
			  	    	   break;
			  	       else
			  	    	   System.out.println("incorrect userName, Please try again");
			  	       
			  	       }
			  	     userService.updateUser(user, columnToUpdate,newValue);
			  		break;
			  	}
			  	case 2:
			  	{
			  		while(true)
			  		{
			  		columnToUpdate="password";
			  		System.out.print("Enter new password: ");
			         newValue = scanner.nextLine();
			         if(Helper.isPasswordValid(newValue))
			        	 break;
			         else
			        	 System.out.println("incorrect password, Please try again");
			        	 
			        	 
			  		}
			         userService.updateUser(user, columnToUpdate,newValue);
			  		break;
			  	}
			  	case 3:
			  	{
			  		while(true)
			  		{
			  		columnToUpdate="phoneNo";
			  		System.out.print("Enter new phone number: ");
			        newValue = scanner.nextLine();
			        if(Helper.isPhoneNumberValid(newValue))
			        {
			        	 break;
			        }
			       	
			        else
			       	 System.out.println("incorrect phone Number, Please try again");
			       	 
			  		}
			        userService.updateUser(user, columnToUpdate,newValue);
			  		break;
			  	}
			  	case 4:
			  	{
			  		while(true)
			  		{
			  	
			  		columnToUpdate="email";
			  		System.out.print("Enter new email: ");
			        newValue = scanner.nextLine();
			        if(Helper.isEmailValid(newValue))
			       	 break;
			        else
			       	 System.out.println("incorrect password, Please try again");
			       	 
			  	}
			        userService.updateUser(user, columnToUpdate,newValue);
			  		break;
			  	}
			  	case 5:
			  	{
			  		columnToUpdate="address";
			  	   System.out.print("Enter new address: ");
			  	   newValue = scanner.nextLine();        	 
			  	 userService.updateUser(user, columnToUpdate,newValue);
			  		break;
			  	}
			  	case 6:
			  		return;
			  	default:
					  System.out.println("Invalid input");
			  	}
				  
			  System.out.println("User updated successfully!");
   } else {
			  System.out.println("User not found!");
   }
		}
		

public void deleteUser(User user) throws SQLException, ClassNotFoundException {
	if(user.getUserRole()=="admin")
	{
		 System.out.println("You can't delete admin");
		 return;	
	}
		
	else
	{
    userService.deleteUser(user);
    System.out.println("User deleted successfully!");
	}
}
public static  void login() throws SQLException, ClassNotFoundException{
	
	
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
	            	GuardMenu guardMenu =new GuardMenu();
	            	guardMenu.displayMenu(user);
	            	System.out.println("Guard");
	            	GuardMenu obj= new GuardMenu();
	            	obj.displayMenu(user);
	            	
	            }
	            else
	            {
	            	System.out.println("admin");
	            	AdminMenu adminMenuObj= new AdminMenu();
	            	
	            	adminMenuObj.displayMenu(user); 	
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
