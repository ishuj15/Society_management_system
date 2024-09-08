package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Model.Services;
import com.Model.User;
import com.service.ServicesService;
import com.util.Helper;
import com.util.str;

public class ServicesController {
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);

	private final ServicesService servicesService = new ServicesService();

	public void createService(User user) throws SQLException, ClassNotFoundException {

		System.out.println(str.enterServiceName);
		String name = scanner.nextLine();
		System.out.print(str.enterServiceDescription);
		String description = scanner.nextLine();
		System.out.print(str.enterCurrentStatus);
		String status = scanner.nextLine();

		Services service = new Services();
		service.setIdServices(Helper.generateUniqueId());
		service.setUserId(user.getIdUser());
		service.setServiceName(name);
		service.setDescription(description);
		service.setStatus(status);
		servicesService.addService(service);
		System.out.println(str.serviceCreatedSuccessfully);
	}

	public void viewService(String idUser) throws SQLException, ClassNotFoundException {

		List<Services> services = servicesService.getServiceById(idUser);
		if (services.isEmpty())
			System.out.println(str.serviceNotFound);
		else {
			System.out.println("---------------------------------------------------------------"
					+ "------------------------------------------------------");
			System.out.printf("| %-5s | %-15s | %-20s | %-30s | %-10s |%n", "No.", "Username", "Service Name",
					"Description", "Status");
			System.out.println("---------------------------------------------------------------"
					+ "------------------------------------------------------");
			int serialNumber = 1;
			for (Services service : services) {
				User user = UserController.userService.getUserById(service.getUserId());
				System.out.printf("| %-5d | %-15s | %-20s | %-30s | %-10s |%n", serialNumber++, user.getUserName(), // Display
																													// the
																													// username
						service.getServiceName(), service.getDescription(), service.getStatus());
			}
			System.out.println("---------------------------------------------------------------"
					+ "------------------------------------------------------");

		}
	}

	public void listServices() throws SQLException, ClassNotFoundException {
		List<Services> services = servicesService.getAllServices();
		if (services.equals(null))
			System.out.println(str.serviceNotFound);
		else {
			System.out.println("---------------------------------------------------------------"
					+ "------------------------------------------------------");
			System.out.printf("| %-5s | %-15s | %-20s | %-30s | %-10s |%n", "No.", "Username", "Service Name",
					"Description", "Status");
			System.out.println("---------------------------------------------------------------"
					+ "------------------------------------------------------");

			int serialNumber = 1;
			for (Services service : services) {
				User user = UserController.userService.getUserById(service.getUserId());
				System.out.printf("| %-5d | %-15s | %-20s | %-30s | %-10s |%n", serialNumber++, user.getUserName(), // Display
																													// the
																													// username
						service.getServiceName(), service.getDescription(), service.getStatus());
			}
			System.out.println("---------------------------------------------------------------"
					+ "------------------------------------------------------");

		}
	}
