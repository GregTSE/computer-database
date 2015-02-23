package com.excilys.formation.cdb.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.formation.cdb.bean.Computer;
import com.excilys.formation.cdb.bean.ComputersList;
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
	String query = "select comput.name, introduced, discontinued, c.name from computer comput left outer join company c on c.id = comput.company_id WHERE comput.id=?";
	ResultSet results;

	try {
	    PreparedStatement preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, id);
	    results = preparedStmt.executeQuery();

	    if (results.first()) {
		computer = new Computer(new Long(id), results.getString(1),
			results.getDate(2), results.getDate(3),
			results.getString(4));
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
    public ComputersList findAll() {
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

	ComputersList compsList = new ComputersList(computers);
	return compsList;
    }

    /**
     * insert in the database the parameter 'computer'
     * @param computer to insert in the database
     */
    public void createComputer(Computer computer) {
	String name = computer.getName();
	Date dateIntroduced = computer.getDateIntroduced();
	Date dateDiscontinued = computer.getDateDiscontinued();
	//recup company
	String nameCompany = computer.getCompany();
	String queryCompanyId = "select * from computer where id=?";
	String query = "insert into computer (name,introduced,discontinued,company_id) values (?,?,?,?)";

	try {
		ResultSet rslt;
		PreparedStatement stmt0 = connection.prepareStatement(queryCompanyId);
		stmt0.setString(1, nameCompany);
		rslt = stmt0.executeQuery();
		
	    PreparedStatement preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setDate(2, dateIntroduced);
	    preparedStmt.setDate(3, dateDiscontinued);
	    if (rslt.first()) {
	    	int idCompany = rslt.getInt(1);
	    	preparedStmt.setInt(4, idCompany);
	    }
	    else
	    	preparedStmt.setNull(4, 0);
	    	
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
	Date dateIntroduced = computer.getDateIntroduced();
	Date dateDiscontinued = computer.getDateDiscontinued();
	String nameCompany = computer.getCompany();
	
	String query = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id=?";
	String queryCompanyId = "select * from computer where id=?";
	
	try {
	    ResultSet rslt;
	    PreparedStatement stmt0 = connection.prepareStatement(queryCompanyId);
	    stmt0.setString(1, nameCompany);
	    rslt = stmt0.executeQuery();
		
	    PreparedStatement preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setDate(2, dateIntroduced);
	    preparedStmt.setDate(3, dateDiscontinued);
	    
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
