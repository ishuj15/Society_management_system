package com.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import com.Model.User;
import com.Model.Visitor;
import com.service.VisitorService;
import com.util.Helper;
import com.util.str;

public class VisitorController {
	
	private final VisitorService visitorService = new VisitorService();
	@SuppressWarnings("resource")
	private static Scanner scanner = new Scanner(System.in);

	public void createVisitor(User user, String apr) throws SQLException, ClassNotFoundException {
		String contact, arrivalDate, depDate, time2, time;
		Time depTime;
		Date arrivalDate2, depDate2;
		Date date = new Date(System.currentTimeMillis());
		System.out.print("Enter visitor name: ");
		String name = scanner.nextLine();
		while (true) {
			System.out.print("Enter visitor contact: ");
			contact = scanner.nextLine();
			if (Helper.isPhoneNumberValid(contact))
				break;
			else
				System.out.println("Wrong Phone number ,Please try again");

		}
		while (true) {
			System.out.print("Enter visit date (yyyy-mm-dd): ");

			arrivalDate = scanner.nextLine();
			if (Helper.isValidDate(arrivalDate)) {
				arrivalDate2 = java.sql.Date.valueOf(arrivalDate);
				// System.out.println(arrivalDate2);
				if ((arrivalDate2.after(date) || arrivalDate2.equals(date))) {
					break;
				}

			} else
				System.out.println("Invalid date ,Please try again");
		}
		System.out.print("Enter visit purpose: ");
		String purpose = scanner.nextLine();
		while (true) {
			System.out.print("Enter visit arrival time(hh:mm:ss): ");
			time = scanner.nextLine();
			if (Helper.isValidTime(time))
				break;
			else
				System.out.println("Incorrect time format, PLease try again");
		}
		Time arrival_time = Time.valueOf(time);
		while (true) {
			System.out.print("Enter departure date (yyyy-mm-dd): ");
			depDate = scanner.nextLine();

			if (Helper.isValidDate(depDate)) {
				depDate2 = Date.valueOf(depDate);
				if ((depDate2.after(arrivalDate2) || arrivalDate2.equals(depDate2)))
					break;
			} else
				System.out.println("Invalid date ,Please try again");

		}
		while (true) {
			System.out.print("Enter visit departure time(hh:mm:ss): ");
			time2 = scanner.nextLine();
			if (Helper.isValidTime(time2)) {
				depTime = Time.valueOf(time2);
				if (arrivalDate.equals(depDate) && depTime.after(arrival_time)) {
					break;
				} else if (depDate2.after(arrivalDate2)) {
					break;
				}
			} else {
				System.out.println("Invalid time, please try again");
			}

		}
		Visitor visitor = new Visitor();
		visitor.setIdVisitor(Helper.generateUniqueId());
		visitor.setUserId(user.getIdUser());
		visitor.setName(name);
		visitor.setContactNo(contact);
		visitor.setPurpose(purpose);
		visitor.setDate(arrivalDate);
		visitor.setArrivalTime(time);
		visitor.setDepartureTime(time2);
		visitor.setApproved(apr);
		visitor.setDep_date(depDate);

		visitorService.addVisitor(visitor);
		
	}

