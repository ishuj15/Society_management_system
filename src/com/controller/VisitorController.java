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
	private final UserController userController=new UserController();
	
	private final VisitorService visitorService = new VisitorService();
	@SuppressWarnings("resource")
	private static Scanner scanner = new Scanner(System.in);

	public void createVisitor(User user, String apr) throws SQLException, ClassNotFoundException {
		String contact, arrivalDate, depDate, time2, time;
		Time depTime;
		Date arrivalDate2, depDate2;
		Date date = new Date(System.currentTimeMillis());
		System.out.print(str.visitorName);
		String name = scanner.nextLine();
		while (true) {
			System.out.print(str.enterVisitorContact);
			contact = scanner.nextLine();
			if (Helper.isPhoneNumberValid(contact))
				break;
			else
				System.out.println(str.wrongPhoneNo);
		}
		while (true) {
			System.out.print(str.enterVisitorDate);
			arrivalDate = scanner.nextLine();
			if (Helper.isValidDate(arrivalDate)) {
				arrivalDate2 = java.sql.Date.valueOf(arrivalDate);
				if ((arrivalDate2.after(date) || arrivalDate2.equals(date))) {
					break;
				}
			} else
				System.out.println(str.invalidInput);
		}
		System.out.print(str.enterVisitPuspose);
		String purpose = scanner.nextLine();
		while (true) {
			System.out.print(str.enterArrivalTime);
			time = scanner.nextLine();
			if (Helper.isValidTime(time))
				break;
			else
				System.out.println(str.wrongTimeFormat);
		}
		Time arrival_time = Time.valueOf(time);
		while (true) {
			System.out.print(str.enterDepartureDate);
			depDate = scanner.nextLine();

			if (Helper.isValidDate(depDate)) {
				depDate2 = Date.valueOf(depDate);
				if ((depDate2.after(arrivalDate2) || arrivalDate2.equals(depDate2)))
					break;
			} else
				System.out.println(str.invalidInput);
		}
		while (true) {
			System.out.print(str.enterDepartureTime);
			time2 = scanner.nextLine();
			if (Helper.isValidTime(time2)) {
				depTime = Time.valueOf(time2);
				if (arrivalDate.equals(depDate) && depTime.after(arrival_time)) {
					break;
				} else if (depDate2.after(arrivalDate2)) {
					break;
				}
			} else {
				System.out.println(str.invalidInput);
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
			System.out.println(str.visitorNotFound);
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
			System.out.println(str.visitorNotFound);

		} else {
			System.out.printf("%-5s %-10s %-15s %-15s %-20s %-20s %-20s %-15s %-15s %-15s\n", "S.No", "Name", "Contact",
					"Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Status","UserName");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------"
							+ "------------------------------------------------------");
			int serialNumber = 1;
			
			for (Visitor visitor : visitors) {
				User user= userController.userService.getUserById(visitor.getUserId());
				System.out.printf("%-5d %-10s %-15s %-15s %-20s %-20s %-20s %-15s %-15s %-15s\n", serialNumber++,
						visitor.getName(), visitor.getContactNo(), visitor.getDate(), 
						visitor.getPurpose(), visitor.getArrivalTime(), visitor.getDepartureTime(),
						visitor.getDep_date(), visitor.isApproved(),user.getUserName());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------"
							+ "------------------------------------------------------");
		}
	}

	public void updateVisitor(String userId) throws SQLException, ClassNotFoundException {
		Date date = new Date(System.currentTimeMillis());
		List<Visitor> visitors = visitorService.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println(str.visitorNotFound);
			return;
		}
		System.out.println(str.selectVisitor);
		viewVisitor(userId);
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(visitors.size(), choice))
				break;
			System.out.println(str.invalidInput);
		}

		Visitor selectedVisitor = visitors.get(choice - 1);
		String visitorId = selectedVisitor.getIdVisitor();
		
		System.out.print(str.visitorUpdateList);
		System.out.println(str.visitorUpdateOption);
		int choice2 = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice2 = Helper.choiceInput();
			if (Helper.checkLimit(7, choice2))
				break;
			System.out.println(str.invalidInput);

		}
		String ColumnToUpdate=null,NewValue=null;
		switch (choice2) {
		case 1: {
			System.out.print(str.visitorName);
			NewValue = scanner.nextLine();
			ColumnToUpdate="name";
			
			break;
		}

		case 2: {
			
			 while (true) {
					System.out.print(str.enterVisitorContact);
					NewValue = scanner.nextLine();
					if (Helper.isPhoneNumberValid(NewValue))
						break;
					else
						System.out.println(str.wrongPhoneNo);
				}
			ColumnToUpdate="contact";
			
			break;
		}
		case 3: {
			
			 while (true) {
					System.out.print(str.enterVisitorDate);
					NewValue = scanner.nextLine();
					if (Helper.isValidDate(NewValue)) {
						 Date NewValue2 = java.sql.Date.valueOf(NewValue);
						if ((NewValue2.after(date) || NewValue2.equals(date))) {
							break;
						}
					} else
						System.out.println(str.invalidInput);
				}

			 
			ColumnToUpdate="date_of_arrival";
		
			break;
		}
		case 4: {
			System.out.print("Enter arrival Time: ");
			 NewValue = scanner.nextLine();
			 ColumnToUpdate= "arrivalTime";
			break;
		}
		case 5: {
			System.out.print("Enter departure date: ");
			 NewValue = scanner.nextLine();
			 ColumnToUpdate="departure_date";
			
			break;
		}
		case 6: {
			System.out.print("Enter departure time: ");
			 NewValue = scanner.nextLine();
			 ColumnToUpdate="departureTime";
			break;
		}
		case 7:
			return;
		default: {
			System.out.println(str.invalidInput);
		}
		}		
		visitorService.updateVisitor(visitorId, ColumnToUpdate, NewValue);
		System.out.println(str.visitorUpdatedSuccessfully);
	}

	public void deleteVisitor(String userId) throws SQLException, ClassNotFoundException {
		
		List<Visitor> visitors = visitorService.getVisitorById(userId);
		if (visitors == null || visitors.isEmpty()) {
			System.out.println(str.whichVisitorToDelete);
			return;
		}
		
		viewVisitor(userId);
		int choice = 0;
		while (true) {
			System.out.println(str.whichVisitorToDelete);
			choice = Helper.choiceInput();
			if (Helper.checkLimit(visitors.size(), choice))
				break;
			System.out.println(str.invalidInput);

		}

		Visitor selectedVisitor = visitors.get(choice - 1);
		String visitorId = selectedVisitor.getIdVisitor();
		visitorService.deleteVisitor(visitorId);
		System.out.println(str.visitorDeletedSuccessfully);
	}
	public void verifyVisitor(User user) throws SQLException, ClassNotFoundException {
		
		List<Visitor> visitors = visitorService.getVisitorById(user.getIdUser());
		if (visitors == null || visitors.isEmpty()) {
			System.out.println(str.visitorNotFound);
			return;
		}
		else {
			viewVisitor(user.getIdUser());
			System.out.println(str.selectVisitorToVerify);
			int choice = 0;
			while (true) {
				System.out.println(str.enterChoice);
				choice = Helper.choiceInput();
				if (Helper.checkLimit(visitors.size(), choice))
					break;
				System.out.println(str.invalidInput);
			}
			Visitor selectedVisitor = visitors.get(choice - 1);
			String visitorId2 = selectedVisitor.getIdVisitor();
			visitorService.verifyVisitor(visitorId2);	
		}
	}

	public void getAllPendingReq(String userId) throws SQLException, ClassNotFoundException {
		 List<Visitor> visitors =visitorService.getAllPendingReq(userId, str.pending);
			if (visitors == null || visitors.isEmpty()) {
				System.out.println(str.visitorNotFound);
				return;
			} else {
				System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", "S.No", "Name", "Contact",
						"Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Status");
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
				System.out.println(str.selectToDenyOrApprove);
				int choice = 0;
				while (true) {
					System.out.println(str.enterChoice);

					choice = Helper.choiceInput();
					if (Helper.checkLimit(visitors.size(), choice))
						break;
					System.out.println(str.invalidInput);
				}

				Visitor selectedVisitor = visitors.get(choice - 1);
				String visitorId = selectedVisitor.getIdVisitor();
				
				System.out.println(str.selectToApprove);
				System.out.println(str.selectToDeny);
				int choice2 = 0;
				while (true) {
					System.out.println(str.enterChoice);

					choice2 = Helper.choiceInput();
					if (Helper.checkLimit(2, choice2))
						break;
					System.out.println(str.invalidInput);
				}
				if (choice2 == 1) {
					visitorService.updateApprovalStatus(visitorId,str.approved );
					System.out.println(str.requestApproved);
				} else {
					visitorService.updateApprovalStatus(visitorId, str.rejected);
					System.out.println(str.requestDenied);
				}
			}	 
	}
	public void deleteVisitorByAdmin() throws ClassNotFoundException, SQLException {
				 
		 List<Visitor> visitors = visitorService.getAllVisitors();
			if (visitors == null || visitors.isEmpty()) {
				System.out.println(str.visitorNotFound);
				return ;
			}
			listVisitors();
			
			int choice = 0;
			while (true) {
				System.out.println(str.selectVisitorToDelete);
				choice = Helper.choiceInput();
				if (Helper.checkLimit(visitors.size(), choice))
					break;
				System.out.println(str.invalidInput);
			}
			Visitor selectedVisitor = visitors.get(choice - 1);
			String visitorId=selectedVisitor.getIdVisitor();
			visitorService.deleteVisitor(visitorId);
			System.out.println(str.visitorDeletedSuccessfully);
		
	}
}

;