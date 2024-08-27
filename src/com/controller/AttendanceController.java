package com.controller;

import com.Model.Attendance;
import com.service.AttendanceService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AttendanceController {
    private AttendanceService attendanceService = new AttendanceService();

    public void createAttendance() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter attendance status: ");
        String status = scanner.nextLine();
        System.out.print("Enter attendance date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();

        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendance.setStatus(status);
        attendance.setDate(java.sql.Date.valueOf(dateStr));

        attendanceService.addAttendance(attendance);
        System.out.println("Attendance created successfully!");
    }

    public void viewAttendance(int idAttendance) throws SQLException {
        Attendance attendance = attendanceService.getAttendanceById(idAttendance);
        if (attendance != null) {
            System.out.println("Attendance ID: " + attendance.getIdAttendance());
            System.out.println("User ID: " + attendance.getUserId());
            System.out.println("Status: " + attendance.getStatus());
            System.out.println("Date: " + attendance.getDate());
        } else {
            System.out.println("Attendance not found!");
        }
    }

    public void listAttendances() throws SQLException {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        for (Attendance attendance : attendances) {
            System.out.println("Attendance ID: " + attendance.getIdAttendance() + ", User ID: " + attendance.getUserId() + ", Status: " + attendance.getStatus());
        }
    }

    public void updateAttendance(int idAttendance) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Attendance attendance = attendanceService.getAttendanceById(idAttendance);
        if (attendance != null) {
            System.out.print("Enter new user ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter new attendance status: ");
            String status = scanner.nextLine();
            System.out.print("Enter new attendance date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine();

            attendance.setUserId(userId);
            attendance.setStatus(status);
            attendance.setDate(java.sql.Date.valueOf(dateStr));

            attendanceService.updateAttendance(attendance);
            System.out.println("Attendance updated successfully!");
        } else {
            System.out.println("Attendance not found!");
        }
    }

    public void deleteAttendance(int idAttendance) throws SQLException {
        attendanceService.deleteAttendance(idAttendance);
        System.out.println("Attendance deleted successfully!");
    }
}

