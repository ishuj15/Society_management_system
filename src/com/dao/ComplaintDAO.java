package com.dao;

import com.Model.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Complaint> getComplaintsByUserId(int userId) throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName() + " WHERE userId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                complaints.add(mapResultSetToEntity(rs));
            }
        }
        return complaints;
    }
}
