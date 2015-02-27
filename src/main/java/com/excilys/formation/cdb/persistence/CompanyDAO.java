package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.model.Company;
import java.sql.Connection;

public class CompanyDAO {
	
	private Connection connection;

	public CompanyDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Company> findAll() {
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
	
	public Company find(Long id){
	    Company c = null;
	    String query = "SELECT name FROM company WHERE id="+id;
	    try {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
	       
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    finally {
		return c;
	    }
	    
	    
	}
}