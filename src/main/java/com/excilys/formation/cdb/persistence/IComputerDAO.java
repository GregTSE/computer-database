package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;

public interface IComputerDAO {

    /**
     * Build an object Computer and fill the fields with the informations from
     * the database
     * 
     * @param id
     *            of the computer
     * @return an instance of the Computer class
     */
    public abstract Computer find(long id, Connection connection);

    /**
     * Build a list of computers with fields 'id' and 'name'
     * 
     * @return the list of computers
     */
    public abstract List<Computer> findAll(Connection connection);

    /**
     * Find computers whose ID is between num and num+offset
     * 
     * @param num
     * @param offset
     * @return list of computers
     */
    public abstract List<Computer> findAll(int num, int offset, Connection connection);

    /**
     * insert in the database the parameter 'computer'
     * 
     * @param computer
     *            to insert in the database
     */
    public abstract void create(String name, String introduced,
	    String discontinued, Company company, Connection connection);

    /**
     * Update a computer by id
     * 
     * @param computer
     *            with new fields
     */
    public abstract void update(Computer computer, Connection connection);

    /**
     * Delete the computer whose ID is 'id'
     * 
     * @param id
     */
    public abstract void delete(long id, Connection connection);

    /**
     * Search of computers which
     * 
     * @param str
     * @param num
     * @param offset
     * @return a computers list
     */
    public abstract List<Computer> search(String str, int num, int offset, Connection connection);

    public abstract int count(String name, Connection connection);

    public abstract List<Long> findByCompany(Long companyId, Connection connection);

    public abstract void deleteByCompany(Long id, Connection connection);


}