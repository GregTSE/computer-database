package com.excilys.formation.cdb.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.IComputerService;

public abstract class AbsComputerService implements IComputerService {

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll()
     */
    @Override
    public final List<Computer> findAll(){
	List<Computer> computers = findAllAbs();
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(long)
     */
    @Override
    public final Computer find(long id){
	Computer computer = findAbs(id);
	return computer;
    };

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public final void create(String name, String introduced,
	    String discontinued, Company company){
	createAbs(name, introduced, discontinued, company);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(long)
     */
    @Override
    public final void delete(long id){
	deleteAbs(id);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public final void update(Computer computer){
	updateAbs(computer);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public final List<Computer> search(String str, int num, int offset){
	List<Computer> computers = searchAbs( str, num, offset);
	return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public final int count(String word){
    	int countComputers = countAbs( word);
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
    
}