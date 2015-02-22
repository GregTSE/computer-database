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
     * 
     * @param id
     *            L'identifiant de l'ordinateur dans la base de données
     * @return Une instance de la classe Computer
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

    public void createComputer(Computer computer) {
	String name = computer.getName();
	Date dateIntroduced = computer.getDateIntroduced();
	Date dateDiscontinued = computer.getDateDiscontinued();
	int company = 1;// computer.getCompany();
	String query = "insert into computer (name,introduced,discontinued,company_id) values (?,?,?,?)";

	try {
	    PreparedStatement preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setDate(2, dateIntroduced);
	    preparedStmt.setDate(3, dateDiscontinued);
	    preparedStmt.setInt(4, company);
	    preparedStmt.executeUpdate();
	    // preparedStmt.setInt(4, company);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public void update() {

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
