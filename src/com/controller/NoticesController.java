package com.controller;

import com.Model.Notices;
import com.service.NoticesService;
import com.util.Helper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NoticesController {

	 Scanner scanner = new Scanner(System.in);
    private final NoticesService noticesService = new NoticesService();

    public void createNotice() throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter message: ");
        String message = scanner.nextLine().trim();
       // System.out.print("Enter notice date (yyyy-mm-dd): ");
        LocalDate currentDate = LocalDate.now();
        System.out.print("Enter target role (guard, resident, all): ");
        String targetRole = scanner.nextLine().trim().toLowerCase(); 
        String noticeId= Helper.generateUniqueId();

        Notices notice = new Notices();
        notice.setIdNotices(noticeId);
        notice.setTitle(title);
        notice.setMessage(message);
        notice.setDate(java.sql.Date.valueOf(currentDate));
        notice.setTargetRole(targetRole);

        noticesService.addNotice(notice);
        System.out.println("Notice created successfully!");
    }

    public void viewNotice(String role) throws SQLException, ClassNotFoundException {
    	List<Notices> notices = noticesService.getNoticeByRole(role);
        
        if (notices == null || notices.isEmpty()) {
            System.out.println("No notices found for role: " + role);
        } else {
            
        	System.out.printf("| %-5s | %-15s | %-30s | %-50s | %-15s |%n", "S.No",  "Title", "Message", "Date","Role");
        	System.out.println("--------------------------------------------------------------------------------------------------------------------"
        	                 + "---------------------------------------------------------------");

        	int serialNumber = 1;
        	for (Notices notice : notices) {
        	    System.out.printf("| %-5d | %-15s | %-30s | %-50s | %-15s |%n",
        	        serialNumber,
        	         
        	        notice.getTitle(),
        	        notice.getMessage(),
        	        notice.getDate().toString(),
        	        notice.getTargetRole());
        	    serialNumber++;
        	}
        	System.out.println("--------------------------------------------------------------------------------------------------------------------"
        	                 + "---------------------------------------------------------------");
        }

    }

    public void listNotices() throws SQLException, ClassNotFoundException {
    	List<Notices> notices = noticesService.getAllNotices();
        
        if (notices == null || notices.isEmpty()) {
            System.out.println("No notices found.");
        } else {
        	System.out.printf("| %-5s | %-15s | %-30s | %-50s | %-15s |%n", "S.No",  "Title", "Message", "Date","Role");
        	System.out.println("--------------------------------------------------------------------------------------------------------------------"
        	                 + "---------------------------------------------------------------");

        	int serialNumber = 1;
        	for (Notices notice : notices) {
        	    System.out.printf("| %-5d | %-15s | %-30s | %-50s | %-15s |%n",
        	        serialNumber,
        	         
        	        notice.getTitle(),
        	        notice.getMessage(),
        	        notice.getDate().toString(),
        	        notice.getTargetRole());
        	    serialNumber++;
        	}
        	System.out.println("--------------------------------------------------------------------------------------------------------------------"
        	                 + "---------------------------------------------------------------");	
        }
    }

    public void updateNotice() throws SQLException, ClassNotFoundException {
       Notices notice=getNotice();
      
       if(notice==null)
    	   System.out.println("Notice not found!");   
       else
       {
    	   String idNotice= notice.getIdNotices();
       String str="""
       		1) Title
       		2) Message
       		3) Date
       		4) TagerRole
       		5) Exit
       		""";
       System.out.println(str);
       System.out.println("Select that needs to be updated");
       int choice=0;
       try {
			choice= scanner.nextInt();
			}catch (InputMismatchException e) {
               System.out.println("Invalid input. Please enter a number.");
               scanner.nextLine();
               }
       switch(choice)
       {
       case 1:
       {
    	   System.out.print("Enter new title: ");
         String title = scanner.nextLine();
         noticesService.updateNotice(idNotice, "title", title);
         System.out.println("Notice updated successfully!");
    	   break;
       }
       case 2:
       {
    	   System.out.print("Enter new message: ");
        String message = scanner.nextLine();
        noticesService.updateNotice(idNotice, "message", message);
        System.out.println("Notice updated successfully!");
    	   break;
       }
       case 3:
       {
    	   System.out.print("Enter new date (yyyy-mm-dd): ");
       String dateStr = scanner.nextLine();
       noticesService.updateNotice(idNotice, "date", dateStr);
       System.out.println("Notice updated successfully!");

    	   break;
       }
       case 4:
       {
    	   System.out.print("Enter target role: ");
           String role = scanner.nextLine();
           noticesService.updateNotice(idNotice, "targetRole", role);
           System.out.println("Notice updated successfully!");
       	   break;
    	   
       }
       case 5:
    	   return;
    	   default:
    		   System.out.println("Invalid Input , Please try again");
       }
       }
       
 
    
    }

    public void deleteNotice() throws SQLException, ClassNotFoundException {
    	Notices notice=getNotice();
        noticesService.deleteNotice(notice.getIdNotices());
        System.out.println("Notice deleted successfully!");
    }
    public Notices getNotice() throws ClassNotFoundException, SQLException {
    	
         List<Notices> notices = noticesService.getAllNotices();
         listNotices();
         System.out.println("Select  notice ");
         int choice=0;
         
         try {
 			choice= scanner.nextInt();
 			}catch (InputMismatchException e) {
                 System.out.println("Invalid input. Please enter a number.");
                 scanner.nextLine();
                  }
         return  notices.get(choice-1);
    }
    
}
