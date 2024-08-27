package com.controller;


public class MasterController {

    public final UserController userController;
    public final AttendanceController attendanceController;
    public final AlertController alertController;
    public final ComplaintController complaintController;
    public final FeedbackController feedbackController;
    public final NoticesController noticesController;
    public final ServicesController servicesController;
    public final VisitorController visitorController;

    public MasterController() {
        this.userController = new UserController();
        this.attendanceController = new AttendanceController();
        this.alertController = new AlertController();
        this.complaintController = new ComplaintController();
        this.feedbackController = new FeedbackController();
        this.noticesController = new NoticesController();
        this.servicesController = new ServicesController();
        this.visitorController = new VisitorController();
    }

    // Getter methods for controllers
    public UserController getUserController() {
        return userController;
    }

    public AttendanceController getAttendanceController() {
        return attendanceController;
    }

    public AlertController getAlertController() {
        return alertController;
    }

    public ComplaintController getComplaintController() {
        return complaintController;
    }

    public FeedbackController getFeedbackController() {
        return feedbackController;
    }

    public NoticesController getNoticesController() {
        return noticesController;
    }

    public ServicesController getServicesController() {
        return servicesController;
    }

    public VisitorController getVisitorController() {
        return visitorController;
    }
}

