package com.excilys.formation.cdb.persistence;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.mysql.jdbc.Connection;

public class ComputerDAO {

    private Connection connection;

    public ComputerDAO(Connection connection) {
	this.connection = connection;
    }

    /**
     * Build an object Computer and fill the fields with the informations from the database
     * @param id of the computer
     * @return an instance of the Computer class
     */
    public Computer find(int id) {

	Computer computer = null;
	String query = "select comput.name, introduced, discontinued, company_id , c.name from computer comput left outer join company c on c.id = comput.company_id WHERE comput.id=?";
	ResultSet results;
	try {
	    PreparedStatement preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, id);
	    results = preparedStmt.executeQuery();
	    Company company = null;
	    if (results.first()) {
		Long idCompany = results.getLong(4);
		if (idCompany != null) {
		   company = new Company(idCompany,results.getString(5));		   
		}
		Timestamp introducedTS = results.getTimestamp(2);
		LocalDate introduced = null, discontinued = null;
		Timestamp discontinuedTS = results.getTimestamp(3);
		if( introducedTS != null )
		    introduced = introducedTS.toLocalDateTime().toLocalDate();
		if ( discontinuedTS != null ) {
		    discontinued = discontinuedTS.toLocalDateTime().toLocalDate();
		}
		
		computer = new Computer(new Long(id), results.getString(1),
			introduced , discontinued, company);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return computer;
    }

    /**
     * Build a list of computers with fields 'id' and 'name'
     * @return the list of computers
     */
    public List<Computer> findAll() {
	ArrayList<Computer> computers = new ArrayList<Computer>();

	String query = "SELECT id, name FROM computer";
	ResultSet results;

	try {
	    Statement stmt = connection.createStatement();

	    results = stmt.executeQuery(query);

	    while (results.next()) {
		Computer computer = new Computer();
		computer.setId(results.getInt(1));
		computer.setName(results.getString(2));
		computers.add(computer);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	
	return computers;
    }

    /**
     * insert in the database the parameter 'computer'
     * @param computer to insert in the database
     */
    public void create(String name, String introduced, String discontinued, String idCompany) {
	String queryCompanyId = "select * from company where id=?";
	String query = "insert into computer (name,introduced,discontinued,company_id) values (?,?,?,?)";

	try {
	    ResultSet rslt;
	    PreparedStatement stmt0 = connection.prepareStatement(queryCompanyId);
	    stmt0.setString(1, idCompany);
	    rslt = stmt0.executeQuery();
		
	    PreparedStatement preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setString(2, introduced);
	    preparedStmt.setString(3, discontinued);
	    if (rslt.first()) {
	    	preparedStmt.setInt(4, Integer.parseInt(idCompany));
	    } else {
	    	preparedStmt.setNull(4, 0);
	    }
	    preparedStmt.executeUpdate();
	   
	} catch (SQLException e) {
	    System.err.println("Mauvaise date");
	    e.printStackTrace();
	}
    }

    /**
     * update a computer by id
     * @param computer with new fields
     */
    public void update(Computer computer) {
	
	String name = computer.getName();
	Timestamp dateIntroduced = new Timestamp(computer.getDateIntroduced().toEpochDay());
	Timestamp dateDiscontinued = new Timestamp(computer.getDateDiscontinued().toEpochDay());
	String nameCompany = computer.getCompany().getName();
	
	String query = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id=?";
	String queryCompanyId = "select * from computer where id=?";
	
	try {
	    ResultSet rslt;
	    PreparedStatement stmt0 = connection.prepareStatement(queryCompanyId);
	    stmt0.setString(1, nameCompany);
	    rslt = stmt0.executeQuery();
		
	    PreparedStatement preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setTimestamp(2, dateIntroduced);
	    preparedStmt.setTimestamp(3, dateDiscontinued);
	    
	    if (rslt.first()) {
		int idCompany = rslt.getInt(1);
	    	preparedStmt.setInt(4, idCompany);
	    }
	    else {
	    	preparedStmt.setNull(4, 0);
	    }
	    
	    int id = computer.getId().intValue();
	    preparedStmt.setInt(5,id);
	    preparedStmt.executeUpdate();
	   
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public void delete(int id) {
	String query = "delete from computer where id=" + id;
	try {
	    Statement stmt = connection.createStatement();
	    stmt.executeUpdate(query);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
}
