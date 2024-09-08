package com.service;

import java.sql.SQLException;
import java.util.List;

import java.util.logging.Logger;

import com.Model.User;

import com.dao.UserDAO;

import com.util.FileLogging;
import com.util.Helper;



public class UserService {
	public static UserDAO userDAO = new UserDAO();
	//private static Logger logger = FileLogging.getLogger(UserController.class);

	private static boolean verifyPassword(String enteredPassword, String storedHashedPassword) {

		String hashedEnteredPassword = Helper.hashPassword(enteredPassword);
		return hashedEnteredPassword.equals(storedHashedPassword);
	}
	public void addUser(User user) throws SQLException, ClassNotFoundException {
		userDAO.addUser(user);
	}

	public User getUserByUserName(String idUser) throws SQLException, ClassNotFoundException {	
		return userDAO.getUserByUsername(idUser);
	}

	public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
		return  userDAO.getAllUsers();
	}

	public List<User> getUsername() throws ClassNotFoundException, SQLException {
		 return  userDAO.getAllUsers();
	}
	public boolean updateUser(User user, String columnToUpdate, String newValue) throws SQLException, ClassNotFoundException {
		
		 return userDAO.updateUser(user.getIdUser(), columnToUpdate, newValue);
	}

	public void deleteUser(User user) throws SQLException, ClassNotFoundException {

		userDAO.deleteUser(user.getIdUser());
	}

	public static User login(String userName, String password) throws SQLException, ClassNotFoundException {
		 User user = userDAO.getUserByUsername(userName);

         if (user==null) {
        	 return null;  
         }
         return verifyPassword(password, user.getPassword())?user:null ;

	}

	public User getUserById(String userId) throws ClassNotFoundException, SQLException {
		return userDAO.getUserById(userId);
	}
	public boolean userNameTaken(String username) throws ClassNotFoundException, SQLException
	{
		if(userDAO.isUsernameTaken(username).equals(null))
		return true ;
		else
			return false;
	}
}
