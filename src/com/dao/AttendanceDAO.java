package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Attendance;

public  class AttendanceDAO extends GenericDAO<Attendance> {

    @Override
    protected String getTableName() {
        return "attendance_id";
    }

    @Override
    protected Attendance mapResultSetToEntity(ResultSet rs) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setIdAttendance(rs.getInt("idattendance_Id"));
        attendance.setUserId(rs.getInt("user_Id"));
        attendance.setStatus(rs.getString("status"));
        attendance.setDate(rs.getDate("date"));
        return attendance;
    }

    @Override
    protected void mapEntityToStatement(Attendance attendance, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, attendance.getIdAttendance());
        stmt.setInt(2, attendance.getUserId());
        stmt.setString(3, attendance.getStatus());
        stmt.setDate(4, attendance.getDate());
    }

}

//
//import com.Model.Attendance;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AttendanceDAO {
//    private static final String URL = "jdbc:mysql://localhost:3306/test";
//    private static final String USER = "root";
//    private static final String PASSWORD = "password";
//
//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    public void addAttendance(Attendance attendance) throws SQLException {
//        String sql = "INSERT INTO attendance_id (user_Id, status, date) VALUES (?, ?, ?)";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, attendance.getUserId());
//            stmt.setString(2, attendance.getStatus());
//            stmt.setDate(3, new java.sql.Date(attendance.getDate().getTime()));
//            stmt.executeUpdate();
//        }
//    }
//
//    public Attendance getAttendanceById(int idAttendance) throws SQLException {
//        String sql = "SELECT * FROM attendance_id WHERE idattendance_Id = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idAttendance);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Attendance attendance = new Attendance();
//                attendance.setIdAttendance(rs.getInt("idattendance_Id"));
//                attendance.setUserId(rs.getInt("user_Id"));
//                attendance.setStatus(rs.getString("status"));
//                attendance.setDate(rs.getDate("date"));
//                return attendance;
//            }
//        }
//        return null;
//    }
//
//    public List<Attendance> getAllAttendances() throws SQLException {
//        List<Attendance> attendances = new ArrayList<>();
//        String sql = "SELECT * FROM attendance_id";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Attendance attendance = new Attendance();
//                attendance.setIdAttendance(rs.getInt("idattendance_Id"));
//                attendance.setUserId(rs.getInt("user_Id"));
//                attendance.setStatus(rs.getString("status"));
//                attendance.setDate(rs.getDate("date"));
//                attendances.add(attendance);
//            }
//        }
//        return attendances;
//    }
//
//    public void updateAttendance(Attendance attendance) throws SQLException {
//        String sql = "UPDATE attendance_id SET user_Id = ?, status = ?, date = ? WHERE idattendance_Id = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, attendance.getUserId());
//            stmt.setString(2, attendance.getStatus());
//            stmt.setDate(3, new java.sql.Date(attendance.getDate().getTime()));
//            stmt.setInt(4, attendance.getIdAttendance());
//            stmt.executeUpdate();
//        }
//    }
//        public void deleteAttendance(int idAttendance) throws SQLException {
//            String sql = "DELETE FROM attendance_id WHERE idattendance_Id = ?";
//            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//                stmt.setInt(1, idAttendance);
//                stmt.executeUpdate();
//            }
//        }
//    
//    }
