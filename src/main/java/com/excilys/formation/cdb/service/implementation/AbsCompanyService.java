package com.excilys.formation.cdb.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.exception.ConnectionException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.service.ICompanyService;

public abstract class AbsCompanyService implements ICompanyService {

    public AbsCompanyService() {
    }

    @Override
    public final List<Company> findAll() {
	Connection connection = getConnection();
	List<Company> companies = null;
	companies = findAllAbs(connection);
	closeConnection(connection);
	return companies;
    }

    @Override
    public final void delete(Long id) {
	Logger logger = LoggerFactory.getLogger(AbsCompanyService.class);
	Connection connection = null;
	try {
	    connection = ConnectionDAO.INSTANCE.connectionPool.getConnection();
	    connection.setAutoCommit(false);
	    deleteAbs(id, connection);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		logger.error("SQL Exception (transaction)");
		connection.rollback();
	    } catch (SQLException e1) {
		// TODO Auto-generated catch block
		logger.error("SQL Exception (rollback)");
		e1.printStackTrace();
	    }
	    e.printStackTrace();
	} finally {
	    try {
		connection.setAutoCommit(true);
		connection.close();
	    } catch (SQLException e) {
		logger.error("SQL Exception (close)");
		throw new ConnectionException("Connection cannot be closed");
	    }
	}
    }

    public abstract List<Company> findAllAbs(Connection connection);

    public abstract void deleteAbs(Long id, Connection connection);

    private Connection getConnection() {
	return ConnectionDAO.INSTANCE.getConnection();
    }

    private void closeConnection(Connection connection) {
	try {
	    
	    if (connection != null && !connection.isClosed()) {
		connection.close();
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    

}
