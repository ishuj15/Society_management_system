package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.Model.Notices;

public class NoticesDAO extends GenericDAO<Notices> {

    @Override
    protected Notices mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Notices notice = new Notices();
        notice.setIdNotices(resultSet.getString("IdNotices"));
        notice.setTitle(resultSet.getString("title"));
        notice.setMessage(resultSet.getString("description"));
        notice.setDate(resultSet.getDate("date"));
       
        return notice;
    }

    public boolean addNotice(Notices notice) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO Notice (noticeId, title, description, datePosted) VALUES ('%s','%s','%s','%s')",
            notice.getIdNotices(), notice.getTitle(), notice.getMessage(), notice.getDate());
        return executeQuery(sqlQuery);
    }

    public Notices getNoticeById(String noticeId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Notice WHERE noticeId = \"" + noticeId + "\"";
        return executeGetQuery(selectSQL);
    }

    public List<Notices> getAllNotices() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Notice";
        return executeGetAllQuery(selectSQL);
    }

    public boolean deleteNotice(String noticeId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Notice WHERE noticeId = \"" + noticeId + "\"";
        return executeQuery(deleteSQL);
    }
}
