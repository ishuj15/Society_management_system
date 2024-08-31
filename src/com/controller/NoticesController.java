package com.controller;

import com.Model.Notices;
import com.service.NoticesService;
import com.util.Helper;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoticesController {

    private final NoticesService noticesService = new NoticesService();

    public void createNotice() throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        System.out.print("Enter notice date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter target role (guard, resident, all): ");
        String targetRole = scanner.nextLine().toLowerCase(); 
        String noticeId= Helper.generateUniqueId();

        Notices notice = new Notices();
        notice.setIdNotices(noticeId);
        notice.setTitle(title);
        notice.setMessage(message);
        notice.setDate(java.sql.Date.valueOf(dateStr));
        notice.setTargetRole(targetRole);

        noticesService.addNotice(notice);
        System.out.println("Notice created successfully!");
    }

    public void viewNotice(String role) throws SQLException, ClassNotFoundException {
    	List<Notices> notices = noticesService.getNoticeByRole(role);
        
        if (notices == null || notices.isEmpty()) {
            System.out.println("No notices found for role: " + role);
        } else {
            
            System.out.printf("%-5s %-30s %-50s %-15s%n", "S.No", "Title", "Message", "Date");
            System.out.println("---------------------------------------------------------------------------------------------");

            // Print each notice in a row with serial number
            int serialNumber = 1;
            for (Notices notice : notices) {
                System.out.printf("%-5d %-30s %-50s %-15s%n",
                    serialNumber,
                    notice.getTitle(),
                    notice.getMessage(),
                    notice.getDate().toString());
                serialNumber++;
            }
        }

    }

    public void listNotices() throws SQLException, ClassNotFoundException {
    	List<Notices> notices = noticesService.getAllNotices();
        
        if (notices == null || notices.isEmpty()) {
            System.out.println("No notices found.");
        } else {
            // Print table headers
            System.out.printf("%-5s %-30s %-50s %-15s%n", "S.No", "Title", "Message", "Date");
            System.out.println("---------------------------------------------------------------------------------------------");

            // Print each notice in a row with serial number
            int serialNumber = 1;
            for (Notices notice : notices) {
                System.out.printf("%-5d %-30s %-50s %-15s%n",
                    serialNumber,
                    notice.getTitle(),
                    notice.getMessage(),
                    notice.getDate().toString());
                serialNumber++;
            }
        }
    }

//    public void updateNotice(String idNotice) throws SQLException, ClassNotFoundException {
//        Scanner scanner = new Scanner(System.in);
//        List<Notices> notice = noticesService.getNoticeByRole(idNotice);
//        if (notice != null) {
//            System.out.print("Enter new title: ");
//            String title = scanner.nextLine();
//            System.out.print("Enter new message: ");
//            String message = scanner.nextLine();
//            System.out.print("Enter new date (yyyy-mm-dd): ");
//            String dateStr = scanner.nextLine();
//
//            notice.setTitle(title);
//            notice.setMessage(message);
//            notice.setDate(java.sql.Date.valueOf(dateStr));
//
//         //   noticesService.updateNotice(notice, idNotice);
//            System.out.println("Notice updated successfully!");
//        } else {
//            System.out.println("Notice not found!");
//        }
//    }

    public void deleteNotice(String idNotice) throws SQLException, ClassNotFoundException {
        noticesService.deleteNotice(idNotice);
        System.out.println("Notice deleted successfully!");
    }
}
