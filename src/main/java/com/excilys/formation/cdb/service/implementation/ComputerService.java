package com.excilys.formation.cdb.service.implementation;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.implementation.ComputerDAO;
import com.excilys.formation.cdb.service.IComputerService;

public class ComputerService implements IComputerService {

    public ComputerService() {
	super();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll()
     */
    @Override
    public List<Computer> findAll() {
	return ComputerDAO.INSTANCE.findAll();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll(int, int)
     */
    @Override
    public List<Computer> findAll(int num, int offset) {
	return ComputerDAO.INSTANCE.findAll(num, offset);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(int)
     */
    @Override
    public Computer find(int id) {
	return ComputerDAO.INSTANCE.find(id);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void create(String name, String introduced, String discontinued, Company company) {
	ComputerDAO.INSTANCE.create(name, introduced, discontinued, company);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(int)
     */
    @Override
    public void delete(int id) {
	ComputerDAO.INSTANCE.delete(id);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public void update(Computer computer) {
	ComputerDAO.INSTANCE.update(computer);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public List<Computer> search(String str, int num, int offset) {
	return ComputerDAO.INSTANCE.search(str, num, offset);
    }
    

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public int count(String word) {
	return ComputerDAO.INSTANCE.count(word);
    }
    
}
