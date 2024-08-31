package com.service;

import com.dao.ComplaintDAO;
import com.Model.Complaint;

import java.sql.SQLException;
import java.util.List;

public class ComplaintService {
    private ComplaintDAO complaintDAO = new ComplaintDAO();

    public void addComplaint(Complaint complaint) throws SQLException, ClassNotFoundException {
        complaintDAO.addComplaint(complaint);
    }

    public Complaint getComplaintById(String idComplaint) throws SQLException, ClassNotFoundException {
        return complaintDAO.getComplaintById(idComplaint);
    }

    public List<Complaint> getAllComplaints() throws SQLException, ClassNotFoundException {
        return complaintDAO.getAllComplaints();
    }

//    public void updateComplaint(Complaint complaint) throws SQLException {
//        complaintDAO.updateEntity(complaint,complaint.getIdComplaint());
    //}

    public void deleteComplaint(String idComplaint) throws SQLException, ClassNotFoundException {
        complaintDAO.deleteComplaint(idComplaint);
    }
}

