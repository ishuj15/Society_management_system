package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Services;

public class ServicesDAO extends GenericDAO<Services> {

    @Override
    protected String getTableName() {
        return "services";
    }

    @Override
    protected Services mapResultSetToEntity(ResultSet rs) throws SQLException {
        Services service = new Services();
        service.setIdServices(rs.getInt("idservices"));
        service.setUserId(rs.getInt("userId"));
        service.setServiceName(rs.getString("serviceName"));
        service.setDescription(rs.getString("description"));
        service.setStatus(rs.getString("status"));
        return service;
    }

    @Override
    protected void mapEntityToStatement(Services service, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, service.getIdServices());
        stmt.setInt(2, service.getUserId());
        stmt.setString(3, service.getServiceName());
        stmt.setString(4, service.getDescription());
        stmt.setString(5, service.getStatus());
    }
}
