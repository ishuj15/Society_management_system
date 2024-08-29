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
        user.setPhoneNo(rs.getString("phoneNo"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        return user;
    }

    public void mapEntityToStatement(User user, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1,     user.getIdUser());
        stmt.setString(2, user.getUserName());
        stmt.setString(3, user.getUserRole());
        stmt.setString(4, user.getPassword());
        stmt.setString(5, user.getPhoneNo());
        stmt.setString(6, user.getEmail());
        stmt.setString(7, user.getAddress());
    }

	@Override
	protected String getIdColumn() {
		// TODO Auto-generated method stub
		return "idUser";
	}
	

}
