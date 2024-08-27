package com.dao;

import com.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends GenericDAO<User> {

    @Override
    protected String getTableName() {
        return "user";
    }

    @Override
    protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
        User user = new User();
       user.setIdUser(rs.getInt("idUser"));
        user.setUserName(rs.getString("userName"));
        user.setUserRole(rs.getString("userRole"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNo(rs.getString("phone_No"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        return user;
    }

    @Override
	public void mapEntityToStatement(User user, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, user.getIdUser());
        stmt.setString(2, user.getUserName());
        stmt.setString(3, user.getUserRole());
        stmt.setString(4, user.getPassword());
        stmt.setString(5, user.getPhoneNo());
        stmt.setString(6, user.getEmail());
        stmt.setString(7, user.getAddress());
    }
}

//import com.Model.User;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDAO {
//    private static final String URL = "jdbc:mysql://localhost:3306/test";
//    private static final String USER = "root";
//    private static final String PASSWORD = "password";
//
//    public UserDAO() {
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
//    public void addUser(User user) throws SQLException {
//        String sql = "INSERT INTO user (userName, userRole, password, phone_No, email, Address) VALUES (?, ?, ?, ?, ?, ?)";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, user.getUserName());
//            stmt.setString(2, user.getUserRole());
//            stmt.setString(3, user.getPassword());
//            stmt.setString(4, user.getPhoneNo());
//            stmt.setString(5, user.getEmail());
//            stmt.setString(6, user.getAddress());
//            stmt.executeUpdate();
//        }
//    }
//
//    public User getUserById(int idUser) throws SQLException {
//        String sql = "SELECT * FROM user WHERE idUser = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idUser);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                User user = new User();
//                user.setIdUser(rs.getInt("idUser"));
//                user.setUserName(rs.getString("userName"));
//                user.setUserRole(rs.getString("userRole"));
//                user.setPassword(rs.getString("password"));
//                user.setPhoneNo(rs.getString("phone_No"));
//                user.setEmail(rs.getString("email"));
//                user.setAddress(rs.getString("Address"));
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers() throws SQLException {
//        List<User> users = new ArrayList<>();
//        String sql = "SELECT * FROM user";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                User user = new User();
//                user.setIdUser(rs.getInt("idUser"));
//                user.setUserName(rs.getString("userName"));
//                user.setUserRole(rs.getString("userRole"));
//                user.setPassword(rs.getString("password"));
//                user.setPhoneNo(rs.getString("phone_No"));
//                user.setEmail(rs.getString("email"));
//                user.setAddress(rs.getString("Address"));
//                users.add(user);
//            }
//        }
//        return users;
//    }
//
//    public void updateUser(User user) throws SQLException {
//        String sql = "UPDATE user SET userName = ?, userRole = ?, password = ?, phone_No = ?, email = ?, Address = ? WHERE idUser = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, user.getUserName());
//            stmt.setString(2, user.getUserRole());
//            stmt.setString(3, user.getPassword());
//            stmt.setString(4, user.getPhoneNo());
//            stmt.setString(5, user.getEmail());
//            stmt.setString(6, user.getAddress());
//            stmt.setInt(7, user.getIdUser());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void deleteUser(int idUser) throws SQLException {
//        String sql = "DELETE FROM user WHERE idUser = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, idUser);
//            stmt.executeUpdate();
//        }
//    }
//}
// old
//import com.Model.User;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class userDao {
//    private Connection connection;
//
//    public userDao(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void addUser(User user) throws SQLException {
//        String sql = "INSERT INTO user (userName, userRole, password, phone_No, email, Address) VALUES (?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, user.getUserName());
//            stmt.setString(2, user.getUserRole());
//            stmt.setString(3, user.getPassword());
//            stmt.setString(4, user.getPhoneNo());
//            stmt.setString(5, user.getEmail());
//            stmt.setString(6, user.getAddress());
//            stmt.executeUpdate();
//        }
//    }
//
//    public User getUserById(int idUser) throws SQLException {
//        String sql = "SELECT * FROM user WHERE idUser = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, idUser);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                User user = new User();
//                user.setIdUser(rs.getInt("idUser"));
//                user.setUserName(rs.getString("userName"));
//                user.setUserRole(rs.getString("userRole"));
//                user.setPassword(rs.getString("password"));
//                user.setPhoneNo(rs.getString("phone_No"));
//                user.setEmail(rs.getString("email"));
//                user.setAddress(rs.getString("Address"));
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers() throws SQLException {
//        List<User> users = new ArrayList<>();
//        String sql = "SELECT * FROM user";
//        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                User user = new User();
//                user.setIdUser(rs.getInt("idUser"));
//                user.setUserName(rs.getString("userName"));
//                user.setUserRole(rs.getString("userRole"));
//                user.setPassword(rs.getString("password"));
//                user.setPhoneNo(rs.getString("phone_No"));
//                user.setEmail(rs.getString("email"));
//                user.setAddress(rs.getString("Address"));
//                users.add(user);
//            }
//        }
//        return users;
//    }
//
//    public void updateUser(User user) throws SQLException {
//        String sql = "UPDATE user SET userName = ?, userRole = ?, password = ?, phone_No = ?, email = ?, Address = ? WHERE idUser = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, user.getUserName());
//            stmt.setString(2, user.getUserRole());
//            stmt.setString(3, user.getPassword());
//            stmt.setString(4, user.getPhoneNo());
//            stmt.setString(5, user.getEmail());
//            stmt.setString(6, user.getAddress());
//            stmt.setInt(7, user.getIdUser());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void deleteUser(int idUser) throws SQLException {
//        String sql = "DELETE FROM user WHERE idUser = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, idUser);
//            stmt.executeUpdate();
//        }
//    }
//}
//
//
