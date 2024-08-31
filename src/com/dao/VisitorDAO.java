package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Visitor;

public class VisitorDAO extends GenericDAO<Visitor> {

    @Override
    protected Visitor mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Visitor visitor = new Visitor();
        visitor.setVisitorId(resultSet.getString("visitorId"));
        visitor.setName(resultSet.getString("name"));
        visitor.setVisitDate(resultSet.getDate("visitDate").toLocalDate());
        visitor.setVisitPurpose(resultSet.getString("visitPurpose"));
        // Map other fields as necessary
        return visitor;
    }

    public boolean addVisitor(Visitor visitor) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO Visitor (visitorId, name, visitDate, visitPurpose) VALUES ('%s','%s','%s','%s')",
            visitor.getVisitorId(), visitor.getName(), visitor.getVisitDate().toString(), visitor.getVisitPurpose());
        return executeQuery(sqlQuery);
    }

    public Visitor getVisitorById(String visitorId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Visitor WHERE visitorId = \"" + visitorId + "\"";
        return executeGetQuery(selectSQL);
    }

    public List<Visitor> getAllVisitors() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Visitor";
        return executeGetAllQuery(selectSQL);
    }

    public boolean deleteVisitor(String visitorId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Visitor WHERE visitorId = \"" + visitorId + "\"";
        return executeQuery(deleteSQL);
    }
    public boolean updateApprovalStatus(String visitorId, boolean approved) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "UPDATE Visitor SET approved = %b WHERE visitorId = %d",
            approved, visitorId);
        return executeQuery(sqlQuery);
}

