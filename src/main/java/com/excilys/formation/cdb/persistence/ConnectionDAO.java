package com.excilys.formation.cdb.persistence;

import java.sql.SQLException;

import com.excilys.formation.cdb.exception.ConnectionException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionDAO {

    INSTANCE;

    private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
    private String user = "admincdb";
    private String passwd = "qwerty1234";
    private int minConnectionsPerPartition = 5;
    private int maxConnectionsPerPartition = 10;
    private int partitionCount = 1;
    
    public BoneCP connectionPool;

    private ConnectionDAO() {
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    BoneCPConfig config = new BoneCPConfig(); 
	    config.setJdbcUrl( url ); 
	    config.setUsername( user ); 
	    config.setPassword( passwd );
	    config.setMinConnectionsPerPartition( minConnectionsPerPartition  );
	    config.setMaxConnectionsPerPartition( maxConnectionsPerPartition );
	    config.setPartitionCount( partitionCount );
	    connectionPool = new BoneCP( config ); 
	} catch (ClassNotFoundException | SQLException e) {
	    throw new ConnectionException("Connection failed");
	} 
    }



}
