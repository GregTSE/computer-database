package com.excilys.formation.cdb.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionDAO {

	private static Connection conn = null;

	private ConnectionDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			String user = "admincdb";
			String passwd = "qwerty1234";
			conn = (Connection) DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (conn == null) {
			new ConnectionDAO();
		}
		return conn;
	}
	
	public static void close(){
	    if (conn != null && !conn.isClosed()) {
		    try {
			conn.close();
		    } catch (SQLException e) {
			throw new IllegalStateException("Database cannot be closed");
		    }
	    }	
	}

}
