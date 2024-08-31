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

    public void addService(Services service) throws SQLException, ClassNotFoundException {
        servicesDAO.addService(service);
    }

    public Services getServiceById(String id) throws SQLException, ClassNotFoundException {
        return servicesDAO.getServiceById(id);
    }

    public List<Services> getAllServices() throws SQLException, ClassNotFoundException {
        return servicesDAO.getAllServices();
    }
//    public void updateService(Services service, int id) throws SQLException {
//        servicesDAO.updateService(service, id);
//    }

    public void deleteService(String id) throws SQLException, ClassNotFoundException {
        servicesDAO.deleteService(id);
    }
}
