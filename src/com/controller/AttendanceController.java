package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Model.Attendance;
import com.service.AttendanceService;
import com.util.Helper;

public class AttendanceController {
	private AttendanceService attendanceService = new AttendanceService();

	public void createAttendance(String userId) throws SQLException, ClassNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine(); // consume newline
		System.out.print("Enter attendance status: ");
		String status = scanner.nextLine();
		System.out.print("Enter attendance date (yyyy-mm-dd): ");
		String dateStr = scanner.nextLine();

		Attendance attendance = new Attendance();
		attendance.setIdAttendance(Helper.generateUniqueId());
		attendance.setUserId(userId);
		attendance.setStatus(status);
		attendance.setDate(java.sql.Date.valueOf(dateStr));

		attendanceService.addAttendance(attendance);
		System.out.println("Attendance created successfully!");
	}

	public void viewAttendance(String userID) throws SQLException, ClassNotFoundException {
		List<Attendance> attendance = attendanceService.getAttendanceById(userID);
		if (attendance.isEmpty()) {
			System.out.println("Attendance not found!");
		}

		else {
			System.out.printf("%-5s | %-15s | %-15s%n", "S.No", "Status", "Date");
			System.out.println("---------------------------------------------");
			int serialNumber = 1;
			for (Attendance attendances : attendance) {
				System.out.printf("%-5d | %-15s | %-15s%n", serialNumber, attendances.getStatus(),
						attendances.getDate());

				System.out.println("---------------------------------------------");

			}
		}
	}

	public List<Attendance> listAttendances() throws SQLException, ClassNotFoundException {
		List<Attendance> attendance = attendanceService.getAllAttendances();

		if (attendance.isEmpty()) {
			System.out.println("Attendance not found!");
		}

		else {
			System.out.printf("%-5s | %-15s | %-15s%n", "S.No", "Status", "Date");
			System.out.println("---------------------------------------------");
			int serialNumber = 1;
			for (Attendance attendances : attendance) {
				System.out.printf("%-5d | %-15s | %-15s%n", serialNumber, attendances.getStatus(),
						attendances.getDate());

				System.out.println("---------------------------------------------");

			}
		}
		return attendance;
	}

	public void viewAdmin() throws ClassNotFoundException, SQLException {
		List<Attendance> attendance = listAttendances();

	}
//    public void updateAttendance(String userId) throws SQLException, ClassNotFoundException {
//        Scanner scanner = new Scanner(System.in);
//        Attendance attendance = attendanceService.getAttendanceById(idAttendance);
//        if (attendance != null) {
////            System.out.print("Enter new user ID: ");
////            String userId = scanner.next();
//            scanner.nextLine(); 
//            System.out.print("Enter new attendance status: ");
//            String status = scanner.nextLine();
//            System.out.print("Enter new attendance date (yyyy-mm-dd): ");
//            String dateStr = scanner.nextLine();
//
//          //  attendance.setUserId(userId);
//            attendance.setStatus(status);
//            attendance.setDate(java.sql.Date.valueOf(dateStr));
//
//         //   attendanceService.updateAttendance(attendance);
//            System.out.println("Attendance updated successfully!");
//        } else {
//            System.out.println("Attendance not found!");
//        }
//    }

//    public void deleteAttendance(String idAttendance) throws SQLException {
//      //  attendanceService.deleteAttendance(idAttendance);
//        System.out.println("Attendance deleted successfully!");
//    }
}
