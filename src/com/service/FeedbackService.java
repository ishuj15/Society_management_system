package com.service;

import com.dao.FeedbackDAO;
import com.Model.Feedback;

import java.sql.SQLException;
import java.util.List;

public class FeedbackService {
    private FeedbackDAO feedbackDAO = new FeedbackDAO();

    public void addFeedback(Feedback feedback) throws SQLException, ClassNotFoundException {
        feedbackDAO.addFeedback(feedback);
    }
    public Feedback getFeedbackById(String idFeedback) throws SQLException, ClassNotFoundException {
        return feedbackDAO.getFeedbackById(idFeedback);
    }

    public List<Feedback> getAllFeedbacks() throws SQLException, ClassNotFoundException {
        return feedbackDAO.getAllFeedbacks();
    }

//    public void updateFeedback(Feedback feedback) throws SQLException {
//        feedbackDAO.updateFeedback(feedback,feedback.getIdFeedback());
//    }

    public void deleteFeedback(String idFeedback) throws SQLException, ClassNotFoundException {
        feedbackDAO.deleteFeedback(idFeedback);
    }
}

