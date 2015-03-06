package com.excilys.formation.cdb.service.implementation;

import java.sql.Connection;
import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.implementation.ComputerDAO;

public class ComputerService extends AbsComputerService {
    
    public ComputerService() {
	super();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll()
     */
    @Override
    public List<Computer> findAllAbs(Connection connection) {
	return ComputerDAO.INSTANCE.findAll(connection);
	
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(int)
     */
    @Override
    public Computer findAbs(long id, Connection connection) {
	return ComputerDAO.INSTANCE.find(id, connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void createAbs(String name, String introduced, String discontinued, Company company, Connection connection) {
	ComputerDAO.INSTANCE.create(name, introduced, discontinued, company, connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(int)
     */
    @Override
    public void deleteAbs(long id, Connection connection) {
	ComputerDAO.INSTANCE.delete(id, connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public void updateAbs(Computer computer, Connection connection) {
	ComputerDAO.INSTANCE.update(computer, connection);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public List<Computer> searchAbs(String str, int num, int offset, Connection connection) {
	return ComputerDAO.INSTANCE.search(str, num, offset, connection);
    }
    

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public int countAbs(String word, Connection connection) {
	return ComputerDAO.INSTANCE.count(word, connection);
    }
    
    
}
