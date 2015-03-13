package com.excilys.formation.cdb.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.IComputerDAO;

@Service
public class ComputerService extends AbsComputerService {
    
    @Autowired
    private IComputerDAO computerDAO;
    
    public ComputerService() {
	super();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll()
     */
    @Override
    public List<Computer> findAllAbs() {
	return computerDAO.findAll();
	
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(int)
     */
    @Override
    public Computer findAbs(long id) {
	return computerDAO.find(id);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void createAbs(String name, String introduced, String discontinued, Company company) {
	computerDAO.create(name, introduced, discontinued, company);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(int)
     */
    @Override
    public void deleteAbs(long id) {
	computerDAO.delete(id);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public void updateAbs(Computer computer) {
	computerDAO.update(computer);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public List<Computer> searchAbs(String str, int num, int offset) {
	return computerDAO.search(str, num, offset);
    }
    

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public int countAbs(String word) {
	return computerDAO.count(word);
    }
    
    
}
