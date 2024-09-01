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

    public List<Complaint> getComplaintById(String userId) throws SQLException, ClassNotFoundException {
        return complaintDAO.getComplaintById(userId);
    }

    public List<Complaint> getAllComplaints() throws SQLException, ClassNotFoundException {
        return complaintDAO.getAllComplaints();
    }


    public void deleteComplaint(String idComplaint) throws SQLException, ClassNotFoundException {
        complaintDAO.deleteComplaint(idComplaint);
    }
    public void updateComplaint(String idComplaint, String ColumnToUpdate, String NewValue) throws SQLException, ClassNotFoundException {
        complaintDAO.updateComplaint(idComplaint,ColumnToUpdate, NewValue);
    }
}

