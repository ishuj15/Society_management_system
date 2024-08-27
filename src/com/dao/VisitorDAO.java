package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Visitor;

public class VisitorDAO extends GenericDAO<Visitor> {

    @Override
    protected String getTableName() {
        return "visitor";
    }

    @Override
    protected Visitor mapResultSetToEntity(ResultSet rs) throws SQLException {
        Visitor visitor = new Visitor();
        visitor.setIdVisitor(rs.getInt("idvisitor"));
        visitor.setUserId(rs.getInt("userId"));
        visitor.setName(rs.getString("name"));
        visitor.setPurpose(rs.getString("purpose"));
        visitor.setArrivalTime(rs.getTime("arrivalTime"));
        visitor.setDepartureTime(rs.getTime("departureTime"));
        return visitor;
    }

    @Override
    protected void mapEntityToStatement(Visitor visitor, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, visitor.getIdVisitor());
        stmt.setInt(2, visitor.getUserId());
        stmt.setString(3, visitor.getName());
        stmt.setString(4, visitor.getPurpose());
        stmt.setTime(5, visitor.getArrivalTime());
        stmt.setTime(6, visitor.getDepartureTime());
    }
}
