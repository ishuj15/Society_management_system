package com.controller;

import com.Model.Services;
import com.service.ServicesService;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServicesController {

    private final ServicesService servicesService = new ServicesService();

    public void createService(Services service) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter service name: ");
        String name = scanner.nextLine();
        System.out.print("Enter service description: ");
        String description = scanner.nextLine();
//        System.out.print("Enter service date (yyyy-mm-dd): ");
//        String dateStr = scanner.nextLine();

        //Services service = new Services();
        service.setServiceName(name);
        service.setDescription(description);
     

        //servicesService.addService(service);
        System.out.println("Service created successfully!");
    }

    public void viewService(String idService) throws SQLException, ClassNotFoundException {
        Services service = servicesService.getServiceById(idService);
        if (service != null) {
            System.out.println("Service ID: " + service.getIdServices());
            System.out.println("Name: " + service.getServiceName());
            System.out.println("Description: " + service.getDescription());
           // System.out.println("Date: " + service.getDate());
        } else {
            System.out.println("Service not found!");
        }
    }

    public void listServices() throws SQLException, ClassNotFoundException {
        List<Services> services = servicesService.getAllServices();
        for (Services service : services) {
            System.out.println("Service ID: " + service.getIdServices() + ", Name: " + service.getServiceName() + ", Description: " + service.getDescription() /*+ ", Date: " + service.getDate()*/);
        }
    }

    public void updateService(String idService) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Services service = servicesService.getServiceById(idService);
        if (service != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
            System.out.print("Enter new date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine();

            service.setServiceName(name);
            service.setDescription(description);
           // service.setDate(java.sql.Date.valueOf(dateStr));

            //servicesService.updateService(service, idService);
            System.out.println("Service updated successfully!");
        } else {
            System.out.println("Service not found!");
        }
    }

    public void deleteService(String idService) throws SQLException, ClassNotFoundException {
        servicesService.deleteService(idService);
        System.out.println("Service deleted successfully!");
    }
}
