package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Alert;

public class AlertDAO extends GenericDAO<Alert> {

    @Override
    protected String getTableName() {
        return "alert";
    }

    @Override
    protected Alert mapResultSetToEntity(ResultSet rs) throws SQLException {
        Alert alert = new Alert();
        alert.setIdAlert(rs.getInt("idAlert"));
        alert.setMessage(rs.getString("message"));
        alert.setDate(rs.getDate("date"));
        return alert;
    }

    protected void mapEntityToStatement(Alert alert, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, alert.getIdAlert());
        stmt.setString(2, alert.getMessage());
        stmt.setDate(3, alert.getDate());
    }

	@Override
	protected String getIdColumn() {
		// TODO Auto-generated method stub
		return "idAlert";
	}
}


//package com.dao;
//
//import com.Model.Alert;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlertDAO {
//    private static final String URL = "jdbc:mysql://localhost:3306/test";
//    private static final String USER = "root";
//    private static final String PASSWORD = "password";
//
//    public AlertDAO() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    public void addAlert(Alert alert) throws SQLException {
//        String sql_query = "INSERT INTO alert (message, date) VALUES (?, ?)";
//        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql_query)) {
//            stmt.setString(1, alert.getMessage());
//            stmt.setDate(2, alert.getDate());
//            stmt.executeUpdate();
//        }
//    }
//
//    public Alert getAlertById(int idAlert) throws SQLException {
//        String sql = "SELECT * FROM alert WHERE idAlert = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idAlert);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Alert alert = new Alert();
//                alert.setIdAlert(rs.getInt("idAlert"));
//                alert.setMessage(rs.getString("message"));
//                alert.setDate(rs.getDate("date"));
//                return alert;
//            }
//        }
//        return null;
//    }
//
//    public List<Alert> getAllAlerts() throws SQLException {
//        List<Alert> alerts = new ArrayList<>();
//        String sql = "SELECT * FROM alert";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Alert alert = new Alert();
//                alert.setIdAlert(rs.getInt("idAlert"));
//                alert.setMessage(rs.getString("message"));
//                alert.setDate(rs.getDate("date"));
//                alerts.add(alert);
//            }
//        }
//        return alerts;
//    }
//
//    public void updateAlert(Alert alert) throws SQLException {
//        String sql = "UPDATE alert SET message = ?, date = ? WHERE idAlert = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, alert.getMessage());
//            stmt.setDate(2, alert.getDate());
//            stmt.setInt(3, alert.getIdAlert());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void deleteAlert(int idAlert) throws SQLException {
//        String sql = "DELETE FROM alert WHERE idAlert = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idAlert);
//            stmt.executeUpdate();
//        }
//    }
//}

//import com.Model.Alert;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AlertDAO extends BaseDAO {
//
//    public void addAlert(Alert alert) throws SQLException {
//        String sql = "INSERT INTO alert (message, date) VALUES (?, ?)";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, alert.getMessage());
//            stmt.setDate(2, new java.sql.Date(alert.getDate().getTime()));
//            stmt.executeUpdate();
//        }
//    }
//
//    public Alert getAlertById(int idAlert) throws SQLException {
//        String sql = "SELECT * FROM alert WHERE idAlert = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idAlert);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                Alert alert = new Alert();
//                alert.setIdAlert(rs.getInt("idAlert"));
//                alert.setMessage(rs.getString("message"));
//                alert.setDate(rs.getDate("date"));
//                return alert;
//            }
//        }
//        return null;
//    }
//
//    public List<Alert> getAllAlerts() throws SQLException {
//        List<Alert> alerts = new ArrayList<>();
//        String sql = "SELECT * FROM alert";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Alert alert = new Alert();
//                alert.setIdAlert(rs.getInt("idAlert"));
//                alert.setMessage(rs.getString("message"));
//                alert.setDate(rs.getDate("date"));
//                alerts.add(alert);
//            }
//        }
//        return alerts;
//    }
//
//    public void updateAlert(Alert alert) throws SQLException {
//        String sql = "UPDATE alert SET message = ?, date = ? WHERE idAlert = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, alert.getMessage());
//            stmt.setDate(2, new java.sql.Date(alert.getDate().getTime()));
//            stmt.setInt(3, alert.getIdAlert());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void deleteAlert(int idAlert) throws SQLException {
//        String sql = "DELETE FROM alert WHERE idAlert = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idAlert);
//            stmt.executeUpdate();
//        }
//    }
//}
