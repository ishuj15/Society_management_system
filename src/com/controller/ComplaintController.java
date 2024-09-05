package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Model.Complaint;
import com.Model.User;
import com.service.ComplaintService;
import com.util.Helper;
import com.util.StringConstants;

public class ComplaintController {
	private ComplaintService complaintService = new ComplaintService();
	Scanner scanner = new Scanner(System.in);

	public void createComplaint(Complaint complaint) throws SQLException, ClassNotFoundException {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		LocalDate currentDate = LocalDate.now();
		System.out.print("Enter complaint description: ");
		String description = scanner.nextLine();

		String status = "Pending";
		String idComplaint = Helper.generateUniqueId();
		complaint.setIdComplaint(idComplaint);

		complaint.setDescription(description);
		complaint.setDate(java.sql.Date.valueOf(currentDate));
		complaint.setStatus(status);

		complaintService.addComplaint(complaint);
		System.out.println("Complaint created successfully!");

	}

	public void viewComplaint(String userId) throws SQLException, ClassNotFoundException {
		List<Complaint> complaints = complaintService.getAllComplaints();
		System.out.printf("%-5s %-15s %-20s %-15s %-15s%n", "No.", "Description", "Status", "Date", "UserName");
		System.out.println("---------------------------------------------------------------------------------");

		int serialNumber = 1;
		for (Complaint complaint : complaints) {
			
			User user = UserController.userService.getUserById(complaint.getUserId());

			// Print the complaint details along with the username
			System.out.printf("%-5d %-15s %-20s %-15s %-15s%n", serialNumber++, complaint.getDescription(),
					complaint.getStatus(), complaint.getDate().toString(), // Assuming getDate() returns a Date object
					user.getUserName());
			System.out.println("---------------------------------------------------------------------------------");
		}
	}

	public void listComplaints() throws SQLException, ClassNotFoundException {
		List<Complaint> complaints = complaintService.getAllComplaints();
		if (complaints.isEmpty()) {
			System.out.println("No complaints found.");
		} else {
			System.out.printf("%-5s %-15s %-20s %-15s %-15s%n", "No.", "Description", "Status", "Date", "UserName");
			System.out.println("---------------------------------------------------------------------------------");

			int serialNumber = 1;
			for (Complaint complaint : complaints) {
				// Fetch the user by userId
				User user = UserController.userService.getUserById(complaint.getUserId());

				// Print the complaint details along with the username
				System.out.printf("%-5d %-15s %-20s %-15s %-15s%n", serialNumber++, complaint.getDescription(),
						complaint.getStatus(), complaint.getDate().toString(), // Assuming getDate() returns a Date
																				// object
						user.getUserName());
				System.out.println("---------------------------------------------------------------------------------");
			}

		}
	}

	public void updateComplaint() throws SQLException, ClassNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Complaint complaint = getComplaint();
		if (complaint == null)
			System.out.println("Complaint not found!");
		else {
			String idComplaint = complaint.getIdComplaint();
			String str = """
					1) Status
					2) Exit
					""";
			System.out.println(str);
			System.out.println("Select that needs to be updated");
			int choice = 0;
			while (true) {
				System.out.println(StringConstants.enterChoice);

				choice = Helper.choiceInput();
				if (Helper.checkLimit(2, choice))
					break;
				System.out.println("Invalid User, Please try again");

			}

			switch (choice) {

			case 1: {
				System.out.print("Enter status: ");
				String status = scanner.nextLine();
				complaintService.updateComplaint(idComplaint, "status", status);
				System.out.println("Complaint updated successfully!");
				break;
			}
			case 2:
				return;
			default:
				System.out.println("Invalid Input , Please try again");
			}
		}

	}

	public void deleteComplaint(String userId) throws SQLException, ClassNotFoundException {
		List<Complaint> complaints = complaintService.getComplaintById(userId);
		System.out.println("Enter complaint  number which you need to delete");
		viewComplaint(userId);
		int choice = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(complaints.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		Complaint selectedComplaint = complaints.get(choice - 1);
		String idComplaint = selectedComplaint.getIdComplaint();
		complaintService.deleteComplaint(idComplaint);
		System.out.println("Complaint deleted successfully!");
	}

	public Complaint getComplaint() throws ClassNotFoundException, SQLException {

		List<Complaint> complaints = complaintService.getAllComplaints();
		listComplaints();
		System.out.println("Select Complaint ");
		int choice = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(complaints.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		return complaints.get(choice - 1);
	}

	public void deleteComplaintAdmin() throws SQLException, ClassNotFoundException {
		Complaint complaint = getComplaint();
		complaintService.deleteComplaint(complaint.getIdComplaint());
		System.out.println("Complaint deleted successfully!");
	}
}
