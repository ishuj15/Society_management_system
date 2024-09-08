package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Model.Alert;
import com.service.AlertService;
import com.util.Helper;
import com.util.str;
public class AlertController {
	private AlertService alertService = new AlertService();
	Scanner scanner = new Scanner(System.in);

	public void createAlert() throws SQLException, ClassNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String message=null;
		String targetRole=null;
		while(true)
		{
			Helper.printFunction(str.alertMessage);
			 message = scanner.nextLine();
			if(Helper.notNullCheck(message))
				Helper.printFunction(str.notNullMessage);
			else
				break;
		}
		
		LocalDate dateStr = LocalDate.now();
		while(true)
		{
			Helper.printFunction(str.alertTargetRole);
			 targetRole = scanner.nextLine().toLowerCase();
			 if(Helper.isValidTarget(targetRole.toLowerCase().trim()))
				 break;
			 else
				 System.out.println(str.validTargetRole);
		}
		String alertId = Helper.generateUniqueId();
		Alert alert = new Alert();
		alert.setMessage(message);
		alert.setIdAlert(alertId);
		alert.setDate(java.sql.Date.valueOf(dateStr));
		alert.setTargetRole(targetRole);
		alertService.addAlert(alert);
		Helper.printFunction(str.alertCreatedSuccessfully);
	}
	public void viewAlert(String idAlert) throws SQLException, ClassNotFoundException {
		Alert alert = alertService.getAlertById(idAlert);
		if (alert != null) {
			Helper.printFunction(str.alertTargetRolePrint + alert.getTargetRole());
			Helper.printFunction(str.alertMessagePrint+ alert.getMessage());
			Helper.printFunction(str.alertDatePrint+ alert.getDate());
		} else {
			Helper.printFunction(str.alertNotFound);	
		}
	}
	public void viewAlertByRole(String role) throws SQLException, ClassNotFoundException {
		List<Alert> alerts = alertService.getAlertByRole(role);

		if (alerts == null || alerts.isEmpty()) {
			System.out.println(str.alertNotFound);
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
			System.out.println(str.alertNotFound);
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
				System.out.println(str.alertNotFound);
			} else {
				String idAlert = alert.getIdAlert();
				
				System.out.println(str.alertUpdateList);
				System.out.println(str.alertUpdatefield);
				int choice = 0;
				while (true) {
					System.out.println(str.enterChoice);

					choice = Helper.choiceInput();
					if (Helper.checkLimit(4, choice))
						break;
					System.out.println(str.invalidInput);

				}
				switch (choice) {
				case 1: {
					String message ;
					while(true)
					{
						Helper.printFunction(str.alertMessage);
						 message = scanner.nextLine();
						if(Helper.notNullCheck(message))
							Helper.printFunction(str.notNullMessage);
						else
							break;
					}
					alertService.updateAlert(idAlert, "message", message);
					System.out.println(str.alertUpdated);
					break;
				}
				case 2: {
					String targetRole ;
					while(true)
					{
						Helper.printFunction(str.alertTargetRole);
						 targetRole = scanner.nextLine().toLowerCase().trim();
						 if(Helper.isValidTarget(targetRole))
							 break;
						 else
							 System.out.println(str.validTargetRole);
					}
					
					alertService.updateAlert(idAlert, "targetRole", targetRole);
					System.out.println(str.alertUpdated);
					break;
				}
				case 3: {
					return;
				}
				case 4:
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
	}

	public void deleteAlert() throws SQLException, ClassNotFoundException {
		Alert alert = getAlert();
		alertService.deleteAlert(alert.getIdAlert());
		System.out.println(str.alertDeleted);
	}

	public Alert getAlert() throws ClassNotFoundException, SQLException {

		List<Alert> alerts = alertService.getAllAlerts();
		listAlerts();
		System.out.println(str.selectAlert);
		while (true) {
			int choice = 0;
			while (true) {
				System.out.println(str.enterChoice);

				choice = Helper.choiceInput();
				if (Helper.checkLimit(alerts.size(), choice))
					break;
				System.out.println(str.invalidInput);

			}

			return alerts.get(choice - 1);

		}

	}
}
