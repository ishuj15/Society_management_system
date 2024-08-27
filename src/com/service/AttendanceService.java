package com.service;

import com.dao.AttendanceDAO;
import com.Model.Attendance;

import java.sql.SQLException;
import java.util.List;

public class AttendanceService {
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public void addAttendance(Attendance attendance) throws SQLException {
        attendanceDAO.addEntity(attendance);
    }

    public Attendance getAttendanceById(int idAttendance) throws SQLException {
        return attendanceDAO.getEntityById(idAttendance);
    }

    public List<Attendance> getAllAttendances() throws SQLException {
        return attendanceDAO.getAllEntities();
    }

    public void updateAttendance(Attendance attendance) throws SQLException {
        attendanceDAO.updateEntity(attendance,attendance.getIdAttendance());
    }

    public void deleteAttendance(int idAttendance) throws SQLException {
        attendanceDAO.deleteEntity(idAttendance);
    }
}

