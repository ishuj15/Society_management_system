package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Notices;

public class NoticesDAO extends GenericDAO<Notices> {

    @Override
    protected String getTableName() {
        return "notices";
    }

    @Override
    protected Notices mapResultSetToEntity(ResultSet rs) throws SQLException {
        Notices notice = new Notices();
        notice.setIdNotices(rs.getInt("idNotices"));
        notice.setTitle(rs.getString("title"));
        notice.setMessage(rs.getString("message"));
        notice.setDate(rs.getDate("date"));
        return notice;
    }

    protected void mapEntityToStatement(Notices notice, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, notice.getIdNotices());
        stmt.setString(2, notice.getTitle());
        stmt.setString(3, notice.getMessage());
        stmt.setDate(4, notice.getDate());
    }

	@Override
	protected String getIdColumn() {
		// TODO Auto-generated method stub
		return null;
	}
}
