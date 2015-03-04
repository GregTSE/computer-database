package com.excilys.formation.cdb.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import com.excilys.formation.cdb.exception.ConnectionException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionDAO {

    INSTANCE;

    private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
    private String user = "admincdb";
    private String passwd = "qwerty1234";
    public BoneCP connectionPool;

    private ConnectionDAO() {
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    BoneCPConfig config = new BoneCPConfig(); 
	    config.setJdbcUrl( url ); 
	    config.setUsername( user ); 
	    config.setPassword( passwd );
	    config.setMinConnectionsPerPartition( 5 );  // définition du nombre min de connexions par partition
	    config.setMaxConnectionsPerPartition( 10 ); // définition du nombre max de connexions par partition
	    config.setPartitionCount( 1 );          // définition du nombre de partitions
	    connectionPool = new BoneCP( config ); 
	} catch (ClassNotFoundException | SQLException e) {
	    throw new ConnectionException("Connection failed");
	} 
    }

    /**
     * Initialize the connection
     */
    public void init() {
//	try {
//        	if (connection == null || connection.isClosed()) {
//        	    connection = DriverManager.getConnection(url, user, passwd);
//        	}
//	} catch (SQLException sqle){
//	    throw new ConnectionException("Connection cannot be initialized");
//	}
    }

    /**
     * Close the connection
     */
    public void close() {
//	try {
//	    connection.close();
//	} catch (SQLException e) {
//	    throw new ConnectionException("Database cannot be closed");
//	}
    }

}