//    	 int maxServiceNameLength = "Service Name".length();
//    	    int maxDescriptionLength = "Description".length();
//    	    
//    	    for (Services service : services) {
//    	        maxServiceNameLength = Math.max(maxServiceNameLength, service.getServiceName().length());
//    	        maxDescriptionLength = Math.max(maxDescriptionLength, service.getDescription().length());
//    	    }
//
//    	    // Calculate the format strings based on max lengths
//    	    String headerFormat = "| %-5s | %-" + maxServiceNameLength + "s | %-" + maxDescriptionLength + "s | %-10s |%n";
//    	    String rowFormat = "| %-5d | %-" + maxServiceNameLength + "s | %-" + maxDescriptionLength + "s | %-10s |%n";
//
//    	    // Calculate the total width of the table for the horizontal line
//    	    int tableWidth = 12 + maxServiceNameLength + maxDescriptionLength + 15; // 12 = 5 (No.) + 2 spaces + 2 vertical bars + 3 spaces, 15 = 10 (Status) + 5 (spaces and vertical bars)
//    	    
//    	    // Print the starting horizontal line
//    	    System.out.println("-".repeat(tableWidth));
//
//    	    // Print table headers
//    	    System.out.printf(headerFormat, "No.", "Service Name", "Description", "Status");
//    	    System.out.println("-".repeat(tableWidth));  // Adjust separator line based on column widths
//
//    	    // Print each service with a serial number
//    	    int serialNumber = 1;
//    	    for (Services service : services) {
//    	        System.out.printf(rowFormat, 
//    	                          serialNumber++, 
//    	                          service.getServiceName(), 
//    	                          service.getDescription(), 
//    	                          service.getStatus());
//    	    }
//
//    	    // Print ending horizontal line
//    	    System.out.println("-".repeat(tableWidth));
	// }

	public void updateService(String idUser) throws SQLException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		List<Services> service = servicesService.getServiceById(idUser);
		if (service.equals(null))
		{
			System.out.println(str.serviceNotFound);
			return;
			
		}
		viewService(idUser);
		System.out.println(str.selectServiceToModify);
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(service.size(), choice))
				break;
			System.out.println(str.invalidInput);
		}
		Services selectedService = service.get(choice - 1);
		if (selectedService.equals(null))
			System.out.println(str.serviceNotFound);
		else {
			System.out.println(str.serviceUpdateList);
			System.out.println(str.serviceToBeUpdated);
			int choice2 = 0;
			while (true) {
				System.out.println(str.enterChoice);
				choice = Helper.choiceInput();
				if (Helper.checkLimit(4, choice2))
					break;
				System.out.println(str.invalidInput);
			}
			switch (choice2) {
			case 1: {
				System.out.print(str.enterServiceName);
				String name = scanner.nextLine();
				servicesService.updateService(selectedService.getIdServices(), "serviceName", name);
				System.out.println();
				break;
			}
			case 2: {
				System.out.print(str.enterServiceDescription);
				String description = scanner.nextLine();
				servicesService.updateService(selectedService.getIdServices(), "description", description);
				System.out.println(str.serviceUpadtedSuccessfully);
				break;
			}
			case 3: {
				System.out.print(str.enterCurrentStatus);
				String status = scanner.nextLine();
				servicesService.updateService(selectedService.getIdServices(), "status", status);
				System.out.println(str.serviceUpadtedSuccessfully);
				break;
			}

			case 4:
				return;
			case 5:{
				scanner.close();
				System.exit(0);
				return;
			}
			default:
				System.out.println(str.invalidInput);
			}

		}
	}

	public void deleteService(String idUser) throws SQLException, ClassNotFoundException {
		List<Services> service = servicesService.getServiceById(idUser);
		if (service.equals(null))
		{
			System.out.println(str.serviceNotFound);
			return;
			
		}

		System.out.println(str.selectServiceThatNeedToBeDeleted);
		viewService(idUser);
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(service.size(), choice))
				break;
			System.out.println(str.invalidInput);

		}

		Services selectedService = service.get(choice - 1);

		servicesService.deleteService(selectedService.getIdServices());
		System.out.println(str.serviceDeleted);
	}

	public void deleteServiceByAdmin() throws SQLException, ClassNotFoundException {
		Services service = getService();
		servicesService.deleteService(service.getIdServices());
		System.out.println(str.serviceDeleted);
	}

	public Services getService() throws ClassNotFoundException, SQLException {

		List<Services> services = servicesService.getAllServices();
		if (services.equals(null))
		{
			System.out.println(str.serviceNotFound);
			return null;
			
		}
		listServices();
		System.out.println(str.selectService);
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);

			choice = Helper.choiceInput();
			if (Helper.checkLimit(services.size(), choice))
				break;
			System.out.println(str.invalidInput);

		}

		return services.get(choice - 1);
	}
}
