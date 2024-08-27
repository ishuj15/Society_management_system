package com.service;

import com.dao.AlertDAO;
import com.Model.Alert;

import java.sql.SQLException;
import java.util.List;

public class AlertService {
    private AlertDAO alertDAO = new AlertDAO();

    public void addAlert(Alert alert) throws SQLException {
        alertDAO.addEntity(alert);
    }

    public Alert getAlertById(int idAlert) throws SQLException {
        return alertDAO.getEntityById(idAlert);
    }

    public List<Alert> getAllAlerts() throws SQLException {
        return alertDAO.getAllEntities();
    }

    public void updateAlert(Alert alert) throws SQLException {
        alertDAO.updateEntity(alert,alert.getIdAlert());
    }

    public void deleteAlert(int idAlert) throws SQLException {
        alertDAO.deleteEntity(idAlert);
    }
}

