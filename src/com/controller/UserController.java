package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.Model.User;
import com.service.UserService;
import com.societyManagement.main.AdminMenu;
import com.societyManagement.main.GuardMenu;
import com.societyManagement.main.ResidentMenu;
import com.util.FileLogging;
import com.util.Helper;
import com.util.str;

public class UserController {
	private static Logger logger = FileLogging.getLogger(UserController.class);
	private static Scanner scanner= new Scanner(System.in);
	public static UserService userService = new UserService();
	public static void createUser() throws SQLException, ClassNotFoundException {
		String password, userRole, phoneNo, email, userName,address;
		User user = new User();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Helper.printFunction(str.enterUserName);
			userName = scanner.nextLine().trim();
			if (Helper.isUsernameValid(userName)   ) {
				if( userService.userNameTaken(userName))
				break;
				else
					System.out.println(str.userNameTaken);		
			}
		}
		while (true) {
			System.out.print(str.enterRole);
			userRole = scanner.nextLine().trim();
			if (Helper.isUserRoleValid(userRole.toLowerCase()))
				break;
			else
				System.out.println(str.invalidInput);
		}
		while (true) {
			System.out.print(str.enterPassword);
			password = scanner.nextLine().trim();
		//	password=Helper.readSensitiveData();
			if (Helper.isPasswordValid(password)) {
				break;
			} else {
				System.out.println(str.wrongPassword);
			}
		}
		while (true) {
			System.out.print(str.enterPhoneNo);
			phoneNo = scanner.nextLine().trim();
			if (Helper.isPhoneNumberValid(phoneNo))
				break;
			else
				System.out.println(str.wrongPhoneNo);

		}
		while (true) {
			System.out.print(str.enterEmail);
			email = scanner.nextLine().trim();
			if (Helper.isEmailValid(email))
				break;
			else
				System.out.println(str.wrongEmail);

		}
		while (true) {
			System.out.print(str.enterAddress);
		    address = scanner.nextLine().trim();
		    if (Helper.notNullCheck(address)) {
		        break;
		    } 
		    else
		    	System.out.println(str.wrongAddress);

		}

		String hashedPassword = Helper.hashPassword(password);

		String userId = Helper.generateUniqueId();
		
		user.setIdUser(userId);
		user.setUserName(userName);
		user.setUserRole(userRole);
		user.setPassword(hashedPassword);
		user.setPhoneNo(phoneNo);
		user.setEmail(email);
		user.setAddress(address);

