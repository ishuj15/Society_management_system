package com.service;
import com.dao.UserDAO;
import com.util.*;
import com.Model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class UserService {
    public static UserDAO userDAO = new UserDAO();
   
    private static boolean verifyPassword(String enteredPassword, String storedHashedPassword) {
    	//System.out.println(salt2);
//    	 byte[] salt = salt2.getBytes();
        String hashedEnteredPassword = Helper.hashPassword(enteredPassword);
    	//String hashedEnteredPassword = Helper.hashPassword(enteredPassword,salt);
        System.out.println(hashedEnteredPassword);
        System.out.println(storedHashedPassword);
        System.out.println(hashedEnteredPassword.equals(storedHashedPassword));
        return hashedEnteredPassword.equals(storedHashedPassword);
    }
    public void addUser(User user) throws SQLException, ClassNotFoundException {

    	userDAO.addUser(user);
    }

    public User getUserById(String idUser) throws SQLException, ClassNotFoundException {
        return userDAO.getUserById(idUser);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return userDAO.getAllUsers();
    }

//    public void updateUser(User user) throws SQLException {
//        userDAO.updateEntity(user,user.getIdUser());
//    }

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
   
}

;