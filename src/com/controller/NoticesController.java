package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Model.Notices;
import com.service.NoticesService;
import com.util.Helper;
import com.util.str;

public class NoticesController {

	Scanner scanner = new Scanner(System.in);
	private final NoticesService noticesService = new NoticesService();

	public void createNotice() throws SQLException, ClassNotFoundException {
		String message=null,title=null;
		String targetRole=null;
		while(true)
		{
			System.out.print(str.noticeTitle);
			title = scanner.nextLine().trim();
			if(Helper.notNullCheck(title))
				System.out.println(str.notNullNoticeTitle);
			else
				break;
		}
		while(true)
		{
			System.out.println(str.noticeMessage);
			
			message = scanner.nextLine().trim();
			if(Helper.notNullCheck(message))
				System.out.println(str.notNullMessage);
			else
				break;
		}
		
		LocalDate currentDate = LocalDate.now();
		while(true)
		{
			System.out.print(str.alertTargetRole);
			 targetRole = scanner.nextLine().trim().toLowerCase();
			if(Helper.isValidTarget(targetRole))
				break;
		}
		
		String noticeId = Helper.generateUniqueId();

		Notices notice = new Notices();
		notice.setIdNotices(noticeId);
		notice.setTitle(title);
		notice.setMessage(message);
		notice.setDate(java.sql.Date.valueOf(currentDate));
		notice.setTargetRole(targetRole);

		noticesService.addNotice(notice);
		System.out.println(str.noticeCreatedSuccefully);
	}

	public void viewNotice(String role) throws SQLException, ClassNotFoundException {
		List<Notices> notices = noticesService.getNoticeByRole(role);

		if (notices == null || notices.isEmpty()) {
			System.out.println("for role: " + role);
		} else {

			System.out.printf("| %-5s | %-15s | %-30s | %-50s | %-15s |%n", "S.No", "Title", "Message", "Date", "Role");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------");

			int serialNumber = 1;
			for (Notices notice : notices) {
				System.out.printf("| %-5d | %-15s | %-30s | %-50s | %-15s |%n", serialNumber,

						notice.getTitle(), notice.getMessage(), notice.getDate().toString(), notice.getTargetRole());
				serialNumber++;
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------");
		}

	}

	public void listNotices() throws SQLException, ClassNotFoundException {
		List<Notices> notices = noticesService.getAllNotices();

		if (notices == null || notices.isEmpty()) {
			System.out.println(str.noticeNotFound);
		} else {
			System.out.printf("| %-5s | %-15s | %-30s | %-50s | %-15s |%n", "S.No", "Title", "Message", "Date", "Role");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------");

			int serialNumber = 1;
			for (Notices notice : notices) {
				System.out.printf("| %-5d | %-15s | %-30s | %-50s | %-15s |%n", serialNumber,

						notice.getTitle(), notice.getMessage(), notice.getDate().toString(), notice.getTargetRole());
				serialNumber++;
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------");
		}
	}

	public void updateNotice() throws SQLException, ClassNotFoundException {
		Notices notice = getNotice();

		if (notice == null)
			System.out.println(str.noticeNotFound);
		else {
			String idNotice = notice.getIdNotices();
			
			System.out.println(str.noticeUpdateList);
			System.out.println(str.selectUpdate);
			int choice = 0;
			while (true) {
				System.out.println(str.enterChoice);

				choice = Helper.choiceInput();
				if (Helper.checkLimit(5, choice))
					break;
				System.out.println(str.invalidInput);
			}

			switch (choice) {
			case 1: {
				String title ;
				while(true)
				{
					System.out.print(str.noticeTitle);
					title = scanner.nextLine().trim();
					if(Helper.notNullCheck(title))
						break;
					else
						System.out.println(str.notNullNoticeTitle);
				}
				noticesService.updateNotice(idNotice, "title", title);
				System.out.println(str.noticeUpdatedSuccessfully);
				break;
			}
			case 2: {
				String message;
				while(true)
				{
					System.out.println(str.noticeMessage);
					
					message = scanner.nextLine().trim();
					if(Helper.notNullCheck(message))
						break;
					else
						System.out.println(str.notNullMessage);		
				}
				noticesService.updateNotice(idNotice, "message", message);
				System.out.println(str.noticeUpdatedSuccessfully);
				break;
			}
			case 3: {
				String targetRole ;
				while(true)
				{
					System.out.print(str.alertTargetRole);
					 targetRole = scanner.nextLine().trim().toLowerCase();
					if(Helper.isValidTarget(targetRole))
						break;
					
				}
				noticesService.updateNotice(idNotice, "targetRole", targetRole);
				System.out.println(str.noticeUpdatedSuccessfully);
				break;
			}
			case 4: {
				return;
			}
			case 5:
			{
				scanner.close();
				System.exit(0);
				return;
			}
			default:
				System.out.println(str.invalidInput);
			}
		}
	}
	public void deleteNotice() throws SQLException, ClassNotFoundException {
		Notices notice = getNotice();
		noticesService.deleteNotice(notice.getIdNotices());
		System.out.println(str.noticeDeleteSuccessfully);
	}
	public Notices getNotice() throws ClassNotFoundException, SQLException {

		List<Notices> notices = noticesService.getAllNotices();
		listNotices();
		System.out.println(str.selectNotice);
		int choice = 0;
		while (true) {
			System.out.println(str.enterChoice);
			choice = Helper.choiceInput();
			if (Helper.checkLimit(notices.size(), choice))
				break;
			System.out.println(str.invalidInput);
		}
		return notices.get(choice - 1);
	}
}
