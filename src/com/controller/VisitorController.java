package com.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

import com.Model.User;
import com.Model.Visitor;
import com.service.VisitorService;
import com.util.Helper;

public class VisitorController {
	
	private final VisitorService visitorService = new VisitorService();
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);

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
		visitorService.getVisitorById(userId);
	}


	public void listVisitors() throws SQLException, ClassNotFoundException {
		 visitorService.getAllVisitors();
	}

	public void updateVisitor(String userId) throws SQLException, ClassNotFoundException {
		visitorService.updateVisitor(userId);
	}

	public void deleteVisitor(String userId) throws SQLException, ClassNotFoundException {
		visitorService.deleteVisitor(userId);
		
	}

	public void verifyVisitor(User user) throws SQLException, ClassNotFoundException {
		visitorService.verifyVisitor(user.getIdUser());
	}

	public void getAllPendingReq(String userId) throws SQLException, ClassNotFoundException {
		 visitorService.getAllPendingReq(userId);	
	}

	public void deleteVisitorByAdmin() throws ClassNotFoundException, SQLException {
		 String id= visitorService.deleteVisitorByAdmin();
		 deleteVisitor(id);
		
	}
}

;