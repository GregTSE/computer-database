package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.exception.ConnectionException;
import com.excilys.formation.cdb.model.Company;

public enum CompanyDAO {

    INSTANCE;

    /**
     * Find all companies
     * @return companies list
     */
    public List<Company> findAll() {
	
	ArrayList<Company> companies = new ArrayList<Company>();
	String query = "SELECT * FROM company";
	ResultSet results;
	Connection connection = null; 
	try {
	    connection = ConnectionDAO.INSTANCE.connectionPool.getConnection();
	    Statement stmt = connection.createStatement();
	    results = stmt.executeQuery(query);

	    while (results.next()) {
		Company company = new Company();
		company.setId(results.getInt(1));
		company.setName(results.getString(2));
		companies.add(company);
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (!connection.isClosed()) {
		    try {
			connection.close();
		    } catch (SQLException e) {
			throw new ConnectionException(
				"Connection cannot be closed");
		    }
		}
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	return companies;
    }

    public void delete(Long id) {
	ComputerDAO.INSTANCE.deleteByCompany(id);
	String query = "DELETE FROM company WHERE id=?";
	Connection connection = null;
	try {
	    connection = ConnectionDAO.INSTANCE.connectionPool.getConnection();
	    connection.setAutoCommit(false);
	    ComputerDAO.INSTANCE.deleteByCompany(id);
	    PreparedStatement pstmt = connection.prepareStatement(query);
	    pstmt.setLong(1, id);
	    pstmt.executeUpdate();
	    connection.commit();
	    
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    e.printStackTrace();
	}
	finally {
	    try {
		connection.setAutoCommit(true);
		connection.close();
	    } catch (SQLException e) {
		throw new ConnectionException("Connection cannot be closed");
	    }
	}
	
	
    }
}