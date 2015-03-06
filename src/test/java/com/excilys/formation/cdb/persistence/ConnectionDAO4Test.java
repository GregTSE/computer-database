package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.formation.cdb.exception.ConnectionException;

public enum ConnectionDAO4Test {

    INSTANCE;

    private String url = "jdbc:mysql://localhost:3306/cdb-test?zeroDateTimeBehavior=convertToNull";
    private String user = "admincdb";
    private String passwd = "qwerty1234";
    public Connection connection;

    private ConnectionDAO4Test() {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (Exception e) {
	    throw new ConnectionException("Database cannot be opened");
	}
    }

    public void init() throws ConnectionException, SQLException {
	if (connection == null || connection.isClosed()) {
	    connection = DriverManager.getConnection(url, user, passwd);
	}

    }

    public void close() throws ConnectionException {
	try {
	    connection.close();
	} catch (SQLException e) {
	    throw new ConnectionException("Database cannot be closed");
	}
    }
}