package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Model.Notices;
import com.dao.NoticesDAO;

public class NoticesService {
	private final NoticesDAO noticesDAO;

	public NoticesService() {
		this.noticesDAO = new NoticesDAO();
	}

	public void addNotice(Notices notice) throws SQLException, ClassNotFoundException {
		noticesDAO.addNotice(notice);
	}

	public List<Notices> getNoticeByRole(String role) throws SQLException, ClassNotFoundException {
		return noticesDAO.getNoticeByRole(role);
	}

	public List<Notices> getAllNotices() throws SQLException, ClassNotFoundException {
		return noticesDAO.getAllNotices();
	}

	public void updateNotice(String noticeId, String ColumnToUpdate, String NewValue)
			throws SQLException, ClassNotFoundException {
		noticesDAO.updateNotice(noticeId, ColumnToUpdate, NewValue);
	}

	public void deleteNotice(String id) throws SQLException, ClassNotFoundException {
		noticesDAO.deleteNotice(id);
	}
}
