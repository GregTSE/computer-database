package com.excilys.formation.cdb.persistence.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.IComputerDAO;

public enum ComputerDAO implements IComputerDAO {

    INSTANCE;

    final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    /*** METHODS FOR CLI ***/

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#find(int)
     */
    @Override
    public Computer find(int id, Connection connection) {

	Computer computer = null;	
	ResultSet results = null;
	PreparedStatement preparedStmt = null;

	String query = "SELECT comput.name, introduced, discontinued, company_id , c.name "
		+ "FROM computer comput LEFT OUTER JOIN company c ON c.id = comput.company_id"
		+ "WHERE comput.id=?";



	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, id);
	    results = preparedStmt.executeQuery();
	    Company company = null;
	    //MAPPER COMPUTER !!!
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
		    discontinued = discontinuedTS.toLocalDateTime()
			    .toLocalDate();
		}

		computer = new Computer(new Long(id), results.getString(1),
			introduced, discontinued, company);
	    }

	} catch (SQLException e) {
	    logger.error("SQL Exception (request find(id)");
	    e.printStackTrace();
	} finally {
	    try {
		if (results!=null) {
		    results.close();
		}
		if (preparedStmt!=null) {
		    preparedStmt.close();
		}
	    } catch (SQLException e) {
		logger.error("SQL Exception (close statement)");
		e.printStackTrace();
	    }
	}
	return computer;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#findAll()
     */
    @Override
    public List<Computer> findAll(Connection connection) {

	ArrayList<Computer> computers = new ArrayList<Computer>();

	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name "
		+ "FROM computer comput LEFT OUTER JOIN company c ON c.id = comput.company_id";
	ResultSet results = null;
	Statement stmt = null;
	
	try {
	
	    stmt = connection.createStatement();

	    results = stmt.executeQuery(query);
	    
	    while (results.next()) {		
		computers.add(MapperDAO.rowToComputer(results));
	    }

	} catch (Exception e) {
	    logger.error("SQL Exception (request findAll())");
	    e.printStackTrace();
	} finally {
	    try {
		if (results!=null) {
		    results.close();
		}
		if (stmt!=null) {
		    stmt.close();
		}
	    } catch (SQLException e) {
		logger.error("SQL Exception (closure statement/resultSet)");
		e.printStackTrace();
	    }
	}
	return computers;
    }

    /*** METHODS FOR WEB-UI ***/

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#findAll(int, int)
     */
    @Override
    public List<Computer> findAll(int num, int offset, Connection connection) {
	ArrayList<Computer> computers = new ArrayList<Computer>();
	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name "
		+ "FROM computer comput LEFT OUTER JOIN company c ON c.id = comput.company_id LIMIT ?, ?";
	ResultSet results = null;
	PreparedStatement pstmt = null;
	try {
	    pstmt = connection.prepareStatement(query);
	    pstmt.setInt(1, num);
	    pstmt.setInt(2, offset);
	    results = pstmt.executeQuery();

	    while (results.next()) {
		computers.add(MapperDAO.rowToComputer(results));
	    }

	} catch (Exception e) {
	    logger.error("SQL Exception (findAll())");
	    e.printStackTrace();
	} finally {
	    try {
		if (results!=null) {
		    results.close();
		}
		if (pstmt!=null) {
		    pstmt.close();
		}
	    } catch (SQLException e) {
		logger.error("SQL Exception : closure statement/resultSet");
		e.printStackTrace();
	    }
	}
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void create(String name, String introduced, String discontinued,
	    Company company, Connection connection) {
	String query = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	PreparedStatement preparedStmt = null;
	try {
	    preparedStmt = connection.prepareStatement(query);
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
	    logger.error("SQL Exception : create()");
	    e.printStackTrace();
	} finally {
	    try {
		if (preparedStmt!=null) {
		    preparedStmt.close();
		}
	    } catch (SQLException e) {
		logger.error("SQL Exception : closure statement/closure");
		e.printStackTrace();
	    }
	} 
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public void update(Computer computer, Connection connection) {
	String name = computer.getName();
	Timestamp dateIntroduced = new Timestamp(computer.getDateIntroduced()
		.toEpochDay());
	Timestamp dateDiscontinued = new Timestamp(computer
		.getDateDiscontinued().toEpochDay());
	String nameCompany = computer.getCompany().getName();
	String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
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
	    } else {
		preparedStmt.setNull(4, 0);
	    }

	    int id = computer.getId().intValue();
	    preparedStmt.setInt(5, id);
	    preparedStmt.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	} 
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#delete(long)
     */
    @Override
    public void delete(long id, Connection connection) {
	// @TODO use a preparedStatement
	String query = "DELETE FROM computer WHERE id=" + id;
	try {
	    Statement stmt = connection.createStatement();
	    stmt.executeUpdate(query);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#search(java.lang.String, int, int)
     */
    @Override
    public List<Computer> search(String str, int num, int offset, Connection connection) {

	ArrayList<Computer> computers = new ArrayList<Computer>();
	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name FROM computer comput LEFT OUTER JOIN company c on c.id = comput.company_id  WHERE comput.name LIKE ? LIMIT ?, ?";
	ResultSet results = null;
	PreparedStatement pstmt = null;
	try {
	    pstmt = connection.prepareStatement(query);
	    pstmt.setString(1, str + '%');
	    pstmt.setInt(2, num);
	    pstmt.setInt(3, offset);
	    results = pstmt.executeQuery();

	    while (results.next()) {		
		computers.add(MapperDAO.rowToComputer(results));
	    }
	    
	    pstmt.close();
	    results.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#count(java.lang.String)
     */
    @Override
    public int count(String name, Connection connection) {
	String query = "SELECT COUNT(id) FROM computer WHERE name LIKE ?";
	int result = 0;
	PreparedStatement pstmt;
	try {
	    pstmt = connection.prepareStatement(query);
	    pstmt.setString(1, name + "%");
	    ResultSet rslt = pstmt.executeQuery();
	    if (rslt.first()) {
		result = rslt.getInt(1);
	    }
	    rslt.close();
	    pstmt.close();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return result;
    }
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#findByCompany(java.lang.Long)
     */
    @Override
    public List<Long> findByCompany(Long companyId, Connection connection) {
	
	List<Long> computersId = new ArrayList<Long>();
	String query = "SELECT id FROM computer WHERE company_id = ?";
	PreparedStatement pstmt;
	ResultSet results = null;
	
	try {
	    pstmt = connection.prepareStatement(query);
	    pstmt.setLong(1, companyId);
	    results = pstmt.executeQuery();

	    while (results.next()) {
		computersId.add(results.getLong(1));
	    }
	    pstmt.close();
	    results.close();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return computersId;
    }
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#deleteByCompany(java.lang.Long)
     */
    @Override
    public void deleteByCompany(Long id, Connection connection){
	List<Long> computersId = findByCompany(id, connection);
	for (Long computerId : computersId) {
	    delete(computerId, connection);
	    System.out.println("id : "+computerId + " supprimé!");
	}
	
    }

}
