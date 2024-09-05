package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Notices;

public class NoticesDAO extends GenericDAO<Notices> {

	@Override
	protected Notices mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Notices notice = new Notices();
		notice.setIdNotices(resultSet.getString("IdNotices"));
		notice.setTitle(resultSet.getString("title"));
		notice.setMessage(resultSet.getString("message"));
		notice.setDate(resultSet.getDate("date"));
		notice.setTargetRole(resultSet.getString("targetRole"));

		return notice;
	}

	public boolean addNotice(Notices notice) throws SQLException, ClassNotFoundException {
		String sqlQuery = String.format(
				"INSERT INTO notices (idNotices, title, message, date, targetRole) VALUES ('%s','%s','%s','%s','%s')",
				notice.getIdNotices(), notice.getTitle(), notice.getMessage(), notice.getDate(),
				notice.getTargetRole());
		return executeQuery(sqlQuery);
	}

	public List<Notices> getNoticeByRole(String role) throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM notices WHERE targetRole = \"" + role + "\"";
		return executeGetAllQuery(selectSQL);
	}

	public List<Notices> getAllNotices() throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM notices";
		return executeGetAllQuery(selectSQL);
	}

	public boolean deleteNotice(String noticeId) throws SQLException, ClassNotFoundException {
		String deleteSQL = "DELETE FROM notices WHERE idNotices = \"" + noticeId + "\"";
		return executeQuery(deleteSQL);
	}

	public boolean updateNotice(String userId, String columnToUpdate, String newValue)
			throws SQLException, ClassNotFoundException {
		String sqlQuery = String.format("UPDATE notices SET %s = '%s' WHERE idNotices = '%s'", columnToUpdate, newValue,
				userId);
		return executeQuery(sqlQuery);
	}
}
