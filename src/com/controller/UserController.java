package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Model.User;
import com.service.UserService;
import com.util.Helper;
import com.util.StringConstants;

public class UserController {
	
	public static UserService userService = new UserService();
	
	

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
		if(user.equals(null))
		{
			System.out.println("No user found");
			return;
		}
		else
		{
			userService.updateUser(user);
			System.out.println("User updated successfully!");
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

			String password = scanner.nextLine().trim();
			 UserService.login(userName, password);

			
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
			System.out.println("Invalid Input, Please try again");
		}
		User selectedUser = users.get(choice - 1);
		return selectedUser;
	}

	public User getUserByadmin() throws ClassNotFoundException, SQLException {
		List<User> users = userService.getAllUsers();
		if(users.isEmpty())
		{
			System.out.println("No List of users found");
			return null;
		}
		else {
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
}