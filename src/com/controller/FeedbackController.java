//package com.controller;
//
//import com.Model.Feedback;
//import com.service.FeedbackService;
//import com.util.Helper;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Scanner;
//
//public class FeedbackController {
//    private FeedbackService feedbackService = new FeedbackService();
//
//    public void createFeedback(Feedback feedback) throws SQLException {
//        @SuppressWarnings("resource")
//		Scanner scanner = new Scanner(System.in);
//
//        scanner.nextLine(); // consume newline
//        System.out.print("Enter comment ");
//        String message = scanner.nextLine();
//        System.out.print("Enter feedback date (yyyy-mm-dd): ");
//        String dateStr = scanner.nextLine();
//        System.out.println("Give ratings");
//        int rating=scanner.nextInt();
//        String feedbackId= Helper.generateUniqueId();
//
//        feedback.setIdFeedback(feedbackId);
//        feedback.setUserId(userId);
//        feedback.setComment(message);
//        feedback.setDate(java.sql.Date.valueOf(dateStr));
//
//        feedbackService.addFeedback(feedback);
//        System.out.println("Feedback created successfully!");
//    }
//
////    public void viewFeedback(int idFeedback) throws SQLException {
////        Feedback feedback = feedbackService.getFeedbackById(idFeedback);
////        if (feedback != null) {
////            System.out.println("Feedback ID: " + feedback.getIdFeedback());
////            System.out.println("User ID: " + feedback.getUserId());
////            System.out.println("Message: " + feedback.getComment());
////            System.out.println("Date: " + feedback.getDate());
////        } else {
////            System.out.println("Feedback not found!");
////        }
////    }
////
////    public void listFeedbacks() throws SQLException {
////        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
////        for (Feedback feedback : feedbacks) {
////            System.out.println("Feedback ID: " + feedback.getIdFeedback() + ", User ID: " + feedback.getUserId() + ", Message: " + feedback.getComment());
////        }
////    }
////
////    public void updateFeedback(int idFeedback) throws SQLException {
////        Scanner scanner = new Scanner(System.in);
////        Feedback feedback = feedbackService.getFeedbackById(idFeedback);
////        if (feedback != null) {
////            System.out.print("Enter new user ID: ");
////            int userId = scanner.nextInt();
////            scanner.nextLine(); // consume newline
////            System.out.print("Enter new feedback message: ");
////            String message = scanner.nextLine();
////            System.out.print("Enter new feedback date (yyyy-mm-dd): ");
////            String dateStr = scanner.nextLine();
////
////            feedback.setUserId(userId);
////            feedback.setComment(message);
////            feedback.setDate(java.sql.Date.valueOf(dateStr));
////
////            feedbackService.updateFeedback(feedback);
////            System.out.println("Feedback updated successfully!");
////        } else {
////            System.out.println("Feedback not found!");
////        }
////    }
////
////    public void deleteFeedback(int idFeedback) throws SQLException {
////        feedbackService.deleteFeedback(idFeedback);
////        System.out.println("Feedback deleted successfully!");
////    }
//}
//
