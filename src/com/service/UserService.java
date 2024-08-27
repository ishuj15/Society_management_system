package com.service;
import com.dao.GenericDAO;
import com.dao.UserDAO;
import com.Model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserService {
    private static UserDAO userDAO = new UserDAO();
     static int userId;
    
//    private static String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashBytes) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static boolean verifyPassword(String enteredPassword, String storedHashedPassword) {
//        String hashedEnteredPassword = hashPassword(enteredPassword);
//        return hashedEnteredPassword.equals(storedHashedPassword);
//    }
    public void addUser(User user) throws SQLException {
    	String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
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
    	User user = userDAO.getUserByUserName(username);
    	 if (user != null && user.getPassword().equals(password)) {
             return true; // Successful login
         }
            // SQL query to get the user by username
//            String sql = "SELECT * FROM user WHERE userName = ?";
//            userId=User.getIdUser();
//            List<User> users = (List<User>) userDAO.getEntityById( userId);
//
//            if (!users.isEmpty()) {
//                User user = users.get(0);
//                // Verify the entered password against the stored hashed password
//                if (verifyPassword(password, user.getPassword())) {
//                    return user;
//                }
//            }
//            return null; // If the username or password is incorrect
        }


        // Hash the password using SHA-256 or any other preferred algorithm
        
   
}

