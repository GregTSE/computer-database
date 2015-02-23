package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.formation.cdb.model.CompaniesList;
import com.excilys.formation.cdb.model.Company;
import com.mysql.jdbc.Connection;

public class CompanyDAO {
	
	private Connection connection;

	public CompanyDAO(Connection connection) {
		this.connection = connection;
	}

	public CompaniesList findAll() {
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
		CompaniesList compsList = new CompaniesList(companies);
		return compsList;
	}
}