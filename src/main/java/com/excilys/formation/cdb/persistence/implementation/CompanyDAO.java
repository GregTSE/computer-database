package com.excilys.formation.cdb.persistence.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.persistence.ICompanyDAO;

public enum CompanyDAO implements ICompanyDAO {

    INSTANCE;
    
    private Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#findAll()
     */
    @Override
    public List<Company> findAll() {
	
	List<Company> companies = new ArrayList<Company>();
	String query = "SELECT * FROM company";
	 Statement stmt = null;
	ResultSet results = null;
	try {	   
	    stmt = ConnectionDAO.INSTANCE.getConnection().createStatement();
	    results = stmt.executeQuery(query);

	    while (results.next()) {
		Company company = new Company();
		company.setId(results.getInt(1));
		company.setName(results.getString(2));
		companies.add(company);
	    }
	    
	} catch (SQLException e) {
	    logger.error("SQL Exception : findAll(companies)");
	    e.printStackTrace();
	} finally {
	    closeStatement(stmt);
	    closeResultSet(results);
	}
	return companies;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
	String query = "DELETE FROM company WHERE id=?";
	PreparedStatement pstmt = null;
	try {
	    pstmt = ConnectionDAO.INSTANCE.getConnection().prepareStatement(query);
	    pstmt.setLong(1, id);
	    pstmt.executeUpdate();    
	} catch (SQLException e) {
	    logger.error("SQL Exception : delete company request");
	    e.printStackTrace();
	}
	finally {
	   closeStatement(pstmt);
	}
	
	
    }
    
    
    private void closeStatement(PreparedStatement pstmt) {
	try {
	    if(pstmt != null) {
		pstmt.close();
	    }
	} catch (SQLException e) {
	    logger.error("SQL Exception : closure PreparedStatement");
	    e.printStackTrace();
	}
    }
    
    private void closeStatement(Statement stmt) {
	try {
	    if(stmt != null) {
		stmt.close();
	    }
	} catch (SQLException e) {
	    logger.error("SQL Exception : closure Statement");
	    e.printStackTrace();
	}
    }
    
    private void closeResultSet(ResultSet rslt) {
	try {
	    if(rslt != null) {
		rslt.close();
	    }
	} catch (SQLException e) {
	    logger.error("SQL Exception : closure ResultSet");
	    e.printStackTrace();
	}
    }

}