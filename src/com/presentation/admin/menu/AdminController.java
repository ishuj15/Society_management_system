package com.presentation.admin.menu;

public class AdminController {
	 public AccontManagementAdmin accountManage;
	    public NoticeManagementAdmin noticeManage;
	    public AlertManagementAdmin alertManage;
	    public VisitorManagementAdmin visitorManage;
	    public ServicesManagementAdmin servicesManage;
	    public ComplaintManagementAdmin complaintManage;
	    public AttendanceManagementAdmin attendanceManage;
	    public AdminController()
	    {
	    	this.accountManage= new AccontManagementAdmin();
	        this.noticeManage= new NoticeManagementAdmin();
	        this.alertManage= new AlertManagementAdmin();
	        this.visitorManage= new VisitorManagementAdmin();
	        this.servicesManage= new ServicesManagementAdmin();
	        this.complaintManage= new ComplaintManagementAdmin();
	        this.attendanceManage= new AttendanceManagementAdmin();
	    }


}
