package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.Attendance;

public class AttendanceDAO extends GenericDAO<Attendance> {

    @Override
    protected Attendance mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setIdAttendance(resultSet.getString("attendanceId"));
        attendance.setUserId(resultSet.getString("user_Id"));
        attendance.setDate(resultSet.getDate("date"));
        attendance.setStatus(resultSet.getString("status"));
        return attendance;
    }

    public boolean addAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO attendance_id (attendanceId, user_Id, date, status) VALUES ('%s','%s','%s','%s')",
            attendance.getIdAttendance(), attendance.getUserId(), attendance.getDate(), attendance.getStatus());
        return executeQuery(sqlQuery);
    }

    public List<Attendance> getAttendanceById(String userId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM attendance_id WHERE user_Id = \"" + userId + "\"";
        return executeGetAllQuery(selectSQL);
    }

    public List<Attendance> getAllAttendances() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM attendance_id";
        return executeGetAllQuery(selectSQL);
    }
    

//    public boolean deleteAttendance(String attendanceId) throws SQLException, ClassNotFoundException {
//        String deleteSQL = "DELETE FROM Attendance WHERE attendanceId = \"" + attendanceId + "\"";
//        return executeQuery(deleteSQL);
//    }
}
