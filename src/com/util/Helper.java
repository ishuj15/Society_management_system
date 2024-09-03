package com.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Helper {
	private static final int SALT_LENGTH = 16;

	public static String hashPassword(String password) {
//		 try {
//	            // Combine salt and password
//	            String saltedPassword = Base64.getEncoder().encodeToString(salt) + password;
//
//	            // Hash the combined salt and password
//	            MessageDigest md = MessageDigest.getInstance("SHA-256");
//	            byte[] hashBytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
//	            StringBuilder sb = new StringBuilder();
//	            for (byte b : hashBytes) {
//	                sb.append(String.format("%02x", b));
//	            }
//	            return sb.toString();
//	        } catch (NoSuchAlgorithmException e) {
//	            throw new RuntimeException(e);
//	        }
//	}
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
	public static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        sr.nextBytes(salt);
        return salt;
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
	        return userRole.equals("resident") || userRole.equals("guard" )  ;
	    }
	    public static boolean isEmailValid(String email) {
	        // Check if the email is null or empty
	        if (email == null || email.trim().isEmpty()) {
	            System.out.println("Email cannot be null or empty.");
	            return false;
	        }

	        // Check for the presence of the '@' symbol
	        int atIndex = email.indexOf('@');
	        if (atIndex == -1) {
	            System.out.println("Email must contain '@' symbol.");
	            return false;
	        }

	        // Split the email into local part and domain part
	        String localPart = email.substring(0, atIndex);
	        String domainPart = email.substring(atIndex + 1);

	        // Check if both local part and domain part are non-empty
	        if (localPart.isEmpty() || domainPart.isEmpty()) {
	            System.out.println("Email must contain both local and domain parts.");
	            return false;
	        }

	        // Check for the presence of a period in the domain part
	        int dotIndex = domainPart.indexOf('.');
	        if (dotIndex == -1 || dotIndex == domainPart.length() - 1) {
	            System.out.println("Domain part must contain a period and a valid top-level domain.");
	            return false;
	        }

	        // Optional: Check for invalid characters
	        for (char ch : email.toCharArray()) {
	            if (!Character.isLetterOrDigit(ch) && ch != '@' && ch != '.' && ch != '_' && ch != '-') {
	                System.out.println("Email contains invalid characters.");
	                return false;
	            }
	        }

	        return true;
	    }
	    public static boolean isUsernameValid(String username) {
	        // Check if the username is null or empty
	        if (username == null || username.trim().isEmpty()) {
	            System.out.println("Username cannot be null or empty.");
	            return false;
	        }

	        // Check the length of the username
	        if (username.length() < 3 || username.length() > 15) {
	            System.out.println("Username must be between 3 and 15 characters long.");
	            return false;
	        }

	        // Check for invalid characters
	        for (char ch : username.toCharArray()) {
	            if (!Character.isLetterOrDigit(ch) && ch != '_' && ch != '-') {
	                System.out.println("Username can only contain letters, digits, underscores, and hyphens.");
	                return false;
	            }
	        }

	        // Check if the username starts with a letter
	        if (!Character.isLetter(username.charAt(0))) {
	            System.out.println("Username must start with a letter.");
	            return false;
	        }

	        return true;
	    }
	    public static String generateUniqueId() {
	        UUID uuid = UUID.randomUUID();
	        return uuid.toString().substring(24);
	    }
	    public static String maskedPassword()
	    {
	    	JPasswordField passwordField = new JPasswordField(20);
            JOptionPane.showConfirmDialog(null, passwordField, "Enter your password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            return password;

	    }
	    public static boolean isValidDate(String date) {
	     
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        try {
	           
	            LocalDate.parse(date, dateFormatter);
	            return true; 
	        } catch (DateTimeParseException e) {
	            return false; 
	        }
	    }
	    public static boolean isValidTime(String time) {
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        try {
	            LocalTime.parse(time, timeFormatter);
	            return true;
	        } catch (DateTimeParseException e) {
	            return false;
	        }
	    }
}
