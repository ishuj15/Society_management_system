package com.controller;

import com.Model.Visitor;
import com.service.VisitorService;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class VisitorController {

    private final VisitorService visitorService = new VisitorService();

    public void createVisitor() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter visitor name: ");
        String name = scanner.nextLine();
//        System.out.print("Enter visitor contact: ");
//        String contact = scanner.nextLine();
        System.out.print("Enter visit date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter visit purpose: ");
        String purpose = scanner.nextLine();
        System.out.print("Enter visit arrival time: ");
        String time=scanner.nextLine();
        Time arrival_time= Time.valueOf(time) ;
        System.out.print("Enter visit departure time: ");
        String time2=scanner.nextLine();
        Time departure_time= Time.valueOf(time2);
        //System.out.print("Enter visit purpose: ");
        

        Visitor visitor = new Visitor();
        visitor.setName(name);
      // visitor.setContact(contact);
       
        visitor.setPurpose(purpose);
        visitor.setDate(java.sql.Date.valueOf(dateStr));
        visitor.setArrivalTime(arrival_time);
        visitor.setDepartureTime(departure_time);

        visitorService.addVisitor(visitor);
        System.out.println("Visitor created successfully!");
    }

    public void viewVisitor(int idVisitor) throws SQLException {
        Visitor visitor = visitorService.getVisitorById(idVisitor);
        if (visitor != null) {
            System.out.println("Visitor ID: " + visitor.getIdVisitor());
            System.out.println("Name: " + visitor.getName());
          //  System.out.println("Contact: " + visitor.getContact());
            System.out.println("Date: " + visitor.getDate());
            System.out.println("Purpose: " + visitor.getPurpose());
        } else {
            System.out.println("Visitor not found!");
        }
    }

    public void listVisitors() throws SQLException {
        List<Visitor> visitors = visitorService.getAllVisitors();
        for (Visitor visitor : visitors) {
          //  System.out.println("Visitor ID: " + visitor.getIdVisitor() + ", Name: " + visitor.getName() + ", Contact: " + visitor.getContact() + ", Date: " + visitor.getDate() + ", Purpose: " + visitor.getPurpose());
        }
    }

    public void updateVisitor(int idVisitor) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Visitor visitor = visitorService.getVisitorById(idVisitor);
        if (visitor != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
//            System.out.print("Enter new contact: ");
//            String contact = scanner.nextLine();
            System.out.print("Enter new date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine();
            System.out.print("Enter new purpose: ");
            String purpose = scanner.nextLine();

            visitor.setName(name);
           // visitor.setContact(contact);
            visitor.setDate(java.sql.Date.valueOf(dateStr));
            visitor.setPurpose(purpose);

            visitorService.updateVisitor(visitor, idVisitor);
            System.out.println("Visitor updated successfully!");
        } else {
            System.out.println("Visitor not found!");
        }
    }

    public void deleteVisitor(int idVisitor) throws SQLException {
        visitorService.deleteVisitor(idVisitor);
        System.out.println("Visitor deleted successfully!");
    }
}

