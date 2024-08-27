package com.service;

import com.dao.FeedbackDAO;
import com.Model.Feedback;

import java.sql.SQLException;
import java.util.List;

public class FeedbackService {
    private FeedbackDAO feedbackDAO = new FeedbackDAO();

    public void addFeedback(Feedback feedback) throws SQLException {
        feedbackDAO.addEntity(feedback);
    }
    public Feedback getFeedbackById(int idFeedback) throws SQLException {
        return feedbackDAO.getEntityById(idFeedback);
    }

    public List<Feedback> getAllFeedbacks() throws SQLException {
        return feedbackDAO.getAllEntities();
    }

    public void updateFeedback(Feedback feedback) throws SQLException {
        feedbackDAO.updateEntity(feedback,feedback.getIdFeedback());
    }

    public void deleteFeedback(int idFeedback) throws SQLException {
        feedbackDAO.deleteEntity(idFeedback);
    }
}

