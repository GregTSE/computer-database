package com.excilys.formation.cdb.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.persistence.implementation.CompanyDAO;
import com.excilys.formation.cdb.service.ICompanyService;

public class CompanyService implements ICompanyService {

    private Connection connection;
    
    public CompanyService() {
	super();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.ICompanyService#findAll()
     */
    @Override
    public List<Company> findAll() {
	getConnection();
	List<Company> companies = null;
	companies = CompanyDAO.INSTANCE.findAll(connection);
	closeConnection();
	return companies;
    }
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.ICompanyService#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
	getConnection();
	CompanyDAO.INSTANCE.delete(id, connection);
	closeConnection();
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
