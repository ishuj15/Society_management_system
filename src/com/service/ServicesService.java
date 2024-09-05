package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Model.Services;
import com.dao.ServicesDAO;

public class ServicesService {
	private final ServicesDAO servicesDAO;

	public ServicesService() {
		this.servicesDAO = new ServicesDAO();
	}

	public void addService(Services service) throws SQLException, ClassNotFoundException {
		servicesDAO.addService(service);
	}

	public List<Services> getServiceById(String idUser) throws SQLException, ClassNotFoundException {
		return servicesDAO.getServiceById(idUser);
	}

	public List<Services> getAllServices() throws SQLException, ClassNotFoundException {
		return servicesDAO.getAllServices();
	}

	public void updateService(String serviceId, String ColumnToUpdate, String NewValue)
			throws SQLException, ClassNotFoundException {
		servicesDAO.updateService(serviceId, ColumnToUpdate, NewValue);
	}

	public void deleteService(String id) throws SQLException, ClassNotFoundException {
		servicesDAO.deleteService(id);
	}
}
