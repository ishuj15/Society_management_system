package com.controller;

import com.Model.Complaint;
import com.Model.Notices;
import com.Model.Services;
import com.Model.User;
import com.service.ServicesService;
import com.util.Helper;

import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServicesController {
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);

    private final ServicesService servicesService = new ServicesService();

    public void createService(User user) throws SQLException, ClassNotFoundException {
    	
        System.out.print("Enter service name: ");
        String name = scanner.nextLine();
        System.out.print("Enter service description: ");
        String description = scanner.nextLine();
        System.out.print("Enter current status ");
        String status = scanner.nextLine();

        Services service = new Services();
        service.setIdServices(Helper.generateUniqueId());
        service.setUserId(user.getIdUser());
        service.setServiceName(name);
        service.setDescription(description);
        service.setStatus(status);
        servicesService.addService(service);
        System.out.println("Service created successfully!");
    	
    }

    public void viewService(String idUser) throws SQLException, ClassNotFoundException {
    	
        List<Services> service = servicesService.getServiceById(idUser);
        if(service.isEmpty())
        	 System.out.println("Service not found!");
        else {
        System.out.printf("%-5s %-20s %-30s %-10s%n", "No.", "Service Name", "Description", "Status");
        System.out.println("-------------------------------------------------------------");

        // Print each service with a serial number
        int serialNumber = 1;
        for (Services services : service) {
            System.out.printf("%-5d %-20s %-30s %-10s%n", 
                              serialNumber++, 
                              services.getServiceName(), 
                              services.getDescription(), 
                              services.getStatus());
            System.out.println("-------------------------------------------------------------");
        }
        }
    }

    public void listServices() throws SQLException, ClassNotFoundException {
    	 List<Services> services = servicesService.getAllServices();

    	    // Print the table header
    	    System.out.printf("%-5s %-20s %-30s %-10s%n", "No.", "Service Name", "Description", "Status");
    	    System.out.println("-------------------------------------------------------------");

    	    // Print each service with a serial number
    	    int serialNumber = 1;
    	    for (Services service : services) {
    	        System.out.printf("%-5d %-20s %-30s %-10s%n", 
    	                          serialNumber++, 
    	                          service.getServiceName(), 
    	                          service.getDescription(), 
    	                          service.getStatus());
    	    }
    }

    public void updateService(String idUser) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<Services> service = servicesService.getServiceById(idUser);
        viewService(idUser);
        System.out.println("Select service which needs to modify ");
        int choice=scanner.nextInt();
        scanner.nextLine();
        if (choice < 1 || choice > service.size()) {
            System.out.println("Invalid choice, please try again.");
            return;
        }
    	Services selectedService = service.get(choice - 1);
    	if(selectedService.equals(null))
    		System.out.println("Service not found!");
    	else
    	{
    		String str="""
    				1) Service Name
					2) Description
					3) Status
    				4) exit
    				""";
    				 System.out.println(str);
       System.out.println("Select that needs to be updated");
       int choice2=scanner.nextInt();
       scanner.nextLine();
    				

		 switch(choice2)
       {
       case 1:
       {
    	   System.out.print("Enter new service name: ");
         String name = scanner.nextLine();
         servicesService.updateService(selectedService.getIdServices(), "serviceName", name);
         System.out.println("Service name updated successfully!");
    	   break;
       }
       case 2:
       {
    	   System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        servicesService.updateService(selectedService.getIdServices(), "description", description);
        System.out.println("Description updated successfully!");
    	   break;
       }
       case 3:
       {
    	   System.out.print("Enter current status ");
       String status = scanner.nextLine();
       servicesService.updateService(selectedService.getIdServices(), "status", status);
       System.out.println("Status updated successfully!");

    	   break;
       }
       
       case 4:
    	   return;
    	   default:
    		   System.out.println("Invalid Input , Please try again");
       }
		
    	}
    }

    public void deleteService(String idUser) throws SQLException, ClassNotFoundException {
    	List<Services> service = servicesService.getServiceById(idUser);
    	
    	System.out.println("Enter service  number which you need to delete");
    	viewService(idUser);
    	int choice= scanner.nextInt();
    	if (choice < 1 || choice > service.size()) {
            System.out.println("Invalid choice, please try again.");
            return;
        }
    	Services selectedService = service.get(choice - 1);

    	servicesService.deleteService(selectedService.getIdServices());
        System.out.println("Service deleted successfully!");
    }
    public void deleteServiceByAdmin() throws SQLException, ClassNotFoundException {
        Services service= getService();
    	servicesService.deleteService(service.getIdServices());
        System.out.println("Service deleted successfully!");
    }
    public Services getService() throws ClassNotFoundException, SQLException {
    	
        List<Services> services = servicesService.getAllServices();
        listServices();
        System.out.println("Select  notice ");
        int choice=scanner.nextInt();
        scanner.nextLine();
        return  services.get(choice-1);
   }
}
