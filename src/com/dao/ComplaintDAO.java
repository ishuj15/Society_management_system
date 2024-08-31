package com.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Complaint;

public class ComplaintDAO extends GenericDAO<Complaint> {

    @Override
    protected Complaint mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Complaint complaint = new Complaint();
        complaint.setIdComplaint(resultSet.getString("complaintId"));
        complaint.setUserId(resultSet.getString("userId"));
        complaint.setDescription(resultSet.getString("description"));
        complaint.setDate(resultSet.getDate("date"));
        complaint.setStatus(resultSet.getString("status"));
       
        return complaint;
    }

    public boolean addComplaint(Complaint complaint) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO Complaint (complaintId, userId, description, date,status) VALUES ('%s','%s','%s','%s','%s')",
            complaint.getIdComplaint(), complaint.getUserId(), complaint.getDescription(), complaint.getDate(), complaint.getStatus());
        return executeQuery(sqlQuery);
    }

    public Complaint getComplaintById(String complaintId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Complaint WHERE complaintId = \"" + complaintId + "\"";
        return executeGetQuery(selectSQL);
    }

    public List<Complaint> getAllComplaints() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Complaint";
        return executeGetAllQuery(selectSQL);
    }

    public boolean deleteComplaint(String complaintId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Complaint WHERE complaintId = \"" + complaintId + "\"";
        return executeQuery(deleteSQL);
    }
}

//import com.Model.Complaint;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ComplaintDAO extends GenericDAO<Complaint> {
//
//    @Override
//    protected String getTableName() {
//        return "complaint";
//    }
//
//    @Override
//    protected Complaint mapResultSetToEntity(ResultSet rs) throws SQLException {
//        Complaint complaint = new Complaint();
//        complaint.setIdComplaint(rs.getInt("idComplaint"));
//        complaint.setUserId(rs.getInt("userId"));
//        complaint.setDescription(rs.getString("description"));
//        complaint.setDate(rs.getDate("date"));
//        complaint.setStatus(rs.getString("status"));
//        return complaint;
//    }
//
//    protected void mapEntityToStatement(Complaint complaint, PreparedStatement stmt) throws SQLException {
//        stmt.setInt(1, complaint.getIdComplaint());
//        stmt.setInt(2, complaint.getUserId());
//        stmt.setString(3, complaint.getDescription());
//        stmt.setDate(4, new java.sql.Date(complaint.getDate().getTime()));
//        stmt.setString(5, complaint.getStatus());
//    }
//    public List<Complaint> getComplaintsByUserId(int userId) throws SQLException {
//        List<Complaint> complaints = new ArrayList<>();
//        String sql = "SELECT * FROM " + getTableName() + " WHERE userId = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                complaints.add(mapResultSetToEntity(rs));
//            }
//        }
//        return complaints;
//    }

//	@Override
//	protected String getIdColumn() {
//		// TODO Auto-generated method stub
//		return "idComplaint";
//	}
//}
