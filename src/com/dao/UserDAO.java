package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Model.User;
//import com.Model.Role;

public class UserDAO extends GenericDAO<User> {

    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdUser(resultSet.getString("idUser"));
	      user.setUserName(resultSet.getString("userName"));
	      user.setUserRole(resultSet.getString("userRole"));
	      user.setPassword(resultSet.getString("password"));
	      user.setPhoneNo(resultSet.getString("phoneNo"));
	      user.setEmail(resultSet.getString("email"));
	      user.setAddress(resultSet.getString("address"));
	     // user.setSalt(resultSet.getString("salt"));
        return user;
    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format(
            "INSERT INTO User ( idUser, userName,  userRole, password,phoneNo,email,address) VALUES ('%s','%s','%s','%s', '%s','%s','%s')",
            user.getIdUser(), user.getUserName(), user.getUserRole(),user.getPassword(), user.getPhoneNo(), user.getEmail(),user.getAddress());
        return executeQuery(sqlQuery);
    }

    public User getUserById(String userId) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM User WHERE idUser = \"" + userId + "\"";
        return executeGetQuery(selectSQL);
    }

    public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM User WHERE userName = \"" + username + "\"";
        return executeGetQuery(selectSQL);
    }

    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM User WHERE idUser = \"" + userId + "\"";
        return executeQuery(deleteSQL);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM User";
        return executeGetAllQuery(selectSQL);
    }
    public  boolean isUsernameTaken(String username) throws ClassNotFoundException, SQLException {
            String sql = "SELECT COUNT(*) FROM user WHERE userName = ?";
           return executeQuery(sql);
    }
    public boolean updateUser(String userId, String columnToUpdate, String newValue) throws SQLException, ClassNotFoundException {
        String sqlQuery = String.format("UPDATE User SET %s = '%s' WHERE idUser = '%s'", columnToUpdate, newValue, userId);
        return executeQuery(sqlQuery);
    }

}

//
//import com.Model.User;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserDAO extends GenericDAO<User> {
//
//    @Override
//    protected String getTableName() {
//        return "user";
//    }
//
//    @Override
//    protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
//        User user = new User();
//       user.setIdUser(rs.getInt("idUser"));
//        user.setUserName(rs.getString("userName"));
//        user.setUserRole(rs.getString("userRole"));
//        user.setPassword(rs.getString("password"));
//        user.setPhoneNo(rs.getString("phoneNo"));
//        user.setEmail(rs.getString("email"));
//        user.setAddress(rs.getString("address"));
//        return user;
//    }
//
//    public void mapEntityToStatement(User user, PreparedStatement stmt) throws SQLException {
//        stmt.setInt(1,     user.getIdUser());
//        stmt.setString(2, user.getUserName());
//        stmt.setString(3, user.getUserRole());
//        stmt.setString(4, user.getPassword());
//        stmt.setString(5, user.getPhoneNo());
//        stmt.setString(6, user.getEmail());
//        stmt.setString(7, user.getAddress());
//    }
//
//	@Override
//	protected String getIdColumn() {
//		// TODO Auto-generated method stub
//		return "idUser";
//	}
//	
//
//}
