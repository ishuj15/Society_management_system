package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Model.Visitor;
import com.dao.VisitorDAO;

public class VisitorService {
    private final VisitorDAO visitorDAO;

    public VisitorService() {
        this.visitorDAO = new VisitorDAO();
    }

    public void addVisitor(Visitor visitor) throws SQLException, ClassNotFoundException {
        visitorDAO.addVisitor(visitor);
    }

    public Visitor getVisitorById(String id) throws SQLException, ClassNotFoundException {
        return visitorDAO.getVisitorById(id);
    }

    public List<Visitor> getAllVisitors() throws SQLException, ClassNotFoundException {
        return visitorDAO.getAllVisitors();
    }

    public void updateVisitor(Visitor visitor, int id) throws SQLException {
        visitorDAO.updateEntity(visitor, id);
    }

    public void deleteVisitor(String id) throws SQLException, ClassNotFoundException {
        visitorDAO.deleteVisitor(id);
    }
    public void verifyVisitor(String visitorId, boolean approved) throws SQLException, ClassNotFoundException {
        visitorDAO.updateApprovalStatus(visitorId, approved);
        if (approved) {
            System.out.println("Visitor approved.");
        } else {
            System.out.println("Visitor rejected.");
        }
    }

    public void requestApproval(String visitorId) throws SQLException {
        // You can implement logic to notify the resident about the approval request
        System.out.println("Approval request sent to resident.");
    }
}
