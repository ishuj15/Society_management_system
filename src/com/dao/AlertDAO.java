package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Alert;

public class AlertDAO extends GenericDAO<Alert> {

    @Override
    protected Alert mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Alert alert = new Alert();
        alert.setIdAlert(resultSet.getString("alertId"));
        alert.setMessage(resultSet.getString("message"));
        alert.setDate(resultSet.getDate("date"));
        alert.setTargetRole(resultSet.getString("targetRole"));
       
        return alert;
    }

    public boolean addAlert(Alert alert) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO Alert (alertId,  message, date,targetRole) VALUES ('%s','%s','%s','%s')",
            alert.getIdAlert(), alert.getMessage(), alert.getDate(), alert.getTargetRole());
        return executeQuery(sqlQuery);
    }

    public Alert getAlertById(String alertId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Alert WHERE alertId = \"" + alertId + "\"";
        return executeGetQuery(selectSQL);
    }
    public List<Alert> getAlertByRole(String role) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Alert WHERE targetRole = \"" + role + "\"";
        return executeGetAllQuery(selectSQL);
    }

    public List<Alert> getAllAlerts() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Alert";
        return executeGetAllQuery(selectSQL);
    }

    public boolean deleteAlert(String alertId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Alert WHERE alertId = \"" + alertId + "\"";
        return executeQuery(deleteSQL);
    }
}
