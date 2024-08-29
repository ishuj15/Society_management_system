package com.dao;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GenericDAO<T> {
    private static final Logger logger = Logger.getLogger(GenericDAO.class.getName());

    protected abstract String getTableName();
    protected abstract String getIdColumn();
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    private static final String URL = "jdbc:mysql://localhost:33066/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    protected Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Database connection established");
            return connection;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to establish database connection", e);
            throw e;
        }
    }

    private void executeUpdate(String sql, ThrowingConsumer<PreparedStatement> mapper) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            mapper.accept(stmt);
            stmt.executeUpdate();
            logger.info("Database update executed");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database update failed", e);
            throw e;
        }
    }

    private T executeQuery(String sql, ThrowingConsumer<PreparedStatement> mapper) throws SQLException {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            mapper.accept(stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                logger.info("Record retrieved from the database");
                return mapResultSetToEntity(rs);
            } else {
                logger.warning("No record found with the provided criteria");
                return null;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve record from the database", e);
            throw e;
        }
    }

    public void addEntity(T entity) throws SQLException {
        String sql = generateInsertQuery(entity);
        executeUpdate(sql, stmt -> mapEntityToStatement(entity, stmt));
    }

    public T getEntityById(int id) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumn() + " = ?";
        return executeQuery(sql, stmt -> stmt.setInt(1, id));
    }

    public List<T> getAllEntities() throws SQLException {
        String sql = "SELECT * FROM " + getTableName();
        List<T> entities = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
            logger.info("All records retrieved from the database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve records from the database", e);
            throw e;
        }
        return entities;
    }

    public void updateEntity(T entity, int id) throws SQLException {
        String sql = generateUpdateQuery(entity);
        executeUpdate(sql, stmt -> {
            mapEntityToStatement(entity, stmt);
            stmt.setInt(getFields(entity).size() + 1, id); // Last parameter is ID
        });
    }

    public void deleteEntity(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumn() + " = ?";
        executeUpdate(sql, stmt -> stmt.setInt(1, id));
    }

    private void mapEntityToStatement(T entity, PreparedStatement stmt) throws SQLException {
        List<Field> fields = getFields(entity);
        int index = 1;
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                stmt.setObject(index++, field.get(entity));
            } catch (IllegalAccessException e) {
                throw new SQLException("Failed to access entity field", e);
            }
        }
    }

    private String generateInsertQuery(T entity) {
        List<Field> fields = getFields(entity);
        String fieldNames = String.join(", ", getFieldNames(fields));
        String placeholders = String.join(", ", Collections.nCopies(fields.size(), "?"));
        return "INSERT INTO " + getTableName() + " (" + fieldNames + ") VALUES (" + placeholders + ")";
    }

    private String generateUpdateQuery(T entity) {
        List<Field> fields = getFields(entity);
        StringBuilder sql = new StringBuilder("UPDATE " + getTableName() + " SET ");
        for (String fieldName : getFieldNames(fields)) {
            sql.append(fieldName).append(" = ?, ");
        }
        sql.setLength(sql.length() - 2); // Remove last comma and space
        sql.append(" WHERE ").append(getIdColumn()).append(" = ?");
        return sql.toString();
    }

    private List<Field> getFields(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        for (Field field : fields) {
            if (!field.isSynthetic()) { // Skip synthetic fields
                fieldList.add(field);
            }
        }
        return fieldList;
    }

    private List<String> getFieldNames(List<Field> fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    @FunctionalInterface
    private interface ThrowingConsumer<T> {
        void accept(T t) throws SQLException;
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
    public static boolean isUsernameTaken(String username) {
        // Database connection
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // SQL query to check if the username exists
            String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // If the count is greater than 0, the username exists
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}








//import java.lang.reflect.Field;
//import java.sql.*;
//import java.util.ArrayList;
//////import java.util.Collections;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
////import com.Model.User;
////import com.mysql.cj.result.Field;
//
//public abstract class GenericDAO<T> {
//	private static final Logger logger = Logger.getLogger(GenericDAO.class.getName());
//    protected abstract String getTableName();
//    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
//    protected abstract void mapEntityToStatement(T entity, PreparedStatement stmt) throws SQLException;
//
//    private static final String URL = "jdbc:mysql://localhost:33066/test";
//    private static final String USER = "root";
//    private static final String PASSWORD = "root";
//
//    protected Connection getConnection() throws SQLException {
//    	try {
//    		  Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
//    		  logger.info("Database connection established");
//    		  return connection;
//    	} catch(SQLException e) {
//    		logger.log(Level.SEVERE, "Failed to establish databse connection", e);
//    		throw e;
//    	}
//      
//    }
//    
//    public void addEntity(T entity) throws SQLException {
//        String sql = "INSERT INTO " + getTableName() + " VALUES (?, ...)"; // Dynamic SQL based on fields
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            mapEntityToStatement(entity, stmt);
//            stmt.executeUpdate();
//            logger.info("Record added to the database");
//        }catch(SQLException e) {
//        	logger.log(Level.SEVERE,"Failed to add record to the database",e);
//        	throw e;
//        }
//    }
//    public List<T> getEntityById(int id) throws SQLException {
//        String sql = "SELECT * FROM " + getTableName() + " WHERE idUser = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//            	 logger.info("Record retrived from the database");
//                return mapResultSetToEntity(rs);
//            } else {
//            	logger.warning("No record found with the provided ID");
//                return null;	
//            }
//        }catch(SQLException e) {
//        	logger.log(Level.SEVERE, "Failed to retrieve record from the database", e);
//            throw e;	
//        }
//       
//    }
//    public T getEntityByUserName(String userName) throws SQLException {
//        String sql = "SELECT * FROM " + getTableName() + " WHERE userName = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, userName);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//            	 logger.info("Record retrived from the database");
//                return mapResultSetToEntity(rs);
//            } else {
//            	logger.warning("No record found with the provided userName");
//                return null;	
//            }
//        }catch(SQLException e) {
//        	logger.log(Level.SEVERE, "Failed to retrieve record from the database", e);
//            throw e;	
//        }
//       
//    }
//
//    public List<T> getAllEntities() throws SQLException {
//        List<T> entities = new ArrayList<>();
//        String sql = "SELECT * FROM " + getTableName();
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                entities.add(mapResultSetToEntity(rs));
//            }
//            logger.info("All records retrieved from the database");
//            return entities;
//        }catch (SQLException e) {
//            logger.log(Level.SEVERE, "Failed to retrieve records from the database", e);
//            throw e;
//        }
//        
//    }
//
//    public void updateEntity(T entity, int id) throws SQLException {
//        String sql = "UPDATE " + getTableName() + " SET ... WHERE idUser = ?"; // Update based on fields
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            mapEntityToStatement(entity, stmt);
//            stmt.setInt( /* last parameter */1, id);
//            stmt.executeUpdate();
//            logger.info("Record updated in the database");
//        }catch (SQLException e) {
//            logger.log(Level.SEVERE, "Failed to update record in the database", e);
//            throw e;
//        }
//    }
//
//    public void deleteEntity(int id) throws SQLException {
//        String sql = "DELETE FROM " + getTableName() + " WHERE idUser = ?";
//        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//        
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//            logger.info("Record deleted from the database");
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Failed to delete record from the database", e);
//            throw e;
//        }
//    }
//}
