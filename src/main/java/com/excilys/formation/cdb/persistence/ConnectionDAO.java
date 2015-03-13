package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionDAO {

    private DataSource dataSource;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}
    
    final Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    private ConnectionDAO() {
//	try {
//
//	} catch (ClassNotFoundException | SQLException e) {
//	    throw new ConnectionException("Connection failed");
//	}
    }

    public Connection getConnection() {
	Connection connection = null;
	try {
	    if (connectionThreadLocal.get() != null && !connectionThreadLocal.get().isClosed()) {
	        return connectionThreadLocal.get();
	    }
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	try {
	    connection = dataSource.getConnection();
	    connectionThreadLocal.set(connection);
	} catch (SQLException e) {
	    logger.error("SQL Exception : getConnection from pool");
	    e.printStackTrace();
	}

	return connection;
    }
    
    public void closeConnection() {
	try {
	    Connection connection = connectionThreadLocal.get();
	    if (connection != null && !connection.isClosed()) {
		connection.close();
		connectionThreadLocal.remove();
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
