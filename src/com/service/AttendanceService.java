package com.service;

import com.dao.AttendanceDAO;
import com.Model.Attendance;

import java.sql.SQLException;
import java.util.List;

public class AttendanceService {
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public void addAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
        attendanceDAO.addAttendance(attendance);
    }
    public List<Attendance> getAttendanceById(String iduser) throws SQLException, ClassNotFoundException {
        return attendanceDAO.getAttendanceById(iduser);
    }
    public List<Attendance> getAllAttendances() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getAllAttendances();
    }

//    public void updateAttendance(Attendance attendance) throws SQLException {
//        attendanceDAO.updateAttendance(attendance,attendance.getIdAttendance());
//    }
}

