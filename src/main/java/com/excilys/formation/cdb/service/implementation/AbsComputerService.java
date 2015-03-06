package com.excilys.formation.cdb.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.service.IComputerService;

public abstract class AbsComputerService implements IComputerService {

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll()
     */
    @Override
    public final List<Computer> findAll(){
	Connection connection = getConnection();
	List<Computer> computers = findAllAbs(connection);
	closeConnection(connection);
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(long)
     */
    @Override
    public final Computer find(long id){
	Connection connection = getConnection();
	Computer computer = findAbs(id, connection);
	closeConnection(connection);
	return computer;
    };

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public final void create(String name, String introduced,
	    String discontinued, Company company){
	Connection connection = getConnection();
	createAbs(name, introduced, discontinued, company, connection);
	closeConnection(connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(long)
     */
    @Override
    public final void delete(long id){
	Connection connection = getConnection();
	deleteAbs(id, connection);
	closeConnection(connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public final void update(Computer computer){
	Connection connection = getConnection();
	updateAbs(computer, connection);
	closeConnection(connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public final List<Computer> search(String str, int num, int offset){
	Connection connection = getConnection();
	List<Computer> computers = searchAbs( str, num, offset, connection);
	closeConnection(connection);
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public final int count(String word){
	Connection connection = getConnection();
	int countComputers = countAbs( word, connection);
	closeConnection(connection);
	return countComputers;
    }
    
    public abstract List<Computer> findAllAbs(Connection connection);
    
    public abstract Computer findAbs(long id, Connection connection);
    
    public abstract void createAbs(String name, String introduced,
	    String discontinued, Company company, Connection connection);
    
    public abstract void deleteAbs(long id, Connection connection);
    
    public abstract void updateAbs(Computer computer, Connection connection);
    
    public abstract List<Computer> searchAbs(String str, int num, int offset, Connection connection);
    
    public abstract int countAbs(String word, Connection connection);
    
    
    private Connection getConnection(){
	Connection connection = null;
	try {
	    connection = ConnectionDAO.INSTANCE.connectionPool.getConnection();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return connection;
    }
    
    private void closeConnection(Connection connection){
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