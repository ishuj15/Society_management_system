package com.controller;
import com.Model.Alert;
import com.service.AlertService;
import com.util.Helper;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AlertController {
    private AlertService alertService = new AlertService();

    public void createAlert() throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter alert message: ");
        String message = scanner.nextLine();
        System.out.print("Enter alert date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter target role (guard, resident, all): ");
        String targetRole = scanner.nextLine().toLowerCase(); 
        String alertId= Helper.generateUniqueId();

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
            // Print table headers
            System.out.printf("%-5s %-50s %-10s%n", "S.No", "Message", "Date");
            System.out.println("--------------------------------------------------");

            // Print each alert in a row with serial number
            int serialNumber = 1;
            for (Alert alert : alerts) {
                System.out.printf("%-5d %-50s %-10s%n",
                    serialNumber,
                    alert.getMessage(),
                    alert.getDate());
                serialNumber++;
            }
        }
        }

    public void listAlerts() throws SQLException, ClassNotFoundException {
    	List<Alert> alerts = alertService.getAllAlerts();
        
        if (alerts == null || alerts.isEmpty()) {
            System.out.println("No alerts found.");
        } else {
            // Print table headers
            System.out.printf("%-5s %-50s%n", "S.No", "Message");
            System.out.println("--------------------------------------------------");

            // Print each alert in a row with serial number
            int serialNumber = 1;
            for (Alert alert : alerts) {
                System.out.printf("%-5d %-50s%n",
                    serialNumber,
                    alert.getMessage());
                serialNumber++;
            }
        }   
    }

//    public void updateAlert(int idAlert) throws SQLException {
//        try (Scanner scanner = new Scanner(System.in)) {
//			Alert alert = alertService.getAlertById(idAlert);
//			if (alert != null) {
//			    System.out.print("Enter new alert message: ");
//			    String message = scanner.nextLine();
//			    System.out.print("Enter new alert date (yyyy-mm-dd): ");
//			    String dateStr = scanner.nextLine();
//
//			    alert.setMessage(message);
//			    alert.setDate(java.sql.Date.valueOf(dateStr));
//
//			    alertService.updateAlert(alert);
//			    System.out.println("Alert updated successfully!");
//			} else {
//			    System.out.println("Alert not found!");
//			}
//		}
   // }

    public void deleteAlert(String idAlert) throws SQLException, ClassNotFoundException {
        alertService.deleteAlert(idAlert);
        System.out.println("Alert deleted successfully!");
    }
}
