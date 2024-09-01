package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.societyManagement.Model.Attendance;

public class AttendanceDAO extends GenericDAO<Attendance> {

	@Override
	protected com.dao.Attendance mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

//    @Override
//    protected Attendance mapResultSetToEntity(ResultSet resultSet) throws SQLException {
//        Attendance attendance = new Attendance();
//        attendance.setAttendanceId(resultSet.getString("attendanceId"));
//        attendance.setUserId(resultSet.getString("userId"));
//        attendance.setDate(resultSet.getDate("date"));
//        attendance.setStatus(resultSet.getString("status"));
//        // Map other fields as necessary
//        return attendance;
//    }
//
//    public boolean addAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
//        String sqlQuery = String.format(
//            "INSERT INTO Attendance (attendanceId, userId, date, status) VALUES ('%s','%s','%s','%s')",
//            attendance.getAttendanceId(), attendance.getUserId(), attendance.getDate(), attendance.getStatus());
//        return executeQuery(sqlQuery);
//    }
//
//    public Attendance getAttendanceById(String attendanceId) throws SQLException, ClassNotFoundException {
//        String selectSQL = "SELECT * FROM Attendance WHERE attendanceId = \"" + attendanceId + "\"";
//        return executeGetQuery(selectSQL);
//    }
//
//    public List<Attendance> getAllAttendances() throws SQLException, ClassNotFoundException {
//        String selectSQL = "SELECT * FROM Attendance";
//        return executeGetAllQuery(selectSQL);
//    }
//
//    public boolean deleteAttendance(String attendanceId) throws SQLException, ClassNotFoundException {
//        String deleteSQL = "DELETE FROM Attendance WHERE attendanceId = \"" + attendanceId + "\"";
//        return executeQuery(deleteSQL);
//    }
//}
