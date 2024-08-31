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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        System.out.print("Enter notice date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        String noticeId= Helper.generateUniqueId();

        Notices notice = new Notices();
        notice.setIdNotices(noticeId);
        notice.setTitle(title);
        notice.setMessage(message);
        notice.setDate(java.sql.Date.valueOf(dateStr));

        noticesService.addNotice(notice);
        System.out.println("Notice created successfully!");
    }

    public void viewNotice(String idNotice) throws SQLException, ClassNotFoundException {
        Notices notice = noticesService.getNoticeById(idNotice);
        if (notice != null) {
           // System.out.println("Notice ID: " + notice.getIdNotices());
            System.out.println("Title: " + notice.getTitle());
            System.out.println("Message: " + notice.getMessage());
            System.out.println("Date: " + notice.getDate());
        } else {
            System.out.println("Notice not found!");
        }
    }

    public void listNotices() throws SQLException, ClassNotFoundException {
        List<Notices> notices = noticesService.getAllNotices();
        if(notices==null)
        	System.out.println("No notices found");
        for (Notices notice : notices) {
            System.out.println("Notice ID: " + notice.getIdNotices() + ", Title: " + notice.getTitle() + ", Message: " + notice.getMessage() + ", Date: " + notice.getDate());
        }
    }

    public void updateNotice(String idNotice) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Notices notice = noticesService.getNoticeById(idNotice);
        if (notice != null) {
            System.out.print("Enter new title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new message: ");
            String message = scanner.nextLine();
            System.out.print("Enter new date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine();

            notice.setTitle(title);
            notice.setMessage(message);
            notice.setDate(java.sql.Date.valueOf(dateStr));

         //   noticesService.updateNotice(notice, idNotice);
            System.out.println("Notice updated successfully!");
        } else {
            System.out.println("Notice not found!");
        }
    }

    public void deleteNotice(String idNotice) throws SQLException, ClassNotFoundException {
        noticesService.deleteNotice(idNotice);
        System.out.println("Notice deleted successfully!");
    }
}
