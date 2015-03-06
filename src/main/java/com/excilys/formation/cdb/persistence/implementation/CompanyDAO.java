package com.excilys.formation.cdb.persistence.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.exception.ConnectionException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ICompanyDAO;

public enum CompanyDAO implements ICompanyDAO {

    INSTANCE;
    
    private Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#findAll()
     */
    @Override
    public List<Company> findAll(Connection connection) {
	
	List<Company> companies = new ArrayList<Company>();
	String query = "SELECT * FROM company";
	ResultSet results;
	try {
	   
	    Statement stmt = connection.createStatement();
	    results = stmt.executeQuery(query);

	    while (results.next()) {
		Company company = new Company();
		company.setId(results.getInt(1));
		company.setName(results.getString(2));
		companies.add(company);
	    }
	    
	} catch (Exception e) {
	    logger.error("SQL Exception");
	    e.printStackTrace();
	}
	return companies;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id, Connection connection) {
	ComputerDAO.INSTANCE.deleteByCompany(id, connection);
	String query = "DELETE FROM company WHERE id=?";
	try {
	    connection.setAutoCommit(false);
	    ComputerDAO.INSTANCE.deleteByCompany(id, connection);
	    PreparedStatement pstmt = connection.prepareStatement(query);
	    pstmt.setLong(1, id);
	    pstmt.executeUpdate();
	    connection.commit();
	    
	} catch (SQLException e) {
	    try {
		logger.error("SQL Exception (delete request)");
		connection.rollback();
	    } catch (SQLException e1) {
		// TODO Auto-generated catch block
		logger.error("SQL Exception (rollback)");
		e1.printStackTrace();
	    }
	    e.printStackTrace();
	}
	finally {
	    try {
		connection.setAutoCommit(true);
		connection.close();
	    } catch (SQLException e) {
		logger.error("SQL Exception (close)");
		throw new ConnectionException("Connection cannot be closed");
	    }
	}
	
	
    }
}