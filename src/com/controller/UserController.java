package com.controller;

import com.Model.User;
import com.service.UserService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private UserService userService = new UserService();

    public void createUser() throws SQLException {

        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine().trim();
        System.out.print("Enter user role: ");
        String userRole = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter phone number: ");
        String phoneNo = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();

        User user = new User();
        user.setUserName(userName);
        user.setUserRole(userRole);
        user.setPassword(password);
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
        try (Scanner scanner = new Scanner(System.in)) {
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
}

public void deleteUser(int idUser) throws SQLException {

    userService.deleteUser(idUser);
    System.out.println("User deleted successfully!");
}
public void login(Scanner scanner) throws SQLException{
	try {
		 System.out.println("Enter your login details:");
	        System.out.print("Username: ");
	        String userName = scanner.nextLine();
	        System.out.print("Password: ");
	        String password = scanner.nextLine();
	        User user = UserService.login(userName, password);

	        if (user != null ) {
	        	
	            System.out.println("Login successful! Welcome, " + user.getUserName() + ".");
	            logger.info("User logged in: " + user.getUserName());
	            // Proceed to the user's main menu or dashboard
	        } else {
	            System.out.println("Invalid username or password. Please try again.");
	            logger.warning("Failed login attempt for username: " + userName);
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Login failed due to a database error", e);
	    }
	
}
}

//public class UserController {
//    private UserService userService;
//
//    public UserController(Connection connection) {
//        this.userService = new UserService(connection);
//    }
//
//    public void addUser(User user) {
//        try {
//            userService.addUser(user);
//            System.out.println("User added successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public User getUserById(int idUser) {
//        try {
//            return userService.getUserById(idUser);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers() {
//        try {
//            return userService.getAllUsers();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void updateUser(User user) {
//        try {
//            userService.updateUser(user);
//            System.out.println("User updated successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteUser(int idUser) {
//        try {
//            userService.deleteUser(idUser);
//            System.out.println("User deleted successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        // Example database connection
//        String url = "jdbc:mysql://localhost:3306/test";
//        String user = "root";
//        String password = "password";
//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//            UserController controller = new UserController(connection);
//
//            // Example usage
//            User newUser = new User();
//            newUser.setUserName("JohnDoe");
//            newUser.setUserRole("Admin");
//            newUser.setPassword("password123");
//            newUser.setPhoneNo("1234567890");
//            newUser.setEmail("john.doe@example.com");
//            newUser.setAddress("123 Main St");
//            controller.addUser(newUser);
//
//            User fetchedUser = controller.getUserById(1);
//            System.out.println("Fetched User: " + fetchedUser.getUserName());
//
//            List<User> users = controller.getAllUsers();
//            users.forEach(user -> System.out.println("User: " + user.getUserName()));
//
//            fetchedUser.setUserRole("User");
//            controller.updateUser(fetchedUser);
//
//            controller.deleteUser(1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
