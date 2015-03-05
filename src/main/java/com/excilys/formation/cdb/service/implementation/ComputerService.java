package com.excilys.formation.cdb.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.persistence.implementation.ComputerDAO;
import com.excilys.formation.cdb.service.IComputerService;

public class ComputerService implements IComputerService {

    Connection connection;
    
    public ComputerService() {
	super();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll()
     */
    @Override
    public List<Computer> findAll() {
	List<Computer> computers;
	getConnection();
	computers = ComputerDAO.INSTANCE.findAll(connection);
	closeConnection();
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll(int, int)
     */
    @Override
    public List<Computer> findAll(int num, int offset) {
	List<Computer> computers;
	getConnection();
	computers = ComputerDAO.INSTANCE.findAll(num, offset, connection);
	closeConnection();
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(int)
     */
    @Override
    public Computer find(int id) {
	Computer computer;
	getConnection();
	computer = ComputerDAO.INSTANCE.find(id, connection);
	closeConnection();
	return computer;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void create(String name, String introduced, String discontinued, Company company) {
	getConnection();
	ComputerDAO.INSTANCE.create(name, introduced, discontinued, company, connection);
	closeConnection();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(int)
     */
    @Override
    public void delete(int id) {
	getConnection();
	ComputerDAO.INSTANCE.delete(id, connection);
	closeConnection();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public void update(Computer computer) {
	getConnection();
	ComputerDAO.INSTANCE.update(computer, connection);
	closeConnection();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public List<Computer> search(String str, int num, int offset) {
	List<Computer> computers;
	getConnection();
	computers = ComputerDAO.INSTANCE.search(str, num, offset, connection);
	return computers;
    }
    

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public int count(String word) {
	getConnection();
	int result = ComputerDAO.INSTANCE.count(word, connection);
	closeConnection();
	return result;
    }
    
    private void getConnection(){
	try {
	    connection = ConnectionDAO.INSTANCE.connectionPool.getConnection();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    private void closeConnection(){
	try {
	    if (connection != null && !connection.isClosed()) {
		connection.close();
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}
