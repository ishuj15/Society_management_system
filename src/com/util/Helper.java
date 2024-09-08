package com.util;

import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Helper {
	private static final int SALT_LENGTH = 16;

	public static boolean checkLimit(int limit, int choice) {
		if (choice > 0 && choice <= limit)
			return true;
		else
			return false;
	}
	 public static String readSensitiveData() {
	        Console console = System.console();
	        if (console == null) {
	            throw new IllegalStateException("No console available");
	        }

	        char[] passwordArray = console.readPassword();
	        StringBuilder input = new StringBuilder();
	        
	        for (char ch : passwordArray) {
	            input.append(ch);
	        }

	        return input.toString();
	    }
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

	public static byte[] generateSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		sr.nextBytes(salt);
		return salt;
	}

	public static boolean isPasswordValid(String password) {
		
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
		
		return phoneNumber.matches("\\d{10}");
	}

	public static boolean isUserRoleValid(String userRole) {
				return userRole.equals("resident") || userRole.equals("guard");
	}
	public static boolean ComplaintStatus(String status)
	{
		return status.equals("resolved") || status.equals("pending") || status.equals("unresolved");
	}
	public static boolean isEmailValid(String email) {
	
		if (email == null || email.trim().isEmpty()) {
			System.out.println("Email cannot be null or empty.");
			return false;
		}

		
		int atIndex = email.indexOf('@');
		if (atIndex == -1) {
			System.out.println("Email must contain '@' symbol.");
			return false;
		}

		
		String localPart = email.substring(0, atIndex);
		String domainPart = email.substring(atIndex + 1);

		
		if (localPart.isEmpty() || domainPart.isEmpty()) {
			System.out.println("Email must contain both local and domain parts(eg- @gmail.com).");
			return false;
		}

		
		int dotIndex = domainPart.indexOf('.');
		if (dotIndex == -1 || dotIndex == domainPart.length() - 1) {
			System.out.println("Domain part must contain a period and a valid top-level domain.");
			return false;
		}

		
		for (char ch : email.toCharArray()) {
			if (!Character.isLetterOrDigit(ch) && ch != '@' && ch != '.' && ch != '_' && ch != '-') {
				System.out.println("Email contains invalid characters.");
				return false;
			}
		}

		return true;
	}

	public static boolean isUsernameValid(String username) {
		
		if (username == null || username.trim().isEmpty()) {
			System.out.println("Username cannot be null or empty.");
			return false;
		}

		
		if (username.length() < 3 || username.length() > 15) {
			System.out.println("Username must be between 3 and 15 characters long.");
			return false;
		}

		
		for (char ch : username.toCharArray()) {
			if (!Character.isLetterOrDigit(ch) && ch != '_' && ch != '-') {
				System.out.println("Username can only contain letters, digits, underscores, and hyphens.");
				return false;
			}
		}
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

	public static String maskedPassword() {
		JPasswordField passwordField = new JPasswordField(20);
		JOptionPane.showConfirmDialog(null, passwordField, "Enter your password", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
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

	public static int choiceInput() {
		Scanner scanner = new Scanner(System.in);
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a Valid Input");
			scanner.next();
			System.out.print("Enter your choice: ");
		}
		int value = scanner.nextInt();
		return value;
	}

	public static void printFunction(String string) {
		System.out.println(string);
	}
	public static boolean notNullCheck(String string) {
	    if (string == null || string.trim().isEmpty()) {
	       
	        return false;
	    }
	    return true;
	}
	public  static boolean isValidTarget(String target) {
		
		return target.equals("gaurd")|| target.equals("all") || target.equals("resident");
	}
	
}


