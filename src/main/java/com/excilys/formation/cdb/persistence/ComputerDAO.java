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

public class ComputerDAO {

    public ComputerDAO() {
	super();
    }

    /***  METHODS FOR CLI ***/

    /**
     * Build an object Computer and fill the fields with the informations from
     * the database
     * 
     * @param id
     *            of the computer
     * @return an instance of the Computer class
     */
    public Computer find(int id) {

	Computer computer = null;
	String query = "SELECT comput.name, introduced, discontinued, company_id , c.name "
		+ "FROM computer comput LEFT OUTER JOIN company c ON c.id = comput.company_id"
		+ "WHERE comput.id=?";
	ResultSet results;
	try {
	    ConnectionDAO.INSTANCE.init();
	    PreparedStatement preparedStmt = ConnectionDAO.INSTANCE.connection.prepareStatement(query);
	    preparedStmt.setInt(1, id);
	    results = preparedStmt.executeQuery();
	    Company company = null;
	    
	    if (results.first()) {
		Long idCompany = results.getLong(4);
		
		if (idCompany != null) {
		    company = new Company(idCompany, results.getString(5));
		}
		
		Timestamp introducedTS = results.getTimestamp(2);
		LocalDate introduced = null, discontinued = null;
		Timestamp discontinuedTS = results.getTimestamp(3);
		
		if (introducedTS != null) {
		    introduced = introducedTS.toLocalDateTime().toLocalDate();
		}
		
		if (discontinuedTS != null) {
		    discontinued = discontinuedTS.toLocalDateTime().toLocalDate();
		}

		computer = new Computer(new Long(id), results.getString(1), introduced, discontinued, company);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDAO.INSTANCE.close();  
	}
	return computer;
    }

    /**
     * Build a list of computers with fields 'id' and 'name'
     * 
     * @return the list of computers
     */
    public List<Computer> findAll() {

	ArrayList<Computer> computers = new ArrayList<Computer>();

	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name " 
	+ "FROM computer comput LEFT OUTER JOIN company c ON c.id = comput.company_id";
	ResultSet results;

	try {
	    ConnectionDAO.INSTANCE.init();
	    Statement stmt = ConnectionDAO.INSTANCE.connection.createStatement();

	    results = stmt.executeQuery(query);

	    while (results.next()) {
		Company company = null;
		Long idCompany = results.getLong(5);
		
		if (idCompany > 0) {
		    company = new Company(idCompany, results.getString(6));
		}
		
		Timestamp introducedTS = results.getTimestamp(3);
		LocalDate introduced = null, discontinued = null;
		Timestamp discontinuedTS = results.getTimestamp(4);
		
		if (introducedTS != null)
		    introduced = introducedTS.toLocalDateTime().toLocalDate();
		
		if (discontinuedTS != null) {
		    discontinued = discontinuedTS.toLocalDateTime()
			    .toLocalDate();
		}
		
		computers.add(new Computer(results.getLong(1), results.getString(2), introduced, discontinued, company));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDAO.INSTANCE.close();
	}
	return computers;
    }

    
    /***  METHODS FOR WEB-UI ***/
    
    /**
     * Find computers whose ID is between num and num+offset
     * 
     * @param num
     * @param offset
     * @return list of computers
     */
    public List<Computer> findAll(int num, int offset) {
	ArrayList<Computer> computers = new ArrayList<Computer>();
	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name " 
			+ "FROM computer comput LEFT OUTER JOIN company c ON c.id = comput.company_id LIMIT ?, ?";
	ResultSet results = null;

	try {
	    ConnectionDAO.INSTANCE.init();
	    PreparedStatement pstmt = ConnectionDAO.INSTANCE.connection.prepareStatement(query);
	    pstmt.setInt(1, num);
	    pstmt.setInt(2, offset);
	    results = pstmt.executeQuery();

	    while (results.next()) {
		
		Company company = null;
		Long idCompany = results.getLong(5);
		
		if (idCompany > 0) {
		    company = new Company(idCompany, results.getString(6));
		}
		
		Timestamp introducedTS = results.getTimestamp(3);
		LocalDate introduced = null, discontinued = null;
		Timestamp discontinuedTS = results.getTimestamp(4);
		
		if (introducedTS != null)
		    introduced = introducedTS.toLocalDateTime().toLocalDate();
		
		if (discontinuedTS != null) {
		    discontinued = discontinuedTS.toLocalDateTime().toLocalDate();
		}
		
		computers.add(new Computer(results.getLong(1), results.getString(2), introduced, discontinued, company));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDAO.INSTANCE.close();
	}
	return computers;
    }

    /**
     * insert in the database the parameter 'computer'
     * 
     * @param computer
     *            to insert in the database
     */
    public void create(String name, String introduced, String discontinued, Company company) {
	
	String query = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";

	try {
	    ConnectionDAO.INSTANCE.init();
	    PreparedStatement preparedStmt = ConnectionDAO.INSTANCE.connection.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setString(2, introduced);
	    preparedStmt.setString(3, discontinued);
	    
	    if (company != null) {
		preparedStmt.setLong(4, company.getId());
	    } else {
		preparedStmt.setNull(4, 0);
	    }
	    preparedStmt.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDAO.INSTANCE.close();
	}
    }

    /**
     * Update a computer by id
     * 
     * @param computer
     *            with new fields
     */
    public void update(Computer computer) {

	String name = computer.getName();
	Timestamp dateIntroduced = new Timestamp(computer.getDateIntroduced().toEpochDay());
	Timestamp dateDiscontinued = new Timestamp(computer.getDateDiscontinued().toEpochDay());
	String nameCompany = computer.getCompany().getName();
	String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	String queryCompanyId = "select * from computer where id=?";

	try {
	    ConnectionDAO.INSTANCE.init();
	    ResultSet rslt;
	    PreparedStatement stmt0 = ConnectionDAO.INSTANCE.connection.prepareStatement(queryCompanyId);
	    stmt0.setString(1, nameCompany);
	    rslt = stmt0.executeQuery();
	    PreparedStatement preparedStmt = ConnectionDAO.INSTANCE.connection.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setTimestamp(2, dateIntroduced);
	    preparedStmt.setTimestamp(3, dateDiscontinued);

	    if (rslt.first()) {
		int idCompany = rslt.getInt(1);
		preparedStmt.setInt(4, idCompany);
	    } else {
		preparedStmt.setNull(4, 0);
	    }

	    int id = computer.getId().intValue();
	    preparedStmt.setInt(5, id);
	    preparedStmt.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDAO.INSTANCE.close();
	}
    }

    /**
     * Delete the computer whose ID is 'id'
     * 
     * @param id
     */
    public void delete(int id) {
	//@TODO use a preparedStatement
	String query = "DELETE FROM computer WHERE id=" + id;
	try {
	    ConnectionDAO.INSTANCE.init();
	    Statement stmt = ConnectionDAO.INSTANCE.connection.createStatement();
	    stmt.executeUpdate(query);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Search of computers which
     * @param str
     * @param num
     * @param offset
     * @return a computers list
     */
    public List<Computer> search(String str, int num, int offset) {

	ArrayList<Computer> computers = new ArrayList<Computer>();
	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name FROM computer comput LEFT OUTER JOIN company c on c.id = comput.company_id  WHERE comput.name LIKE ? LIMIT ?, ?";
	ResultSet results = null;
	PreparedStatement pstmt = null;
	try {
	    ConnectionDAO.INSTANCE.init();
	    pstmt = ConnectionDAO.INSTANCE.connection.prepareStatement(query);
	    pstmt.setString(1, str + '%');
	    pstmt.setInt(2, num);
	    pstmt.setInt(3, offset);
	    results = pstmt.executeQuery();

	    while (results.next()) {
		Company company = null;
		Long idCompany = results.getLong(5);
		if (idCompany > 0) {
		    company = new Company(idCompany, results.getString(6));
		}
		Timestamp introducedTS = results.getTimestamp(3);
		LocalDate introduced = null, discontinued = null;
		Timestamp discontinuedTS = results.getTimestamp(4);
		if (introducedTS != null)
		    introduced = introducedTS.toLocalDateTime().toLocalDate();
		if (discontinuedTS != null) {
		    discontinued = discontinuedTS.toLocalDateTime()
			    .toLocalDate();
		}
		computers.add(new Computer(results.getLong(1), results
			.getString(2), introduced, discontinued, company));
	    }
	    pstmt.close();
	    results.close();

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDAO.INSTANCE.close();
	}
	return computers;
    }

    public int count() {
	String query = "SELECT COUNT(id) FROM computer";
	int result = 0;
	Statement stmt;
	try {
	    ConnectionDAO.INSTANCE.init();
	    stmt = ConnectionDAO.INSTANCE.connection.createStatement();
	    ResultSet rslt = stmt.executeQuery(query);
	    if (rslt.first()) {
		result = rslt.getInt(1);
	    }
	    rslt.close();
	    stmt.close();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    ConnectionDAO.INSTANCE.close();
	}

	return result;
    }

}
