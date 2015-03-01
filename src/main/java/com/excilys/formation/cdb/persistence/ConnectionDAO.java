package com.excilys.formation.cdb.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import com.excilys.formation.cdb.exception.ConnectionException;

//public enum Example{
//    INSTANCE;
//
//    public static Example getInstance(){
//         return Example.INSTANCE;
//    }
public class ConnectionDAO {

	private static Connection connection = null;

	private ConnectionDAO() throws ConnectionException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			String user = "admincdb";
			String passwd = "qwerty1234";
			connection = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			throw new ConnectionException("Database cannot be opened");
		}
	}

	public static Connection getInstance() throws ConnectionException, SQLException {
		if (connection == null || connection.isClosed()) {
			new ConnectionDAO(); 
		}
		return connection;
	}
	
	public static void close() throws ConnectionException{
	    try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
		    }  
	    } catch (SQLException e) {
	    	throw new ConnectionException("Database cannot be closed");
	    }	
	}

}
