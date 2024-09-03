package com.service;
import com.dao.UserDAO;
import com.util.*;
import com.Model.User;


import java.sql.SQLException;
import java.util.List;


public class UserService {
    public static UserDAO userDAO = new UserDAO();
   
    private static boolean verifyPassword(String enteredPassword, String storedHashedPassword) {
  
        String hashedEnteredPassword = Helper.hashPassword(enteredPassword);
    	//String hashedEnteredPassword = Helper.hashPassword(enteredPassword,salt);
//        System.out.println(hashedEnteredPassword);
//        System.out.println(storedHashedPassword);
//        System.out.println(hashedEnteredPassword.equals(storedHashedPassword));
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
         System.out.printf("+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");
         System.out.printf("| SN | User Name       | User Role                    | Phone No          | Email                        | Address                      |%n");
         System.out.printf("+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");

        
         int serialNumber = 1;
         for (User user : users) {
             System.out.printf("| %-2d | %-15s | %-30s | %-17s | %-28s | %-28s |%n",
                     serialNumber++, user.getUserName(), user.getUserRole(), user.getPhoneNo(), user.getEmail(), user.getAddress());
         }
         
         System.out.printf("+----+-----------------+------------------------------+-------------------+------------------------------+------------------------------+%n");
   return users;
    }
    public List<User> getUsername() throws ClassNotFoundException, SQLException
    {
    	List<User> users = userDAO.getAllUsers();
    	if(users==null || users.isEmpty())
   	 {
   		 System.out.println("users not found!");	 
   	 }
   	 else
   	 {
   	System.out.printf("| %-5s | %-20s |\n", "S.No", "Username");
   	System.out.println("|-------|----------------------|");

   	int serialNumber = 1;
   	for (User user : users) {
   	   System.out.printf("| %-5d | %-20s |\n", 
   	                     serialNumber++, 
   	                     user.getUserName()); 
   	}
   	System.out.println("|-------|----------------------|");
   	 }
    	return users;

    }

     public void updateUser(User user, String columnToUpdate, String newValue) throws SQLException, ClassNotFoundException {
        userDAO.updateUser(user.getIdUser(), columnToUpdate, newValue);
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
   
}

;