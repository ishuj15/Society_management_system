package com.controller;

import com.Model.Complaint;
import com.service.ComplaintService;
import com.util.Helper;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ComplaintController {
    private ComplaintService complaintService = new ComplaintService();
    Scanner scanner= new Scanner(System.in);


    public void createComplaint( Complaint complaint) throws SQLException, ClassNotFoundException {
    	
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter complaint date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter complaint description: ");
        String description = scanner.nextLine();
     
        System.out.print("Enter complaint status: ");
        String status = scanner.nextLine();
        String idComplaint= Helper.generateUniqueId();
        complaint.setIdComplaint(idComplaint);
       // complaint.setUserId(complaint.getUserId());
        complaint.setDescription(description);
        complaint.setDate(java.sql.Date.valueOf(dateStr));
        complaint.setStatus(status);

        complaintService.addComplaint(complaint);
        System.out.println("Complaint created successfully!");
       
    }

    public void viewComplaint(String userId) throws SQLException, ClassNotFoundException {
    	 List<Complaint> complaints = complaintService.getAllComplaints();
    	 System.out.println("-------------------------------------------------------------");
    	    System.out.printf("| %-5s | %-30s | %-15s | %-10s |\n", "S.No", "Description", "Status", "Date");
    	    System.out.println("-------------------------------------------------------------");

    	    int serialNumber = 1;

    	    // Print each complaint in a row with a serial number
    	    for (Complaint complaint : complaints) {
    	        System.out.printf("| %-5d | %-30s | %-15s | %-10s |\n",
    	                serialNumber++,
    	                complaint.getDescription(),
    	                complaint.getStatus(),
    	                complaint.getDate());
    	    }

    	    // Print the table footer
    	    System.out.println("-------------------------------------------------------------");
    }

    public void listComplaints() throws SQLException, ClassNotFoundException {
        List<Complaint> complaints = complaintService.getAllComplaints();
        for (Complaint complaint : complaints) {
            System.out.println(" Description: " + complaint.getDescription()+
            		" Complaint status"+ complaint.getStatus()+"Date"+complaint.getDate());
        }
    }

//    public void updateComplaint(String idComplaint) throws SQLException, ClassNotFoundException {
//        @SuppressWarnings("resource")
//		Scanner scanner = new Scanner(System.in);
//        Complaint complaint = complaintService.getComplaintById(idComplaint);
//        if (complaint != null) {
////            System.out.print("Enter new user ID: ");
////            int userId = scanner.nextInt();
//            scanner.nextLine(); // consume newline
//            System.out.print("Enter new complaint description: ");
//            String description = scanner.nextLine();
//            System.out.print("Enter new complaint date (yyyy-mm-dd): ");
//            String dateStr = scanner.nextLine();
//            System.out.print("Enter new complaint status: ");
//            String status = scanner.nextLine();
//            String complaintId= Helper.generateUniqueId();
//            complaint.setIdComplaint(complaintId);
//            complaint.setUserId(complaint.getUserId());
//            complaint.setDescription(description);
//            complaint.setDate(java.sql.Date.valueOf(dateStr));
//            complaint.setStatus(status);
//
//           // complaintService.updateComplaint(complaint);
//            System.out.println("Complaint updated successfully!");
//        } else {
//            System.out.println("Complaint not found!");
//        }
    
//    }

    public void deleteComplaint(String userId) throws SQLException, ClassNotFoundException  {
    	List<Complaint> complaints = complaintService.getComplaintById(userId);
    	System.out.println("Enter complaint  number which you need to delete");
    	viewComplaint(userId);
    	int choice= scanner.nextInt();
    	if (choice < 1 || choice > complaints.size()) {
            System.out.println("Invalid choice, please try again.");
            return;
        }
    	Complaint selectedComplaint = complaints.get(choice - 1);
        String idComplaint = selectedComplaint.getIdComplaint();
        complaintService.deleteComplaint(idComplaint);
        System.out.println("Complaint deleted successfully!");
    }
}
