package com.service;

import com.dao.AlertDAO;
import com.Model.Alert;

import java.sql.SQLException;
import java.util.List;

public class AlertService {
    private AlertDAO alertDAO = new AlertDAO();

    public void addAlert(Alert alert) throws SQLException, ClassNotFoundException {
        alertDAO.addAlert(alert);
    }

    public Alert getAlertById(String idAlert) throws SQLException, ClassNotFoundException {
        return alertDAO.getAlertById(idAlert);
    }
    public List<Alert> getAlertByRole(String idAlert) throws SQLException, ClassNotFoundException {
        return alertDAO.getAlertByRole(idAlert);
    }
    
    public List<Alert> getAllAlerts() throws SQLException, ClassNotFoundException {
        return alertDAO.getAllAlerts();
    }

//    public void updateAlert(Alert alert) throws SQLException {
//        alertDAO.updateAlert(alert,alert.getIdAlert());
//    }

    public void deleteAlert(String idAlert) throws SQLException, ClassNotFoundException {
        alertDAO.deleteAlert(idAlert);
    }
}
