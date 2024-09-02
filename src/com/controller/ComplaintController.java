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
     
      //  System.out.print("Enter complaint status: ");
        String status = "nil";
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
    	    for (Complaint complaint : complaints) {
    	        System.out.printf("| %-5d | %-30s | %-15s | %-10s |\n",
    	                serialNumber++,
    	                complaint.getDescription(),
    	                complaint.getStatus(),
    	                complaint.getDate());
    	    }  
    	    System.out.println("-------------------------------------------------------------");
    }

    public void listComplaints() throws SQLException, ClassNotFoundException {
        List<Complaint> complaints = complaintService.getAllComplaints();
        if (complaints.isEmpty()) {
            System.out.println("No complaints found.");
        } else {
            System.out.printf("%-5s %-15s %-20s %-15s%n", "No.", "Description", "Status", "Date");
            System.out.println("---------------------------------------------------------------");

            int serialNumber = 1;
            for (Complaint complaint : complaints) {
                System.out.printf("%-5d %-15s %-20s %-15s%n",
                        serialNumber++,
                        complaint.getDescription(),
                        complaint.getStatus(),
                        complaint.getDate());
                System.out.println("---------------------------------------------------------------");
            }
        }    }

    public void updateComplaint() throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        Complaint complaint= getComplaint();
        if(complaint==null)
        	System.out.println("Complaint not found!");
        else
        {
        	 String idComplaint= complaint.getIdComplaint();
             String str="""
             		1) Status
             		2) Exit
             		""";
             System.out.println(str);
             System.out.println("Select that needs to be updated");
             int choice=scanner.nextInt();
             scanner.nextLine();
             switch(choice)
             {
             
             case 1:
             {
          	   System.out.print("Enter status: ");
              String status = scanner.nextLine();
              complaintService.updateComplaint(idComplaint, "status", status);
              System.out.println("Complaint updated successfully!");
          	   break;
             }
             case 2:
          	   return;
          	   default:
          		   System.out.println("Invalid Input , Please try again");
             }
        }
    
   }

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
    public  Complaint getComplaint() throws ClassNotFoundException, SQLException {
    	
        List<Complaint> complaints = complaintService.getAllComplaints();
        listComplaints();
        System.out.println("Select Complaint ");
        int choice=scanner.nextInt();
        scanner.nextLine();
        return  complaints.get(choice-1);
   }
    public void deleteComplaintAdmin() throws SQLException, ClassNotFoundException  {
    	Complaint complaint = getComplaint();
        complaintService.deleteComplaint(complaint.getIdComplaint());
        System.out.println("Complaint deleted successfully!");
    }
}
