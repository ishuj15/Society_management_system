package com.dao;

import com.Model.Complaint;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplaintDAO extends GenericDAO<Complaint> {

    @Override
    protected String getTableName() {
        return "complaint";
    }

    @Override
    protected Complaint mapResultSetToEntity(ResultSet rs) throws SQLException {
        Complaint complaint = new Complaint();
        complaint.setIdComplaint(rs.getInt("idComplaint"));
        complaint.setUserId(rs.getInt("userId"));
        complaint.setDescription(rs.getString("description"));
        complaint.setDate(rs.getDate("date"));
        complaint.setStatus(rs.getString("status"));
        return complaint;
    }

    @Override
    protected void mapEntityToStatement(Complaint complaint, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, complaint.getIdComplaint());
        stmt.setInt(2, complaint.getUserId());
        stmt.setString(3, complaint.getDescription());
        stmt.setDate(4, new java.sql.Date(complaint.getDate().getTime()));
        stmt.setString(5, complaint.getStatus());
    }
}

//import com.Model.Complaint;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ComplaintDAO {
//    private static final String URL = "jdbc:mysql://localhost:3306/test";
//    private static final String USER = "root";
//    private static final String PASSWORD = "password";
//
//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    public void addComplaint(Complaint complaint) throws SQLException {
//        String sql = "INSERT INTO complaint (userId, description, date, status) VALUES (?, ?, ?, ?)";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, complaint.getUserId());
//            stmt.setString(2, complaint.getDescription());
//            stmt.setDate(3, new java.sql.Date(complaint.getDate().getTime()));
//            stmt.setString(4, complaint.getStatus());
//            stmt.executeUpdate();
//        }
//    }
//
//    public Complaint getComplaintById(int idComplaint) throws SQLException {
//        String sql = "SELECT * FROM complaint WHERE idcomplaint = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idComplaint);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Complaint complaint = new Complaint();
//                complaint.setIdComplaint(rs.getInt("idcomplaint"));
//                complaint.setUserId(rs.getInt("userId"));
//                complaint.setDescription(rs.getString("description"));
//                complaint.setDate(rs.getDate("date"));
//                complaint.setStatus(rs.getString("status"));
//                return complaint;
//            }
//        }
//        return null;
//    }
//
//    public List<Complaint> getAllComplaints() throws SQLException {
//        List<Complaint> complaints = new ArrayList<>();
//        String sql = "SELECT * FROM complaint";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Complaint complaint = new Complaint();
//                complaint.setIdComplaint(rs.getInt("idcomplaint"));
//                complaint.setUserId(rs.getInt("userId"));
//                complaint.setDescription(rs.getString("description"));
//                complaint.setDate(rs.getDate("date"));
//                complaint.setStatus(rs.getString("status"));
//                complaints.add(complaint);
//            }
//        }
//        return complaints;
//    }
//
//    public void updateComplaint(Complaint complaint) throws SQLException {
//        String sql = "UPDATE complaint SET userId = ?, description = ?, date = ?, status = ? WHERE idcomplaint = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, complaint.getUserId());
//            stmt.setString(2, complaint.getDescription());
//            stmt.setDate(3, new java.sql.Date(complaint.getDate().getTime()));
//            stmt.setString(4, complaint.getStatus());
//            stmt.setInt(5, complaint.getIdComplaint());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void deleteComplaint(int idComplaint) throws SQLException {
//        String sql = "DELETE FROM complaint WHERE idcomplaint = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idComplaint);
//            stmt.executeUpdate();
//        }
//    }
//}
