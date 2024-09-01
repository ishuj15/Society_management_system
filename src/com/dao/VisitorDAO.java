package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Visitor;

public class VisitorDAO extends GenericDAO<Visitor> {

    @Override
    protected Visitor mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Visitor visitor = new Visitor();
        visitor.setIdVisitor(resultSet.getString("idvisitor"));
        visitor.setUserId(resultSet.getString("userId"));
        visitor.setName(resultSet.getString("name"));
        visitor.setDate(resultSet.getDate("date_of_arrival"));
        visitor.setPurpose(resultSet.getString("purpose"));
        visitor.setApproved(resultSet.getString("approvalReq"));
       visitor.setArrivalTime(resultSet.getTime("arrivalTime"));
       visitor.setDepartureTime(resultSet.getTime("departureTime"));
       visitor.setContactNo(resultSet.getString("contact"));
       visitor.setDep_date(resultSet.getDate("departure_date"));
        return visitor;
    }

    public boolean addVisitor(Visitor visitor) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO Visitor (idvisitor, userId, name,contact, purpose, date_of_arrival,arrivalTime,departure_date,departureTime, approvalReq) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
            visitor.getIdVisitor(),visitor.getUserId(), visitor.getName(), visitor.getContactNo() ,visitor.getPurpose(), visitor.getDate().toString(), visitor.getArrivalTime() , visitor.getDep_date().toString(),visitor.getDepartureTime(), visitor.isApproved());
        return executeQuery(sqlQuery);
    }

    public List<Visitor> getVisitorById(String userId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Visitor WHERE userId = \"" + userId+ "\"";
        return executeGetAllQuery(selectSQL);
    }

    public List<Visitor> getAllVisitors() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Visitor";
        return executeGetAllQuery(selectSQL);
    }

    public boolean deleteVisitor(String visitorId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Visitor WHERE idvisitor = \"" + visitorId + "\"";
        return executeQuery(deleteSQL);
    }
    public boolean updateApprovalStatus(String visitorId, String approved) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "UPDATE Visitor SET approvalreq = %s WHERE idvisitor = %s",
            approved, visitorId);
        return executeQuery(sqlQuery);
}
    public boolean updateVisitor(String visitorId, String columnToUpdate, String newValue) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format("UPDATE Visitor SET %s = '%s' WHERE idvisitor = '%s'", columnToUpdate, newValue, visitorId);
        return executeQuery(sqlQuery);
    }
    public Visitor verifyVisitor(String visitorId) throws ClassNotFoundException, SQLException
    {
    	String sql ="SELECT * FROM Visitor WHERE idvisitor=\""+ visitorId+"\"";
    return executeGetQuery(sql);
    }
    public List<Visitor> pendingRequests(String userId, String apr) throws ClassNotFoundException, SQLException
    {
    	String sql ="SELECT * FROM Visitor WHERE userId=\""+ userId+"\" AND approvalreq = \""+apr+"\"";			
    	return executeGetAllQuery(sql);
    }

}