	public void viewVisitor(String userId) throws SQLException, ClassNotFoundException {
		
		List<Visitor> visitors = visitorService.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("Visitor not found!");
			return;
		} else {
			System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", "S.No", "Name", "Contact",
					"Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Approval Req");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------"
							+ "------------------------------------------------------");
			int serialNumber = 1;
			for (Visitor visitor : visitors) {
				System.out.printf("%-5d %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", serialNumber++,
						visitor.getName(), visitor.getContactNo(), visitor.getDate(), visitor.getPurpose(),
						visitor.getArrivalTime(), visitor.getDepartureTime(), visitor.getDep_date(),
						visitor.isApproved());

			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------"
							+ "------------------------------------------------------");
		}
		
	}


	public void listVisitors() throws SQLException, ClassNotFoundException {
		List<Visitor>visitors=  visitorService.getAllVisitors();
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("Visitor not found!");

		} else {
			System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", "S.No", "Name", "Contact",
					"Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Approval Req");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------"
							+ "------------------------------------------------------");
			int serialNumber = 1;
			for (Visitor visitor : visitors) {
				System.out.printf("%-5d %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", serialNumber++,
						visitor.getName(), visitor.getContactNo(), visitor.getDate(), 
						visitor.getPurpose(), visitor.getArrivalTime(), visitor.getDepartureTime(),
						visitor.getDep_date(), visitor.isApproved());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------"
							+ "------------------------------------------------------");
		}
	}

	public void updateVisitor(String userId) throws SQLException, ClassNotFoundException {
		
		List<Visitor> visitors = visitorService.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("Visitor not found!");
			return;
		}
		System.out.println("Enter which visitor to update");
		viewVisitor(userId);
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(visitors.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		Visitor selectedVisitor = visitors.get(choice - 1);
		String visitorId = selectedVisitor.getIdVisitor();
		String str2 = """
				1) Name
				2) Contact Number
				3) Arrival Date
				4) Arrival Time
				5) Departure Date
				6) Departure time
				7) Exit""";
		System.out.print(str2);
		System.out.println("Which field do you want to update");
		int choice2 = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice2 = Helper.choiceInput();
			if (Helper.checkLimit(7, choice2))
				break;
			System.out.println("Invalid User, Please try again");

		}
		String ColumnToUpdate=null,NewValue=null;
		switch (choice2) {
		case 1: {
			System.out.print("Enter new name: ");
			NewValue = scanner.nextLine();
			ColumnToUpdate="name";
			System.out.println("Vistor updated");
			break;
		}

		case 2: {
			System.out.print("Enter new contact: ");
			 NewValue = scanner.nextLine();
			ColumnToUpdate="contact";
			System.out.println("Vistor updated");
			break;
		}
		case 3: {
			System.out.print("Enter new arrival date (yyyy-mm-dd): ");
			 NewValue= scanner.nextLine();
			ColumnToUpdate="date_of_arrival";
			System.out.println("Vistor updated");
			break;
		}
		case 4: {
			System.out.print("Enter arrival Time: ");
			 NewValue = scanner.nextLine();
			 ColumnToUpdate= "arrivalTime";
			//Time arrival_time = Time.valueOf(time);
			System.out.println("Vistor updated");
			break;
		}
		case 5: {
			System.out.print("Enter departure date: ");
			 NewValue = scanner.nextLine();
			 ColumnToUpdate="departure_date";
			System.out.println("Vistor updated");
			break;
		}
		case 6: {
			System.out.print("Enter departure time: ");
			 NewValue = scanner.nextLine();
			 ColumnToUpdate="departureTime";
			//Time departure_time = Time.valueOf(newValue);
			System.out.println("Vistor updated");
			break;
		}
		case 7:
			return;
		default: {
			System.out.println("Invalid input, please try again");
		}
		}		
		visitorService.updateVisitor(visitorId, ColumnToUpdate, NewValue);
	}

	public void deleteVisitor(String userId) throws SQLException, ClassNotFoundException {
		
		List<Visitor> visitors = visitorService.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("Visitor not found!");
			return;
		}
		System.out.println("Enter which visitor to delete");
		viewVisitor(userId);
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(visitors.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		Visitor selectedVisitor = visitors.get(choice - 1);
		String visitorId = selectedVisitor.getIdVisitor();
		visitorService.deleteVisitor(visitorId);
		System.out.println("Visitor deleted successfully!");
	}

	public void verifyVisitor(User user) throws SQLException, ClassNotFoundException {
		
		List<Visitor> visitors = visitorService.getVisitorById(user.getIdUser());
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("No visitors found!");
			return;
		}

		else {
			viewVisitor(user.getIdUser());
			System.out.println("Enter which visitor to verify");
			int choice = 0;
			while (true) {
				System.out.println(str.enterChoice);
				choice = Helper.choiceInput();
				if (Helper.checkLimit(visitors.size(), choice))
					break;
				System.out.println("Invalid input, Please try again");
			}
			Visitor selectedVisitor = visitors.get(choice - 1);
			String visitorId2 = selectedVisitor.getIdVisitor();
			visitorService.verifyVisitor(visitorId2);	
		}
	}

	public void getAllPendingReq(String userId) throws SQLException, ClassNotFoundException {
	
		 List<Visitor> visitors =visitorService.getAllPendingReq(userId, "Pending");
			if (visitors == null || visitors.isEmpty()) {
				System.out.println("Visitor not found!");
				return;
			} else {
				System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", "S.No", "Name", "Contact",
						"Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Approval Req");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------"
								+ "------------------------------------------------------");
				int serialNumber = 1;
				for (Visitor visitor : visitors) {
					System.out.printf("%-5d %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", serialNumber++,
							visitor.getName(), visitor.getContactNo(), visitor.getDate(), visitor.getPurpose(),
							visitor.getArrivalTime(), visitor.getDepartureTime(), visitor.getDep_date(),
							visitor.isApproved());

				}
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------"
								+ "------------------------------------------------------");
				System.out.println("Select which visitor to approve or deny");
				int choice = 0;
				while (true) {
					System.out.println(str.enterChoice);

					choice = Helper.choiceInput();
					if (Helper.checkLimit(visitors.size(), choice))
						break;
					System.out.println("Invalid User, Please try again");

				}

				Visitor selectedVisitor = visitors.get(choice - 1);
				String visitorId = selectedVisitor.getIdVisitor();
				
				System.out.println("Select 1 to approve");
				System.out.println("Select 2 to deny");
				int choice2 = 0;
				while (true) {
					System.out.println(str.enterChoice);

					choice2 = Helper.choiceInput();
					if (Helper.checkLimit(2, choice2))
						break;
					System.out.println("Invalid input, Please try again");
				}
				if (choice2 == 1) {
					visitorService.updateApprovalStatus(visitorId, "Approved");
					System.out.println("Request approved");
				} else {
					visitorService.updateApprovalStatus(visitorId, "Rejected");
					System.out.println("Request Denied");
				}
			}
		 
		 
	}

	public void deleteVisitorByAdmin() throws ClassNotFoundException, SQLException {
				 
		 List<Visitor> visitors = visitorService.getAllVisitors();
			if (visitors == null || visitors.isEmpty()) {
				System.out.println("Visitor not found!");
				return ;
			}
			listVisitors();
			System.out.println("Enter user  number which you need to delete");
			int choice = 0;
			while (true) {
				System.out.println(str.enterChoice);

				choice = Helper.choiceInput();
				if (Helper.checkLimit(visitors.size(), choice))
					break;
				System.out.println("Invalid User, Please try again");
			}
			Visitor selectedVisitor = visitors.get(choice - 1);
			String visitorId=selectedVisitor.getIdVisitor();
		 deleteVisitor(visitorId);
		
	}
}

;