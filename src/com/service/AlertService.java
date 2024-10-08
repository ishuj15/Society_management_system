package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Model.Alert;
import com.dao.AlertDAO;

public class AlertService {
	private AlertDAO alertDAO = new AlertDAO();

	public void addAlert(Alert alert) throws SQLException, ClassNotFoundException {
		alertDAO.addAlert(alert);
	}

	public Alert getAlertById(String idAlert) throws SQLException, ClassNotFoundException {
		return alertDAO.getAlertById(idAlert);
	}

	public List<Alert> getAlertByRole(String role) throws SQLException, ClassNotFoundException {
		return alertDAO.getAlertByRole(role);
	}

	public List<Alert> getAllAlerts() throws SQLException, ClassNotFoundException {
		return alertDAO.getAllAlerts();
	}

	public void updateAlert(String idAlert, String ColumnToUpdate, String NewValue)
			throws SQLException, ClassNotFoundException {
		alertDAO.updateAlert(idAlert, ColumnToUpdate, NewValue);
	}

	public void deleteAlert(String idAlert) throws SQLException, ClassNotFoundException {
		alertDAO.deleteAlert(idAlert);
	}
}