		userService.addUser(user);
		System.out.println(str.userCreatedSuccessfully);
	}

	public void viewUser(String idUser) throws SQLException, ClassNotFoundException {
		User user =  userService.getUserByUserName(idUser);

		if (user != null) {
			System.out.println("-----------------------------------------------------");
			System.out.printf("| %-15s | %-30s |\n", "Field", "Value");
			System.out.println("-----------------------------------------------------");

			System.out.printf("| %-15s | %-30s |\n", "User Name", user.getUserName());
			System.out.printf("| %-15s | %-30s |\n", "User Role", user.getUserRole());
			System.out.printf("| %-15s | %-30s |\n", "Phone No", user.getPhoneNo());
			System.out.printf("| %-15s | %-30s |\n", "Email", user.getEmail());
			System.out.printf("| %-15s | %-30s |\n", "Address", user.getAddress());

			System.out.println("------------------------------------------------------");
		} else {
			System.out.println(str.userNotFound);
		}

	}

	public void listUsers() throws SQLException, ClassNotFoundException {
		List<User> users=userService.getAllUsers();
		
		
		if (users == null || users.isEmpty()) {
			System.out.println(str.userNotFound);
			return ;
		}
		System.out.printf(
				"+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");
		System.out.printf(
				"| SN | User Name       | User Role                    | Phone No          | Email                        | Address                      |%n");
		System.out.printf(
				"+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");

		int serialNumber = 1;
		for (User user : users) {
			System.out.printf("| %-2d | %-15s | %-30s | %-17s | %-28s | %-28s |%n", serialNumber++, user.getUserName(),
					user.getUserRole(), user.getPhoneNo(), user.getEmail(), user.getAddress());
		}

		System.out.printf(
				"+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");
	}

	public void updateUser(User user) throws SQLException, ClassNotFoundException {
		
		System.out.println(str.userUpdateList);
		System.out.println(str.selectUserFieldToUpdate);

		String columnToUpdate = null, newValue = null;

		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(6, choice))
				break;
			System.out.println(str.invalidInput);
		}
		switch (choice) {
		case 1: {
			while (true) {
				columnToUpdate =str.userName;
				System.out.print(str.enterUserName);
				newValue = scanner.nextLine();
				if (Helper.isUsernameValid(newValue) )
					if(userService.userNameTaken(newValue))
						break;
						else
							System.out.println(str.userNameTaken);
					
				}
			break;
		}
		case 2: {
			while (true) {
				columnToUpdate = str.password;
				System.out.print(str.enterPassword);
				newValue = scanner.nextLine();
				if (Helper.isPasswordValid(newValue))
					break;
			}
			break;
		}
		case 3: {
			while (true) {
				columnToUpdate = str.phoneNo;
				System.out.print(str.enterPhoneNo);
				newValue = scanner.nextLine();
				if (Helper.isPhoneNumberValid(newValue)) {
					break;
				}
				else
					System.out.println(str.wrongPhoneNo);
			}
			break;
		}
		case 4: {
			while (true) {

				columnToUpdate = str.email;
				System.out.print("Enter new email: ");
				newValue = scanner.nextLine();
				if (Helper.isEmailValid(newValue))
					break;
				else
					System.out.println(str.wrongEmail);
			}
			break;
		}
		case 5: {
			columnToUpdate = str.address;
			while (true) {
				System.out.print(str.enterAddress);
				newValue = scanner.nextLine().trim();
			    if (Helper.notNullCheck(newValue)) {
			        break;
			    } 
			    else
			    	System.out.println(str.wrongAddress);

			}
			break;
		}
		case 6:
			return;
		case 7:
		{
			scanner.close();
			System.exit(0);
			return;
		}
		default:
			System.out.println(str.invalidInput);
		}
		userService.updateUser(user,columnToUpdate, newValue);
		System.out.println(str.userUpdatedSuccessfully);

	}

	public void deleteUser(User user) throws SQLException, ClassNotFoundException {
		if (user.getUserRole().toLowerCase().equals(str.admin)) {
			System.out.println(str.NoadminDeletion);
			return;
		}

		else {
			userService.deleteUser(user);
			System.out.println(str.UserdeletedSuccessfully);
		}
	}

	public static void login() throws SQLException, ClassNotFoundException, InterruptedException {

		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println(str.enterLoginDeatils);
			System.out.print(str.enterUserName);

			String userName = scanner.nextLine().trim();
			System.out.println(str.enterPassword);

			String password = scanner.nextLine().trim();
			User user= UserService.login(userName, password);
			 
			 
			 if (user == null ) {
		        	
	        	 System.out.println(str.invalidUserNameOrPassword);
		            logger.warning("Failed login attempt for username: " + userName);
	            
	           
	        } else {
	        	System.out.println( str.loginSuccessful+ user.getUserName() + ".");
	            logger.info("User logged in: " + user.getUserName());
	           
	            
	            if(user.getUserRole().toLowerCase().equals(str.resident))
	            {
	            	ResidentMenu obj= new ResidentMenu();
	            	
	            	obj.displayMenu(user);	
	            }
	            else if(user.getUserRole().toLowerCase().equals(str.guard)) {
	            	GuardMenu guardMenu =new GuardMenu();
	            	guardMenu.displayMenu(user);
	            }
	            else
	            {
	            	AdminMenu adminMenuObj= new AdminMenu();	
	            	adminMenuObj.displayMenu(user); 	
	            }
	        }
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Login failed due to a database error", e);
		}

	}

	public static  User getUsernameList() throws ClassNotFoundException, SQLException {
		List<User> users = userService.getUsername();
		int count=0;
		if (users == null || users.isEmpty()) {
			System.out.println(str.userNotFound);
		} else {
			
			System.out.printf("| %-5s | %-20s |\n", "S.No", "Username");
			System.out.println("|-------|----------------------|");

			int serialNumber = 1;
			for (User user : users) {
				if(user.getUserRole().equals("resident"))
				{
					count++;
					System.out.printf("| %-5d | %-20s |\n", serialNumber++, user.getUserName());
				}
				
			}
			System.out.println("|-------|----------------------|");
		}
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(count, choice))
				break;
			System.out.println(str.invalidInput);
		}
		User selectedUser = users.get(choice - 1);
		return selectedUser;
	}

	public User getUserByadmin() throws ClassNotFoundException, SQLException {
		List<User> users = userService.getAllUsers();
		if(users.isEmpty())
		{
			System.out.println(str.noListOfUser);
			return null;
		}
		else {
			listUsers();
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);
			choice = Helper.choiceInput();
			if (Helper.checkLimit(users.size(), choice))
				break;
			System.out.println(str.invalidInput);	
		}
		User selectedUser = users.get(choice - 1);
		return selectedUser;
		}	
	}
}