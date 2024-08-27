package com.service;

import com.dao.ServicesDAO;
import com.Model.Services;

import java.sql.SQLException;
import java.util.List;

public class ServicesService {
    private final ServicesDAO servicesDAO;

    public ServicesService() {
        this.servicesDAO = new ServicesDAO();
    }

    public void addService(Services service) throws SQLException {
        servicesDAO.addEntity(service);
    }

    public Services getServiceById(int id) throws SQLException {
        return servicesDAO.getEntityById(id);
    }

    public List<Services> getAllServices() throws SQLException {
        return servicesDAO.getAllEntities();
    }

    public void updateService(Services service, int id) throws SQLException {
        servicesDAO.updateEntity(service, id);
    }

    public void deleteService(int id) throws SQLException {
        servicesDAO.deleteEntity(id);
    }
}
