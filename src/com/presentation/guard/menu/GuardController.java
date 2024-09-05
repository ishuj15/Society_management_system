package com.presentation.guard.menu;

import com.presentarion.resident.menu.AlertMenu;
import com.presentarion.resident.menu.ComplaintMenu;
import com.presentarion.resident.menu.NoticeMenu;
import com.presentarion.resident.menu.UserManagementMenu;

public class GuardController {
	public VisitorMenu visitorMenuObj;
	public NoticeMenu noticesMenuObj;
	public UserManagementMenu userManagementObj;
	public ComplaintMenu complaintMenuObj;
	public AlertMenu alertMenuObj;
	public AttendanceMenu attendanceMenuObj;

	public GuardController() {
		this.userManagementObj = new UserManagementMenu();
		this.visitorMenuObj = new VisitorMenu();
		this.noticesMenuObj = new NoticeMenu();
		this.alertMenuObj = new AlertMenu();
		this.complaintMenuObj = new ComplaintMenu();
		this.attendanceMenuObj = new AttendanceMenu();
	}

}
