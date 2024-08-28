package com.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.util.Scanner;

public class Helper {
	public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	 public static  boolean isPasswordValid(String password) {
	        // Implement password policy validation here
	        if (password.length() < 8) {
	            System.out.println("Password must be at least 5 characters long.");
	            return false;
	        }
	        if (!password.matches(".*[A-Z].*")) {
	            System.out.println("Password must contain at least one uppercase letter.");
	            return false;
	        }
	        if (!password.matches(".*[a-z].*")) {
	            System.out.println("Password must contain at least one lowercase letter.");
	            return false;
	        }
	        if (!password.matches(".*\\d.*")) {
	            System.out.println("Password must contain at least one digit.");
	            return false;
	        }
	        if (!password.matches(".*[@#$%^&+=].*")) {
	            System.out.println("Password must contain at least one special character (@#$%^&+=).");
	            return false;
	        }
	        return true;
	    }
	 public static boolean isPhoneNumberValid(String phoneNumber) {
	        // Phone number should be exactly 10 digits and only contain numbers
	        return phoneNumber.matches("\\d{10}");
	    }

	    public static  boolean isUserRoleValid(String userRole) {
	        // The role should be one of the predefined roles
	        return userRole.equals("resident") || userRole.equals("guard") || userRole.equals("admin");
	    }
//	    public  static String readPassword(Scanner scanner) {
//	        StringBuilder password = new StringBuilder();
//	        while (true) {
//	            char input = scanner.next().charAt(0);
//	            if (input == '\r' || input == '\n') {
//	                break;
//	            } else {
//	                System.out.print("*");
//	                password.append(input);
//	            }
//	        }
//	        return password.toString();
//	    }

}
