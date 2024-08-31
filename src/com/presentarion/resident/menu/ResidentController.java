package com.presentarion.resident.menu;

public class ResidentController {

	public UserManagementMenu userManagementObj;
	public  VisitorMenu visitorMenuObj;
	public ServicesMenu servicesMenuObj;
	public AlertMenu alertMenuObj;
	public NoticeMenu noticesMenuObj;
	public ComplaintMenu complaintMenuObj;
	public ResidentController()
	{
		this.userManagementObj = new UserManagementMenu();
	       this.servicesMenuObj = new ServicesMenu();
	       this.visitorMenuObj = new VisitorMenu();
	       this.noticesMenuObj = new NoticeMenu();
	       this.alertMenuObj = new AlertMenu();
	       this.complaintMenuObj = new ComplaintMenu();	
	}
}
