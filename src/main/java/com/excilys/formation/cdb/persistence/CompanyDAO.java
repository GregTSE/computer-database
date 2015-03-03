package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.model.Company;

public class CompanyDAO {

    public CompanyDAO() {
	super();
    }

    /**
     * Find all companies
     * @return companies list
     */
    public List<Company> findAll() {
	
	ArrayList<Company> companies = new ArrayList<Company>();
	String query = "SELECT * FROM company";
	ResultSet results;

	try {
	    ConnectionDAO.INSTANCE.init();
	    Statement stmt = ConnectionDAO.INSTANCE.connection.createStatement();
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
	    ConnectionDAO.INSTANCE.close();
	}

	return companies;
    }

//    public Company find(Long id) {
//	Company c = null;
//	String query = "SELECT name FROM company WHERE id=" + id;
//	try {
//	    ConnectionDAO.INSTANCE.init();
//	    Statement stmt = ConnectionDAO.INSTANCE.connection
//		    .createStatement();
//	    ResultSet rs = stmt.executeQuery(query);
//
//	} catch (SQLException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	} finally {
//	    ConnectionDAO.INSTANCE.close();
//	}
//	return c;
//
//    }
}