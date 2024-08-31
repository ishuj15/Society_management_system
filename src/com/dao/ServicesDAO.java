package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Services;

public class ServicesDAO extends GenericDAO<Services> {

    @Override
    protected Services mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Services service = new Services();
        service.setIdServices(resultSet.getString("idServices"));
        service.setUserId(resultSet.getString("userId"));
        service.setServiceName(resultSet.getString("serviceName"));
        service.setDescription(resultSet.getString("Description"));
        service.setStatus(resultSet.getString("status"));
        return service;
    }

    public boolean addService(Services service) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO Service (idServices, userId, serviceName, Description,status) VALUES ('%s','%s','%s','%s','%s')",
            service.getIdServices(), service.getUserId(), service.getServiceName(), service.getDescription(), service.getStatus());
        return executeQuery(sqlQuery);
    }

    public Services getServiceById(String serviceId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Service WHERE serviceId = \"" + serviceId + "\"";
        return executeGetQuery(selectSQL);
    }

    public List<Services> getAllServices() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Service";
        return executeGetAllQuery(selectSQL);
    }

    public boolean deleteService(String serviceId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Service WHERE serviceId = \"" + serviceId + "\"";
        return executeQuery(deleteSQL);
    }
}