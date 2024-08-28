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
    private static UserDAO userDAO = new UserDAO();
   
    private static boolean verifyPassword(String enteredPassword, String storedHashedPassword) {
        String hashedEnteredPassword = Helper.hashPassword(enteredPassword);
        System.out.println(hashedEnteredPassword);
        System.out.println(storedHashedPassword);
        System.out.println(hashedEnteredPassword.equals(storedHashedPassword));
        return hashedEnteredPassword.equals(storedHashedPassword);
    }
    public void addUser(User user) throws SQLException {

    	userDAO.addEntity(user);
    }

    public User getUserById(int idUser) throws SQLException {
        return userDAO.getEntityById(idUser);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllEntities();
    }

    public void updateUser(User user) throws SQLException {
        userDAO.updateEntity(user,user.getIdUser());
    }

    public void deleteUser(int idUser) throws SQLException {
    	
        userDAO.deleteEntity(idUser);
    }
        
    public static User login(String userName, String password) throws SQLException {
    	 
         User user = userDAO.getEntityByUserName(userName);

         if (user==null) {
        	 return null;  
         }
       
         return verifyPassword(password, user.getPassword())?user:null ;
        
     }
   
}

;