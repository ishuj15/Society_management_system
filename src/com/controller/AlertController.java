package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Model.Alert;
import com.service.AlertService;
import com.util.Helper;
import com.util.StringConstants;

public class AlertController {
	private AlertService alertService = new AlertService();
	Scanner scanner = new Scanner(System.in);

	public void createAlert() throws SQLException, ClassNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter alert message: ");
		String message = scanner.nextLine();
		// System.out.print("Enter alert date (yyyy-mm-dd): ");
		LocalDate dateStr = LocalDate.now();
		System.out.print("Enter target role (guard, resident, all): ");
		String targetRole = scanner.nextLine().toLowerCase();
		String alertId = Helper.generateUniqueId();

		Alert alert = new Alert();
		alert.setMessage(message);
		alert.setIdAlert(alertId);
		alert.setDate(java.sql.Date.valueOf(dateStr));
		alert.setTargetRole(targetRole);

		alertService.addAlert(alert);
		System.out.println("Alert created successfully!");
	}

	public void viewAlert(String idAlert) throws SQLException, ClassNotFoundException {
		Alert alert = alertService.getAlertById(idAlert);
		if (alert != null) {
			System.out.println("Alert ID: " + alert.getIdAlert());
			System.out.println("Message: " + alert.getMessage());
			System.out.println("Date: " + alert.getDate());
		} else {
			System.out.println("Alert not found!");
		}
	}

	public void viewAlertByRole(String role) throws SQLException, ClassNotFoundException {
		List<Alert> alerts = alertService.getAlertByRole(role);

		if (alerts == null || alerts.isEmpty()) {
			System.out.println("No alerts found for the specified role.");
		} else {
			System.out.printf("| %-5s | %-15s | %-50s | %-10s |%n", "S.No", "Role", "Message", "Date");
			System.out.println("----------------------------------------------------------------------------"
					+ "-------------------------------------");

			int serialNumber = 1;
			for (Alert alert : alerts) {
				System.out.printf("| %-5d | %-15s | %-50s | %-10s |%n", serialNumber, alert.getTargetRole(),
						alert.getMessage(), alert.getDate().toString());
				serialNumber++;
			}
			System.out.println("----------------------------------------------------------------------------"
					+ "-------------------------------------");

		}
	}

	public void listAlerts() throws SQLException, ClassNotFoundException {
		List<Alert> alerts = alertService.getAllAlerts();

		if (alerts == null || alerts.isEmpty()) {
			System.out.println("No alerts found.");
		} else {
			System.out.printf("| %-5s | %-15s | %-50s | %-10s |%n", "S.No", "Role", "Message", "Date");
			System.out.println("----------------------------------------------------------------------------"
					+ "-------------------------------------");

			int serialNumber = 1;
			for (Alert alert : alerts) {
				System.out.printf("| %-5d | %-15s | %-50s | %-10s |%n", serialNumber, alert.getTargetRole(),
						alert.getMessage(), alert.getDate().toString());
				serialNumber++;
			}
			System.out.println("----------------------------------------------------------------------------"
					+ "-------------------------------------");
		}

	}

	public void updateAlert() throws SQLException, ClassNotFoundException {
		try (Scanner scanner = new Scanner(System.in)) {
			Alert alert = getAlert();
			if (alert == null) {
				System.out.println("Alert not found!");
			} else {
				String idAlert = alert.getIdAlert();
				String str = """
						1) Message
						2) Date
						3) TagerRole
						4) Exit""";
				System.out.println(str);
				System.out.println("Select field that needs to be updated");
				int choice = 0;
				while (true) {
					System.out.println(StringConstants.enterChoice);

					choice = Helper.choiceInput();
					if (Helper.checkLimit(4, choice))
						break;
					System.out.println("Invalid User, Please try again");

				}

				switch (choice) {

				case 1: {
					System.out.print("Enter new message: ");
					String message = scanner.nextLine();
					alertService.updateAlert(idAlert, "message", message);
					System.out.println("Alert updated successfully!");
					break;
				}
				case 2: {
					System.out.print("Enter new date (yyyy-mm-dd): ");
					String dateStr = scanner.nextLine();
					alertService.updateAlert(idAlert, "date", dateStr);
					System.out.println("Alert updated successfully!");

					break;
				}
				case 3: {
					System.out.print("Enter target role: ");
					String role = scanner.nextLine().trim();
					alertService.updateAlert(idAlert, "targetRole", role);
					System.out.println("Alert updated successfully!");
					break;

				}
				case 4:
					return;
				default:
					System.out.println("Invalid Input , Please try again");
				}

			}
		}
	}

	public void deleteAlert() throws SQLException, ClassNotFoundException {
		Alert alert = getAlert();
		alertService.deleteAlert(alert.getIdAlert());
		System.out.println("Alert deleted successfully!");
	}

	public Alert getAlert() throws ClassNotFoundException, SQLException {

		List<Alert> alerts = alertService.getAllAlerts();
		listAlerts();
		System.out.println("Select  alert ");
		while (true) {
			int choice = 0;
			while (true) {
				System.out.println(StringConstants.enterChoice);

				choice = Helper.choiceInput();
				if (Helper.checkLimit(alerts.size(), choice))
					break;
				System.out.println("Invalid User, Please try again");

			}

			return alerts.get(choice - 1);

		}

	}
}
