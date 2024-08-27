package com.service;

import com.dao.ComplaintDAO;
import com.Model.Complaint;

import java.sql.SQLException;
import java.util.List;

public class ComplaintService {
    private ComplaintDAO complaintDAO = new ComplaintDAO();

    public void addComplaint(Complaint complaint) throws SQLException {
        complaintDAO.addEntity(complaint);
    }

    public Complaint getComplaintById(int idComplaint) throws SQLException {
        return complaintDAO.getEntityById(idComplaint);
    }

    public List<Complaint> getAllComplaints() throws SQLException {
        return complaintDAO.getAllEntities();
    }

    public void updateComplaint(Complaint complaint) throws SQLException {
        complaintDAO.updateEntity(complaint,complaint.getIdComplaint());
    }

    public void deleteComplaint(int idComplaint) throws SQLException {
        complaintDAO.deleteEntity(idComplaint);
    }
}

