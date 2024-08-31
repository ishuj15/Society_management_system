package com.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Feedback;

public class FeedbackDAO extends GenericDAO<Feedback> {

    @Override
    protected Feedback mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setIdFeedback(resultSet.getString("Feedback_id"));
        feedback.setUserId(resultSet.getString("userId"));
        feedback.setComment(resultSet.getString("comment"));
        feedback.setRating(resultSet.getInt("rating"));
        feedback.setDate(resultSet.getDate("date"));
        
        return feedback;
    }

    public boolean addFeedback(Feedback feedback) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO Feedback (feedbackId, userId,  rating,comment,date) VALUES ('%s','%s','%d','%s', %s)",
            feedback.getIdFeedback(), feedback.getUserId(), feedback.getComment(), feedback.getRating());
        return executeQuery(sqlQuery);
    }

    public Feedback getFeedbackById(String feedbackId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Feedback WHERE feedbackId = \"" + feedbackId + "\"";
        return executeGetQuery(selectSQL);
    }

    public List<Feedback> getAllFeedbacks() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Feedback";
        return executeGetAllQuery(selectSQL);
    }

    public boolean deleteFeedback(String feedbackId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Feedback WHERE feedbackId = \"" + feedbackId + "\"";
        return executeQuery(deleteSQL);
    }
}
