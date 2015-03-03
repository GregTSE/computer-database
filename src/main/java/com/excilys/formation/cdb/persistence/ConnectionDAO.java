package com.excilys.formation.cdb.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import com.excilys.formation.cdb.exception.ConnectionException;

public enum ConnectionDAO {

    INSTANCE;

    private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
    private String user = "admincdb";
    private String passwd = "qwerty1234";
    public Connection connection;

    private ConnectionDAO() {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (Exception e) {
	    throw new ConnectionException("Driver JDBC cannot be loaded");
	}
    }

    /**
     * Initialize the connection
     */
    public void init() {
	try {
        	if (connection == null || connection.isClosed()) {
        	    connection = DriverManager.getConnection(url, user, passwd);
        	}
	} catch (SQLException sqle){
	    throw new ConnectionException("Connection cannot be initialized");
	}
    }

    /**
     * Close the connection
     */
    public void close() {
	try {
	    connection.close();
	} catch (SQLException e) {
	    throw new ConnectionException("Database cannot be closed");
	}
    }

}
