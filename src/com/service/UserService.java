package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.Model.User;
import com.controller.UserController;
import com.dao.UserDAO;
import com.societyManagement.main.AdminMenu;
import com.societyManagement.main.GuardMenu;
import com.societyManagement.main.ResidentMenu;
import com.util.FileLogging;
import com.util.Helper;
import com.util.StringConstants;

public class UserService {
	public static UserDAO userDAO = new UserDAO();
	private Scanner scanner = new Scanner(System.in);
	private static Logger logger = FileLogging.getLogger(UserController.class);

	private static boolean verifyPassword(String enteredPassword, String storedHashedPassword) {

		String hashedEnteredPassword = Helper.hashPassword(enteredPassword);

		return hashedEnteredPassword.equals(storedHashedPassword);
	}

	public void addUser(User user) throws SQLException, ClassNotFoundException {

		userDAO.addUser(user);
	}

	public User getUserByUserName(String idUser) throws SQLException, ClassNotFoundException {
		User user = userDAO.getUserByUsername(idUser);
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
			System.out.println("User not found!");
		}
		return user;

	}

	public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
		List<User> users = userDAO.getAllUsers();
		if (users == null || users.isEmpty()) {
			System.out.println("No users found.");
			return null;
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
		return users;
	}

	public List<User> getUsername() throws ClassNotFoundException, SQLException {
		List<User> users = userDAO.getAllUsers();
		if (users == null || users.isEmpty()) {
			System.out.println("users not found!");
		} else {
			System.out.printf("| %-5s | %-20s |\n", "S.No", "Username");
			System.out.println("|-------|----------------------|");

			int serialNumber = 1;
			for (User user : users) {
				System.out.printf("| %-5d | %-20s |\n", serialNumber++, user.getUserName());
			}
			System.out.println("|-------|----------------------|");
		}
		return users;

	}



	public void updateUser(User user) throws SQLException, ClassNotFoundException {
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
			if (Helper.checkLimit(6, choice))
				break;
			System.out.println("Invalid Input, Please try again");

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
			
			break;
		}
		case 5: {
			columnToUpdate = "address";
			System.out.print("Enter new address: ");
			newValue = scanner.nextLine();
			break;
		}
		case 6:
			return;
		default:
			System.out.println("Invalid input");
		}
		userDAO.updateUser(user.getIdUser(), columnToUpdate, newValue);
	}

	public void deleteUser(User user) throws SQLException, ClassNotFoundException {

		userDAO.deleteUser(user.getIdUser());
	}

	public static void login(String userName, String password) throws SQLException, ClassNotFoundException {

		User user = userDAO.getUserByUsername(userName);

		if (user.equals(null)) {
			System.out.println("Invalid username or password");

			return ;
		}

		 if(verifyPassword(password, user.getPassword())==false)
		 {
			 System.out.println("Invalid username or password. Please try again.");
			logger.warning("Failed login attempt for username: " + userName);
		 }
		 else {
			System.out.println("Login successful! Welcome, " + user.getUserName() + ".");
			logger.info("User logged in: " + user.getUserName());
			if (user.getUserRole().toLowerCase().equals("resident")) {
				ResidentMenu obj = new ResidentMenu();

				obj.displayMenu(user);
			} else if (user.getUserRole().toLowerCase().equals("guard")) {
				GuardMenu guardMenu = new GuardMenu();
				guardMenu.displayMenu(user);

			} else {
				AdminMenu adminMenuObj = new AdminMenu();

				adminMenuObj.displayMenu(user);
			}

		}

	}

	public User getUserById(String userId) throws ClassNotFoundException, SQLException {
		return userDAO.getUserById(userId);
	}
}
