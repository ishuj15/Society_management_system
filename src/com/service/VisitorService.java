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

	public List<Visitor> getVisitorById(String id) throws SQLException, ClassNotFoundException {
		
		return visitorDAO.getVisitorById(id);
	}

	public List<Visitor> getAllVisitors() throws SQLException, ClassNotFoundException {
		return visitorDAO.getAllVisitors();	
	}
	public void updateVisitor(String visitorId, String ColumnToUpdate, String NewValue)
			throws SQLException, ClassNotFoundException {
	
		visitorDAO.updateVisitor(visitorId, ColumnToUpdate, NewValue);
	}

	public void deleteVisitor(String visitorId) throws SQLException, ClassNotFoundException {
		
		visitorDAO.deleteVisitor(visitorId);	
	}
	public void verifyVisitor(String visitorId) throws SQLException, ClassNotFoundException {
		
		
		Visitor visitor = visitorDAO.verifyVisitor(visitorId);
		if (visitor.isApproved().equals("Approved")) {
			System.out.println("Visitor verified.");
		} else if (visitor.isApproved().equals("Pending")) {
			System.out.println("Visitor Request Pending.");
		} else {
			System.out.println("Visitor rejected.");
		}
	}
	public void updateApprovalStatus(String visitorId, String approved) throws SQLException, ClassNotFoundException {
		visitorDAO.updateApprovalStatus(visitorId, approved);
	}

	public List<Visitor> getAllPendingReq(String userId, String string) throws SQLException, ClassNotFoundException {
		return visitorDAO.pendingRequests(userId, string);
	}		
}
