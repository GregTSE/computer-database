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
	List<Computer> computers = findAllAbs();
	closeConnection(connection);
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(long)
     */
    @Override
    public final Computer find(long id){
	Connection connection = getConnection();
	Computer computer = findAbs(id);
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
	createAbs(name, introduced, discontinued, company);
	closeConnection(connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(long)
     */
    @Override
    public final void delete(long id){
	Connection connection = getConnection();
	deleteAbs(id);
	closeConnection(connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public final void update(Computer computer){
	Connection connection = getConnection();
	updateAbs(computer);
	closeConnection(connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public final List<Computer> search(String str, int num, int offset){
	Connection connection = getConnection();
	List<Computer> computers = searchAbs( str, num, offset);
	closeConnection(connection);
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public final int count(String word){
	Connection connection = getConnection();
	int countComputers = countAbs( word);
	closeConnection(connection);
	return countComputers;
    }
    
    public abstract List<Computer> findAllAbs();
    
    public abstract Computer findAbs(long id);
    
    public abstract void createAbs(String name, String introduced,
	    String discontinued, Company company);
    
    public abstract void deleteAbs(long id);
    
    public abstract void updateAbs(Computer computer);
    
    public abstract List<Computer> searchAbs(String str, int num, int offset);
    
    public abstract int countAbs(String word);
    
    
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