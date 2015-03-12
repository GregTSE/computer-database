package com.excilys.formation.cdb.persistence.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.IComputerDAO;
import com.excilys.formation.cdb.persistence.MapperDAO;

@Repository
public class ComputerDAO implements IComputerDAO {

    @Autowired
    private DataSource dataSource;

    final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    /*** METHODS FOR CLI ***/

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#find(int)
     */
    @Override
    public Computer find(long id) {

	Computer computer = null;
	 String query =
	 "SELECT comput.name, introduced, discontinued, company_id , c.name AS cname"
	 +
	 "FROM computer comput LEFT OUTER JOIN company c ON c.id = comput.company_id"
	 + "WHERE comput.id=?";
	
	 JdbcTemplate find = new JdbcTemplate(dataSource);
	 Object[] row = find.queryForObject(query, new Object[] { "name",
	 "introduced", "discontinued", "company_id", "cname" },
	 Object[].class);
	 Company company = null;
	
	 if (row[3] != null) {
	 Long idCompany = Long.parseLong(row[3].toString());
	 company = new Company(idCompany, row[4].toString());
	
	
	
	 LocalDate introduced = null, discontinued = null;
	
	 Timestamp discontinuedTS = Timestamp.valueOf(row[2].toString());
	 Timestamp introducedTS = Timestamp.valueOf(row[1].toString());
	
	 if (introducedTS != null) {
	 introduced = introducedTS.toLocalDateTime().toLocalDate();
	 }
	
	 if (discontinuedTS != null) {
	 discontinued = discontinuedTS.toLocalDateTime()
	 .toLocalDate();
	 }
	
	 computer = new Computer(new Long(id), row[0].toString(),
	 introduced, discontinued, company);
	 }

	return computer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#findAll()
     */
    @Override
    public List<Computer> findAll() {

	ArrayList<Computer> computers = new ArrayList<Computer>();

	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name AS cname FROM computer comput LEFT OUTER JOIN company c on c.id = comput.company_id";

	JdbcTemplate search = new JdbcTemplate(dataSource);

	List<Map<String, Object>> rows = search.queryForList(query);

	for (Map<String, Object> row : rows) {
	    Computer computer = null;
	    Company company = null;
	    if (row.get("cname") != null) {
		Long idCompany = Long.parseLong(row.get("company_id")
			.toString());
		company = new Company(idCompany, row.get("cname").toString());
	    }
	    LocalDate dateIntroduced = null, dateDiscontinued = null;

	    if (row.get("introduced") != null) {
		Timestamp introducedTS = Timestamp.valueOf(row
			.get("introduced").toString());
		dateIntroduced = introducedTS.toLocalDateTime().toLocalDate();
	    }

	    if (row.get("discontinued") != null) {
		Timestamp discontinuedTS = Timestamp.valueOf(row.get(
			"discontinued").toString());
		dateDiscontinued = discontinuedTS.toLocalDateTime()
			.toLocalDate();

	    }

	    computer = new Computer(Long.parseLong(row.get("id").toString()),
		    row.get("name").toString(), dateIntroduced,
		    dateDiscontinued, company);

	    computers.add(computer);
	}
	return computers;
    }

    /*** METHODS FOR WEB-UI ***/

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#findAll(int, int)
     */
    @Override
    public List<Computer> findAll(int num, int offset) {
	ArrayList<Computer> computers = new ArrayList<Computer>();

	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name AS cname FROM computer comput LEFT OUTER JOIN company c on c.id = comput.company_id LIMIT ?, ?";

	JdbcTemplate search = new JdbcTemplate(dataSource);

	List<Map<String, Object>> rows = search.queryForList(query,
		new Object[] { num, offset });

	for (Map<String, Object> row : rows) {
	    Computer computer = null;
	    Company company = null;
	    if (row.get("cname") != null) {
		Long idCompany = Long.parseLong(row.get("company_id")
			.toString());
		company = new Company(idCompany, row.get("cname").toString());
	    }
	    LocalDate dateIntroduced = null, dateDiscontinued = null;

	    if (row.get("introduced") != null) {
		Timestamp introducedTS = Timestamp.valueOf(row
			.get("introduced").toString());
		dateIntroduced = introducedTS.toLocalDateTime().toLocalDate();
	    }

	    if (row.get("discontinued") != null) {
		Timestamp discontinuedTS = Timestamp.valueOf(row.get(
			"discontinued").toString());
		dateDiscontinued = discontinuedTS.toLocalDateTime()
			.toLocalDate();

	    }

	    computer = new Computer(Long.parseLong(row.get("id").toString()),
		    row.get("name").toString(), dateIntroduced,
		    dateDiscontinued, company);

	    computers.add(computer);
	}
	return computers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#create(java.lang.String
     * , java.lang.String, java.lang.String,
     * com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void create(String name, String introduced, String discontinued,
	    Company company) {
	String query = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	PreparedStatement preparedStmt = null;
	try {
	    preparedStmt = dataSource.getConnection().prepareStatement(query);
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
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#update(com.excilys
     * .formation.cdb.model.Computer)
     */
    @Override
    public void update(Computer computer) {
	String name = computer.getName();
	Timestamp dateIntroduced = new Timestamp(computer.getDateIntroduced()
		.toEpochDay());
	Timestamp dateDiscontinued = new Timestamp(computer
		.getDateDiscontinued().toEpochDay());
	String nameCompany = computer.getCompany().getName();
	String updateQuery = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	String queryCompanyId = "select * from computer where id=?";
	ResultSet rslt = null;
	PreparedStatement stmt = null;
	PreparedStatement preparedStmt = null;
	try {

	    stmt = dataSource.getConnection().prepareStatement(queryCompanyId);
	    stmt.setString(1, nameCompany);
	    rslt = stmt.executeQuery();
	    preparedStmt = dataSource.getConnection().prepareStatement(
		    updateQuery);
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

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#delete(long)
     */
    @Override
    public void delete(long id) {
	String query = "DELETE FROM computer WHERE id=?";
	JdbcTemplate delete = new JdbcTemplate(dataSource);
	delete.update(query, new Object[] { id });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#search(java.lang.String
     * , int, int)
     */
    @Override
    public List<Computer> search(String str, int num, int offset) {

	ArrayList<Computer> computers = new ArrayList<Computer>();

	String query = "SELECT comput.id, comput.name, introduced, discontinued, company_id , c.name AS cname FROM computer comput LEFT OUTER JOIN company c on c.id = comput.company_id  WHERE comput.name LIKE ? LIMIT ?, ?";

	JdbcTemplate search = new JdbcTemplate(dataSource);

	List<Map<String, Object>> rows = search.queryForList(query,
		new Object[] { str + "%", num, offset });

	for (Map<String, Object> row : rows) {
	    Computer computer = null;
	    Company company = null;
	    if (row.get("cname") != null) {
		Long idCompany = Long.parseLong(row.get("company_id")
			.toString());
		company = new Company(idCompany, row.get("cname").toString());
	    }
	    LocalDate dateIntroduced = null, dateDiscontinued = null;

	    if (row.get("introduced") != null) {
		Timestamp introducedTS = Timestamp.valueOf(row
			.get("introduced").toString());
		dateIntroduced = introducedTS.toLocalDateTime().toLocalDate();
	    }

	    if (row.get("discontinued") != null) {
		Timestamp discontinuedTS = Timestamp.valueOf(row.get(
			"discontinued").toString());
		dateDiscontinued = discontinuedTS.toLocalDateTime()
			.toLocalDate();

	    }

	    computer = new Computer(Long.parseLong(row.get("id").toString()),
		    row.get("name").toString(), dateIntroduced,
		    dateDiscontinued, company);

	    computers.add(computer);
	}
	return computers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#count(java.lang.String
     * )
     */
    @Override
    public int count(String name) {
	String query = "SELECT COUNT(id) FROM computer WHERE name LIKE ?";
	JdbcTemplate count = new JdbcTemplate(dataSource);
	return count.queryForObject(query, new Object[] { name + "%" },
		Integer.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#findByCompany(java
     * .lang.Long)
     */
    @Override
    public List<Long> findByCompany(Long companyId) {

	List<Long> computersId = new ArrayList<Long>();
	String query = "SELECT id FROM computer WHERE company_id = ?";
	PreparedStatement pstmt = null;
	ResultSet results = null;
	try {
	    pstmt = dataSource.getConnection().prepareStatement(query);
	    pstmt.setLong(1, companyId);
	    results = pstmt.executeQuery();

	    while (results.next()) {
		computersId.add(results.getLong(1));
	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} 
	return computersId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#deleteByCompany(java
     * .lang.Long)
     */
    @Override
    @Transactional
    public void deleteByCompany(Long id) {
	List<Long> computersId = findByCompany(id);
	for (Long computerId : computersId) {
	    delete(computerId);
	    logger.info("computer(" + computerId + ") removed");
	}
    }
}
