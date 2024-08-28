package com.controller;

import com.Model.Complaint;
import com.service.ComplaintService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ComplaintController {
    private ComplaintService complaintService = new ComplaintService();
//	private Object masterController;
    

    public void createComplaint(Complaint complaint) throws SQLException {
    	
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter user ID: ");
//        int userId = scanner.nextInt();
       // scanner.nextLine(); // consume newline
        System.out.print("Enter complaint description: ");
        String description = scanner.nextLine();
        System.out.print("Enter complaint date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter complaint status: ");
        String status = scanner.nextLine();

       // Complaint complaint = new Complaint();
        //System.out.println(complaint.getUserId());
        complaint.setUserId(complaint.getUserId());
        complaint.setDescription(description);
        complaint.setDate(java.sql.Date.valueOf(dateStr));
        complaint.setStatus(status);

        complaintService.addComplaint(complaint);
        System.out.println("Complaint created successfully!");
    }

    public void viewComplaint(int userId) throws SQLException {
    	 List<Complaint> complaints = complaintService.getComplaintById(userId);
         if (complaints.isEmpty()) {
             System.out.println("No complaints found!");
         } else {
             for (Complaint complaint : complaints) {
                 System.out.println("Complaint ID: " + complaint.getIdComplaint());
                 System.out.println("Description: " + complaint.getDescription());
                 System.out.println("Date: " + complaint.getDate());
                 System.out.println("Status: " + complaint.getStatus());
                 System.out.println("-----");
             }
         
      }
//        Complaint complaint = complaintService.getComplaintById(idComplaint);
//        if (complaint != null) {
//            System.out.println("Complaint ID: " + complaint.getIdComplaint());
//            System.out.println("User ID: " + complaint.getUserId());
//            System.out.println("Description: " + complaint.getDescription());
//            System.out.println("Date: " + complaint.getDate());
//            System.out.println("Status: " + complaint.getStatus());
//        } else {
//            System.out.println("Complaint not found!");
//        }
    }

    public void listComplaints() throws SQLException {
        List<Complaint> complaints = complaintService.getAllComplaints();
        for (Complaint complaint : complaints) {
            System.out.println("Complaint ID: " + complaint.getIdComplaint() + ", User ID: " + complaint.getUserId() + ", Description: " + complaint.getDescription());
        }
    }

    public void updateComplaint(int idComplaint) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Complaint complaint = complaintService.getComplaintById(idComplaint);
        if (complaint != null) {
            System.out.print("Enter new user ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter new complaint description: ");
            String description = scanner.nextLine();
            System.out.print("Enter new complaint date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine();
            System.out.print("Enter new complaint status: ");
            String status = scanner.nextLine();

            complaint.setUserId(userId);
            complaint.setDescription(description);
            complaint.setDate(java.sql.Date.valueOf(dateStr));
            complaint.setStatus(status);

            complaintService.updateComplaint(complaint);
            System.out.println("Complaint updated successfully!");
        } else {
            System.out.println("Complaint not found!");
        }
    }

    public void deleteComplaint(int idComplaint) throws SQLException {
        complaintService.deleteComplaint(idComplaint);
        System.out.println("Complaint deleted successfully!");
    }
}
