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
}

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.Model.Visitor;
//
//public class VisitorDAO extends GenericDAO<Visitor> {
//
//    @Override
//    protected String getTableName() {
//        return "visitor";
//    }
//
//    @Override
//    protected Visitor mapResultSetToEntity(ResultSet rs) throws SQLException {
//        Visitor visitor = new Visitor();
//        visitor.setIdVisitor(rs.getInt("idvisitor"));
//        visitor.setUserId(rs.getInt("userId"));
//        visitor.setName(rs.getString("name"));
//        visitor.setPurpose(rs.getString("purpose"));
//        visitor.setDate(rs.getDate("date_of_arrival"));
//        visitor.setArrivalTime(rs.getTime("arrivalTime"));
//        visitor.setDepartureTime(rs.getTime("departureTime"));
//        return visitor;
//    }
//
//    protected void mapEntityToStatement(Visitor visitor, PreparedStatement stmt) throws SQLException {
//        stmt.setInt(1, visitor.getIdVisitor());
//        stmt.setInt(2, visitor.getUserId());
//        stmt.setString(3, visitor.getName());
//        stmt.setString(4, visitor.getPurpose());
//        stmt.setDate(5, visitor.getDate());
//        stmt.setTime(6, visitor.getArrivalTime());
//        stmt.setTime(7, visitor.getDepartureTime());
//    }
//
//	@Override
//	protected String getIdColumn() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
