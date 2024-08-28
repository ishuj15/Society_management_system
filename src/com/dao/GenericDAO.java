package com.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.Model.User;
//import com.mysql.cj.result.Field;

public abstract class GenericDAO<T> {
	private static final Logger logger = Logger.getLogger(GenericDAO.class.getName());
    protected abstract String getTableName();
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
    protected abstract void mapEntityToStatement(T entity, PreparedStatement stmt) throws SQLException;

    private static final String URL = "jdbc:mysql://localhost:33066/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    protected Connection getConnection() throws SQLException {
    	try {
    		  Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
    		  logger.info("Database connection established");
    		  return connection;
    	} catch(SQLException e) {
    		logger.log(Level.SEVERE, "Failed to establish databse connection", e);
    		throw e;
    	}
      
    }
    
    public void addEntity(T entity) throws SQLException {
    	Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        // Build the SQL query dynamically
        StringBuilder sql = new StringBuilder("INSERT INTO " + getTableName() + " (");
        StringBuilder placeholders = new StringBuilder("VALUES (");
        
        for (Field field : fields) {
            sql.append(field.getName()).append(", ");
            placeholders.append("?, ");
        }
        
        // Remove the last comma and space
        sql.setLength(sql.length() - 2);
        placeholders.setLength(placeholders.length() - 2);
        
        sql.append(") ").append(placeholders).append(")");
        
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            mapEntityToStatement(entity, stmt);
            stmt.executeUpdate();
            logger.info("Record added to the database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to add record to the database", e);
            throw e;
        }
 
    	
//        String sql = "INSERT INTO " + getTableName() + " VALUES (?, ...)"; // Dynamic SQL based on fields
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            mapEntityToStatement(entity, stmt);
//            stmt.executeUpdate();
//            logger.info("Record added to the database");
//        }catch(SQLException e) {
//        	logger.log(Level.SEVERE,"Failed to add record to the database",e);
//        	throw e;
//        }
    }
    public T getEntityById(int id) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE idUser = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	 logger.info("Record retrived from the database");
                return mapResultSetToEntity(rs);
            } else {
            	logger.warning("No record found with the provided ID");
                return null;	
            }
        }catch(SQLException e) {
        	logger.log(Level.SEVERE, "Failed to retrieve record from the database", e);
            throw e;	
        }
       
    }
    public T getEntityByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE userName = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	 logger.info("Record retrived from the database");
                return mapResultSetToEntity(rs);
            } else {
            	logger.warning("No record found with the provided userName");
                return null;	
            }
        }catch(SQLException e) {
        	logger.log(Level.SEVERE, "Failed to retrieve record from the database", e);
            throw e;	
        }
       
    }

    public List<T> getAllEntities() throws SQLException {
        List<T> entities = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
            logger.info("All records retrieved from the database");
            return entities;
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve records from the database", e);
            throw e;
        }
        
    }

    public void updateEntity(T entity, int id) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET ... WHERE idUser = ?"; // Update based on fields
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            mapEntityToStatement(entity, stmt);
            stmt.setInt( /* last parameter */1, id);
            stmt.executeUpdate();
            logger.info("Record updated in the database");
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to update record in the database", e);
            throw e;
        }
    }

    public void deleteEntity(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE idUser = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Record deleted from the database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to delete record from the database", e);
            throw e;
        }
    }
}
