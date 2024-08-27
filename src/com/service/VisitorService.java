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

    public void addVisitor(Visitor visitor) throws SQLException {
        visitorDAO.addEntity(visitor);
    }

    public Visitor getVisitorById(int id) throws SQLException {
        return visitorDAO.getEntityById(id);
    }

    public List<Visitor> getAllVisitors() throws SQLException {
        return visitorDAO.getAllEntities();
    }

    public void updateVisitor(Visitor visitor, int id) throws SQLException {
        visitorDAO.updateEntity(visitor, id);
    }

    public void deleteVisitor(int id) throws SQLException {
        visitorDAO.deleteEntity(id);
    }
}
