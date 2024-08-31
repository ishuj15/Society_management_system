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

    public void addNotice(Notices notice) throws SQLException, ClassNotFoundException {
        noticesDAO.addNotice(notice);
    }

    public Notices getNoticeById(String id) throws SQLException, ClassNotFoundException {
        return noticesDAO.getNoticeById(id);
    }

    public List<Notices> getAllNotices() throws SQLException, ClassNotFoundException {
        return noticesDAO.getAllNotices();
    }

//    public void updateNotice(Notices notice, int id) throws SQLException {
//        noticesDAO.updateNotice(notice, id);
//    }

    public void deleteNotice(String  id) throws SQLException, ClassNotFoundException {
        noticesDAO.deleteNotice(id);
    }
}
