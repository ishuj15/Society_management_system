package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Model.Complaint;
import com.Model.User;
import com.service.ComplaintService;
import com.util.Helper;
import com.util.str;

public class ComplaintController {
	private ComplaintService complaintService = new ComplaintService();
	Scanner scanner = new Scanner(System.in);

	public void createComplaint(User user) throws SQLException, ClassNotFoundException {
		String description=null;
		LocalDate currentDate = LocalDate.now();
		while(true)
		{
			System.out.print(str.enterComplaintDescription);
			description = scanner.nextLine();
			if(Helper.notNullCheck(description))
				System.out.println(str.notNullComplaint);
			else
				break;
		}
		String status = str.pending;
		String idComplaint = Helper.generateUniqueId();
		Complaint complaint = new Complaint();
		complaint.setUserId(user.getIdUser());
		complaint.setIdComplaint(idComplaint);
		complaint.setDescription(description);
		complaint.setDate(java.sql.Date.valueOf(currentDate));
		complaint.setStatus(status);
		complaintService.addComplaint(complaint);
		System.out.println(str.complaintCreated);

	}

	public void viewComplaint(User user) throws SQLException, ClassNotFoundException {
	
		 List<Complaint> complaints = complaintService.getComplaintById(user.getIdUser());
    	 System.out.println("-------------------------------------------------------------");
    	    System.out.printf("| %-5s | %-30s | %-15s | %-10s |\n", "S.No", "Description", "Status", "Date");
    	    System.out.println("-------------------------------------------------------------");

    	    int serialNumber = 1;
    	    for (Complaint complaint : complaints) {
    	        System.out.printf("| %-5d | %-30s | %-15s | %-10s |\n",
    	                serialNumber++,
    	                complaint.getDescription(),
    	                complaint.getStatus(),
    	                complaint.getDate());
    	    }  
    	    System.out.println("-------------------------------------------------------------");
	}

	public void listComplaints() throws SQLException, ClassNotFoundException {
		List<Complaint> complaints = complaintService.getAllComplaints();
		if (complaints.isEmpty()) {
			System.out.println(str.complaintNotFound);
		} else {
			System.out.printf("%-5s %-15s %-20s %-15s %-15s%n", "No.", "Description", "Status", "Date", "UserName");
			System.out.println("---------------------------------------------------------------------------------");

			int serialNumber = 1;
			for (Complaint complaint : complaints) {
				
				User user = UserController.userService.getUserById(complaint.getUserId());

				System.out.printf("%-5d %-15s %-20s %-15s %-15s%n", serialNumber++, complaint.getDescription(),
						complaint.getStatus(), complaint.getDate().toString(),
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
			System.out.println(str.complaintNotFound);
		else {
			String idComplaint = complaint.getIdComplaint();
			
			System.out.println(str.complaintUpdateList);
			System.out.println(str.complaintTobeUpdated);
			int choice = 0;
			while (true) {
				Helper.printFunction(str.enterChoice);
				choice = Helper.choiceInput();
				if (Helper.checkLimit(3, choice))
					break;
				Helper.printFunction(str.invalidInput);
			}
			switch (choice) {

			case 1: {
				String status=null;
				while(true)
				{
					System.out.println(str.enterStatus);
					status = scanner.nextLine();
					if(!Helper.ComplaintStatus(status.toLowerCase().trim()) )
						Helper.printFunction(str.notNullStatus);
					else 
						break;
				}
				complaintService.updateComplaint(idComplaint, "status", status);
				System.out.println(str.complaintUpdated);
				break;
			}
			case 2:
				return;
			case 3:
			{
				scanner.close();
				System.exit(0);
				return;
			}
			default:
				
				System.out.println(str.invalidInput);
			}
		}

	}

	public void deleteComplaint(String userId) throws SQLException, ClassNotFoundException {
		List<Complaint> complaints = complaintService.getComplaintById(userId);
		System.out.println(str.complaintToBeDeleted);
		listComplaints();
		int choice = 0;
		while (true) {
			Helper.printFunction(str.enterChoice);
			choice = Helper.choiceInput();
			if (Helper.checkLimit(complaints.size(), choice))
				break;
			Helper.printFunction(str.invalidInput);
		}
		Complaint selectedComplaint = complaints.get(choice - 1);
		String idComplaint = selectedComplaint.getIdComplaint();
		complaintService.deleteComplaint(idComplaint);
		System.out.println(str.complaintDeleted);
	}
	public Complaint getComplaint() throws ClassNotFoundException, SQLException {

		List<Complaint> complaints = complaintService.getAllComplaints();
		listComplaints();
		System.out.println(str.selectComplaint);
		int choice = 0;
		while (true) {
			Helper.printFunction(str.enterChoice);
			choice = Helper.choiceInput();
			if (Helper.checkLimit(complaints.size(), choice))
				break;
			Helper.printFunction(str.invalidInput);
		}

		return complaints.get(choice - 1);
	}

	public void deleteComplaintAdmin() throws SQLException, ClassNotFoundException {
		Complaint complaint = getComplaint();
		complaintService.deleteComplaint(complaint.getIdComplaint());
		System.out.println(str.complaintDeleted);
	}
}
