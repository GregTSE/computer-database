package com.excilys.formation.cdb.persistence.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.exception.ConnectionException;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.persistence.ICompanyDAO;

public enum CompanyDAO implements ICompanyDAO {

    INSTANCE;

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#findAll()
     */
    @Override
    public List<Company> findAll(Connection connection) {
	
	ArrayList<Company> companies = new ArrayList<Company>();
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