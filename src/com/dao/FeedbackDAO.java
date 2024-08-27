package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Feedback;

public class FeedbackDAO extends GenericDAO<Feedback> {

    @Override
    protected String getTableName() {
        return "feedback";
    }

    @Override
    protected Feedback mapResultSetToEntity(ResultSet rs) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setIdFeedback(rs.getInt("Feedback_id"));
        feedback.setRating(rs.getInt("Rating"));
        feedback.setComment(rs.getString("Comment"));
        feedback.setDate(rs.getDate("Date"));
        feedback.setUserId(rs.getInt("userId"));
        return feedback;
    }

    @Override
    protected void mapEntityToStatement(Feedback feedback, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, feedback.getIdFeedback());
        stmt.setInt(2, feedback.getRating());
        stmt.setString(3, feedback.getComment());
        stmt.setDate(4, feedback.getDate());
        stmt.setInt(5, feedback.getUserId());
    }
}

//package com.dao;
//
//import com.Model.Feedback;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FeedbackDAO {
//    private static final String URL = "jdbc:mysql://localhost:3306/test";
//    private static final String USER = "root";
//    private static final String PASSWORD = "password";
//
//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    public void addFeedback(Feedback feedback) throws SQLException {
//        String sql = "INSERT INTO feedback (userId, message, date) VALUES (?, ?, ?)";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, feedback.getUserId());
//            stmt.setString(2, feedback.getComment());
//            stmt.setDate(3, new java.sql.Date(feedback.getDate().getTime()));
//            stmt.executeUpdate();
//        }
//    }
//
//    public Feedback getFeedbackById(int idFeedback) throws SQLException {
//        String sql = "SELECT * FROM feedback WHERE idfeedback = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idFeedback);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Feedback feedback = new Feedback();
//                feedback.setIdFeedback(rs.getInt("idfeedback"));
//                feedback.setUserId(rs.getInt("userId"));
//                feedback.setComment(rs.getString("message"));
//                feedback.setDate(rs.getDate("date"));
//                return feedback;
//            }
//        }
//        return null;
//    }
//
//    public List<Feedback> getAllFeedbacks() throws SQLException {
//        List<Feedback> feedbacks = new ArrayList<>();
//        String sql = "SELECT * FROM feedback";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Feedback feedback = new Feedback();
//                feedback.setIdFeedback(rs.getInt("idfeedback"));
//                feedback.setUserId(rs.getInt("userId"));
//                feedback.setComment(rs.getString("message"));
//                feedback.setDate(rs.getDate("date"));
//                feedbacks.add(feedback);
//            }
//        }
//        return feedbacks;
//    }
//
//    public void updateFeedback(Feedback feedback) throws SQLException {
//        String sql = "UPDATE feedback SET userId = ?, message = ?, date = ? WHERE idfeedback = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, feedback.getUserId());
//            stmt.setString(2, feedback.getComment());
//            stmt.setDate(3, new java.sql.Date(feedback.getDate().getTime()));
//            stmt.setInt(4, feedback.getIdFeedback());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void deleteFeedback(int idFeedback) throws SQLException {
//        String sql = "DELETE FROM feedback WHERE idfeedback = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idFeedback);
//            stmt.executeUpdate();
//        }
//    }
//}
//
