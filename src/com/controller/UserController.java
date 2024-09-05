package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.Model.User;
import com.service.UserService;
import com.societyManagement.main.AdminMenu;
import com.societyManagement.main.GuardMenu;
import com.societyManagement.main.ResidentMenu;
import com.util.FileLogging;
import com.util.Helper;
import com.util.StringConstants;

public class UserController {
	
	public static UserService userService = new UserService();
	private Scanner scanner = new Scanner(System.in);

	private static Logger logger = FileLogging.getLogger(UserController.class);

	public static void createUser() throws SQLException, ClassNotFoundException {
		String password, userRole, phoneNo, email, userName;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter user name: ");
			userName = scanner.nextLine().trim();
			if (Helper.isUsernameValid(userName)) {
				break;
			}

			else {
				System.out.println("username already taken");
				System.out.println("Please try again");
			}

		}
		while (true) {
			System.out.print("Enter user role (resident,guard): ");
			userRole = scanner.nextLine().trim();
			if (Helper.isUserRoleValid(userRole.toLowerCase()))
				break;
			else
				System.out.println("Wrong role");

		}

		while (true) {
			System.out.print("Enter password: ");
			password = scanner.nextLine().trim();
			
			if (Helper.isPasswordValid(password)) {
				break;
			} else {
				System.out.println("Password does not meet the policy requirements. Please try again.");
			}
		}
		while (true) {
			System.out.print("Enter phone number: ");
			phoneNo = scanner.nextLine().trim();
			if (Helper.isPhoneNumberValid(phoneNo))
				break;
			else
				System.out.println("Wrong phone number");

		}
		while (true) {
			System.out.print("Enter email: ");
			email = scanner.nextLine().trim();
			if (Helper.isEmailValid(email))
				break;
			else
				System.out.println("Wrong email format");

		}

		System.out.print("Enter address: ");
		String address = scanner.nextLine().trim();

		String hashedPassword = Helper.hashPassword(password);

		String userId = Helper.generateUniqueId();
		User user = new User();
		user.setIdUser(userId);
		user.setUserName(userName);
		user.setUserRole(userRole);
		user.setPassword(hashedPassword);
		user.setPhoneNo(phoneNo);
		user.setEmail(email);
		user.setAddress(address);

		userService.addUser(user);
		System.out.println("User created successfully!");
	}

	public void viewUser(String idUser) throws SQLException, ClassNotFoundException {
		userService.getUserByUserName(idUser);

	}

	public void listUsers() throws SQLException, ClassNotFoundException {
		userService.getAllUsers();
	}

	public void updateUser(User user) throws SQLException, ClassNotFoundException {
		if (user != null) {
			String str = """
					1) UserName
					2) Password
					3) Phone Number
					4) Email
					5) Address""";
			System.out.println(str);
			System.out.println("6) " + StringConstants.previousmenu);

			System.out.println("Select field that you need to update");

			String columnToUpdate = null, newValue = null;

			int choice = 0;
			while (true) {
				System.out.println(StringConstants.enterChoice);

				choice = Helper.choiceInput();
				if (Helper.checkLimit(5, choice))
					break;
				System.out.println("Invalid User, Please try again");

			}

			switch (choice) {
			case 1: {
				while (true) {
					columnToUpdate = "userName";
					System.out.print("Enter new user name: ");
					newValue = scanner.nextLine();
					if (Helper.isUsernameValid(newValue))
						break;
					else
						System.out.println("incorrect userName, Please try again");

				}
				userService.updateUser(user, columnToUpdate, newValue);
				break;
			}
			case 2: {
				while (true) {
					columnToUpdate = "password";
					System.out.print("Enter new password: ");
					newValue = scanner.nextLine();
					if (Helper.isPasswordValid(newValue))
						break;
					else
						System.out.println("incorrect password, Please try again");

				}
				userService.updateUser(user, columnToUpdate, newValue);
				break;
			}
			case 3: {
				while (true) {
					columnToUpdate = "phoneNo";
					System.out.print("Enter new phone number: ");
					newValue = scanner.nextLine();
					if (Helper.isPhoneNumberValid(newValue)) {
						break;
					}

					else
						System.out.println("incorrect phone Number, Please try again");

				}
				userService.updateUser(user, columnToUpdate, newValue);
				break;
			}
			case 4: {
				while (true) {

					columnToUpdate = "email";
					System.out.print("Enter new email: ");
					newValue = scanner.nextLine();
					if (Helper.isEmailValid(newValue))
						break;
					else
						System.out.println("incorrect password, Please try again");

				}
				userService.updateUser(user, columnToUpdate, newValue);
				break;
			}
			case 5: {
				columnToUpdate = "address";
				System.out.print("Enter new address: ");
				newValue = scanner.nextLine();
				userService.updateUser(user, columnToUpdate, newValue);
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
		if (user.getUserRole().toLowerCase().equals("admin")) {
			System.out.println("You can't delete admin");
			return;
		}

		else {
			userService.deleteUser(user);
			System.out.println("User deleted successfully!");
		}
	}

	public static void login() throws SQLException, ClassNotFoundException {

		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your login details:");
			System.out.print("Username: ");

			String userName = scanner.nextLine().trim();
			System.out.println("Enter your password");
//	        String password=Helper.maskedPassword();
			String password = scanner.nextLine().trim();
			User user = UserService.login(userName, password);

			if (user == null) {

				System.out.println("Invalid username or password. Please try again.");
				logger.warning("Failed login attempt for username: " + userName);

			} else {
				System.out.println("Login successful! Welcome, " + user.getUserName() + ".");
				logger.info("User logged in: " + user.getUserName());
				// logge.info("User Authenticated Successfully! Username: " + username););

				if (user.getUserRole().toLowerCase().equals("resident")) {
					ResidentMenu obj = new ResidentMenu();

					obj.displayMenu(user);
				} else if (user.getUserRole().toLowerCase().equals("guard")) {
					GuardMenu guardMenu = new GuardMenu();
					guardMenu.displayMenu(user);
					// System.out.println("Guard");
//	            	GuardMenu obj= new GuardMenu();
//	            	obj.displayMenu(user);

				} else {
					// System.out.println("admin");
					AdminMenu adminMenuObj = new AdminMenu();

					adminMenuObj.displayMenu(user);
				}

			}
		} catch (SQLException e) {
			// logger.log(Level.SEVERE, "Login failed due to a database error", e);
		}

	}

	public User getUsernameList() throws ClassNotFoundException, SQLException {
		List<User> users = userService.getUsername();
		System.out.println(StringConstants.enterChoice);
		int choice = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(users.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		User selectedUser = users.get(choice - 1);
		return selectedUser;

	}

	public User getUserByadmin() throws ClassNotFoundException, SQLException {
		List<User> users = userService.getAllUsers();

		System.out.println("Select user  ");
		int choice = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(users.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		User selectedUser = users.get(choice - 1);
		return selectedUser;
	}

}
