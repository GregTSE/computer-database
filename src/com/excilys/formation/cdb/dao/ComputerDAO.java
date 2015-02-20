package com.excilys.formation.cdb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
		String query = "SELECT * FROM computer WHERE id=?";
		ResultSet results;

		try {
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, id);
			results = preparedStmt.executeQuery();

			if (results.first()) {
				computer = new Computer(id, results.getString(2),
						results.getDate(3), results.getDate(4),
						results.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return computer;
	}

	public ComputersList findAll() {
		ArrayList<Computer> computers = new ArrayList<Computer>();

		String query = "SELECT * FROM computer";
		ResultSet results;

		try {
			Statement stmt = connection.createStatement();

			results = stmt.executeQuery(query);

			while (results.next()) {
				Computer computer = new Computer();
				computer.setId(results.getInt(1));
				computer.setName(results.getString(2));
				computer.setDateIntroduced(results.getDate(3));
				computer.setDateDiscontinued(results.getDate(4));
				computer.setCompany(results.getString(5));
				computers.add(computer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ComputersList compsList = new ComputersList(computers);
		return compsList;
	}
	
	public void createComputer(Computer computer){
		String name = computer.getName();
		int id = computer.getId();
		Date dateIntroduced = computer.getDateIntroduced();
		Date dateDiscontinued = computer.getDateDiscontinued();
		String company = computer.getCompany();
		int id_company;
		String query = "insert into computer (id,name,introduced,discontinued,company_id) values (?,?,?,?,?)";
		
	}
	
}
