package com.service;

import com.dao.NoticesDAO;
import com.Model.Notices;

import java.sql.SQLException;
import java.util.List;

public class NoticesService {
    private final NoticesDAO noticesDAO;

    public NoticesService() {
        this.noticesDAO = new NoticesDAO();
    }

    public void addNotice(Notices notice) throws SQLException {
        noticesDAO.addEntity(notice);
    }

    public Notices getNoticeById(int id) throws SQLException {
        return noticesDAO.getEntityById(id);
    }

    public List<Notices> getAllNotices() throws SQLException {
        return noticesDAO.getAllEntities();
    }

    public void updateNotice(Notices notice, int id) throws SQLException {
        noticesDAO.updateEntity(notice, id);
    }

    public void deleteNotice(int id) throws SQLException {
        noticesDAO.deleteEntity(id);
    }
}
