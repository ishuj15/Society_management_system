package com.controller;
import com.Model.Alert;
import com.service.AlertService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AlertController {
    private AlertService alertService = new AlertService();

    public void createAlert() throws SQLException {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter alert message: ");
        String message = scanner.nextLine();
        System.out.print("Enter alert date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();

        Alert alert = new Alert();
        alert.setMessage(message);
        alert.setDate(java.sql.Date.valueOf(dateStr));

        alertService.addAlert(alert);
        System.out.println("Alert created successfully!");
    }

    public void viewAlert(int idAlert) throws SQLException {
        Alert alert = alertService.getAlertById(idAlert);
        if (alert != null) {
            System.out.println("Alert ID: " + alert.getIdAlert());
            System.out.println("Message: " + alert.getMessage());
            System.out.println("Date: " + alert.getDate());
        } else {
            System.out.println("Alert not found!");
        }
    }

    public void listAlerts() throws SQLException {
        List<Alert> alerts = alertService.getAllAlerts();
        for (Alert alert : alerts) {
            System.out.println("Alert ID: " + alert.getIdAlert() + ", Message: " + alert.getMessage());
        }
    }

    public void updateAlert(int idAlert) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
			Alert alert = alertService.getAlertById(idAlert);
			if (alert != null) {
			    System.out.print("Enter new alert message: ");
			    String message = scanner.nextLine();
			    System.out.print("Enter new alert date (yyyy-mm-dd): ");
			    String dateStr = scanner.nextLine();

			    alert.setMessage(message);
			    alert.setDate(java.sql.Date.valueOf(dateStr));

			    alertService.updateAlert(alert);
			    System.out.println("Alert updated successfully!");
			} else {
			    System.out.println("Alert not found!");
			}
		}
    }

    public void deleteAlert(int idAlert) throws SQLException {
        alertService.deleteAlert(idAlert);
        System.out.println("Alert deleted successfully!");
    }
}
