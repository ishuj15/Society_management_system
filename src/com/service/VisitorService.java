package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Model.Visitor;
import com.dao.VisitorDAO;
import com.util.Helper;
import com.util.StringConstants;

public class VisitorService {
	private final VisitorDAO visitorDAO;

	public VisitorService() {
		this.visitorDAO = new VisitorDAO();
	}

	public void addVisitor(Visitor visitor) throws SQLException, ClassNotFoundException {
		
		visitorDAO.addVisitor(visitor);
	}

	public void getVisitorById(String id) throws SQLException, ClassNotFoundException {
		List<Visitor> visitors = visitorDAO.getVisitorById(id);
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

	public void getAllVisitors() throws SQLException, ClassNotFoundException {
		List<Visitor>visitors= visitorDAO.getAllVisitors();
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
	public void updateVisitor(String userId)
			throws SQLException, ClassNotFoundException {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		List<Visitor> visitors = visitorDAO.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("Visitor not found!");
			return;
		}
		System.out.println("Enter which visitor to update");
		getVisitorById(userId);
		int choice = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(visitors.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		Visitor selectedVisitor = visitors.get(choice - 1);
		String visitorId = selectedVisitor.getIdVisitor();
		String str = """
				1) Name
				2) Contact Number
				3) Arrival Date
				4) Arrival Time
				5) Departure Date
				6) Departure time
				7) Exit""";
		System.out.print(str);
		System.out.println("Which field do you want to update");
		int choice2 = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

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
		visitorDAO.updateVisitor(visitorId, ColumnToUpdate, NewValue);
	}

	public void deleteVisitor(String userId) throws SQLException, ClassNotFoundException {
		List<Visitor> visitors = visitorDAO.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("Visitor not found!");
			return;
		}
		System.out.println("Enter which visitor to delete");
		getVisitorById(userId);
		int choice = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(visitors.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");

		}

		Visitor selectedVisitor = visitors.get(choice - 1);
		String visitorId = selectedVisitor.getIdVisitor();
		visitorDAO.deleteVisitor(visitorId);
	}

	public void verifyVisitor(String userId) throws SQLException, ClassNotFoundException {
		
		List<Visitor> visitors = visitorDAO.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("No visitors found!");
			return;
		}

		else {
			getVisitorById(userId);
			System.out.println("Enter which visitor to verify");
			int choice = 0;
			while (true) {
				System.out.println(StringConstants.enterChoice);

				choice = Helper.choiceInput();
				if (Helper.checkLimit(visitors.size(), choice))
					break;
				System.out.println("Invalid User, Please try again");

			}

			Visitor selectedVisitor = visitors.get(choice - 1);
			String visitorId2 = selectedVisitor.getIdVisitor();
		Visitor visitor = visitorDAO.verifyVisitor(visitorId2);
		if (visitor.isApproved().equals("Approved")) {
			System.out.println("Visitor verified.");
		} else if (visitor.isApproved().equals("Pending")) {
			System.out.println("Visitor Request Pending.");
		} else {
			System.out.println("Visitor rejected.");
		}
	}
	}
	public void updateApprovalStatus(String visitorId, String approved) throws SQLException, ClassNotFoundException {
		visitorDAO.updateApprovalStatus(visitorId, approved);
	}

	public void getAllPendingReq(String userId) throws SQLException, ClassNotFoundException {
		List<Visitor> visitors =visitorDAO.pendingRequests(userId, "Pending");
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
				System.out.println(StringConstants.enterChoice);

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
				System.out.println(StringConstants.enterChoice);

				choice2 = Helper.choiceInput();
				if (Helper.checkLimit(2, choice2))
					break;
				System.out.println("Invalid input, Please try again");
			}
			if (choice2 == 1) {
				updateApprovalStatus(visitorId, "Approved");
				System.out.println("Request approved");
			} else {
				updateApprovalStatus(visitorId, "Rejected");
				System.out.println("Request Denied");
			}
		}
	}

	public String deleteVisitorByAdmin() throws ClassNotFoundException, SQLException {
		List<Visitor> visitors = visitorDAO.getAllVisitors();
		if (visitors == null || visitors.isEmpty()) {
			System.out.println("Visitor not found!");
			return null;
		}
		getAllVisitors();
		System.out.println("Enter user  number which you need to delete");
		int choice = 0;
		while (true) {
			System.out.println(StringConstants.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(visitors.size(), choice))
				break;
			System.out.println("Invalid User, Please try again");
		}
		Visitor selectedVisitor = visitors.get(choice - 1);
		return selectedVisitor.getIdVisitor();
	}
}
